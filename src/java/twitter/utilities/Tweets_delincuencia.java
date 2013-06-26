package twitter.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static labs.CrunchifyJSON.callURL;
import twitter.bean.BGeometry;
import twitter.bean.BPunto_delincuencia;
import twitter.bean.BUsuario;

/**
 *
 * @author ruben
 */
public class Tweets_delincuencia {

    public List<BPunto_delincuencia> get_tweets(List<BPunto_delincuencia> list_in_db) { //list_in_db= listado de tweets en la base de datos

        List<BPunto_delincuencia> list = new LinkedList<BPunto_delincuencia>();
        String jsonString = callURL("https://search.twitter.com/search.json?q=%23inseguridad&include_entities=1&&geocode=-13.16074,-74.22563,1mi");
        //System.out.println("\n\njsonString: " + jsonString);
        //quitar el primero 
        String word = "\"results\":";
        //System.out.println(jsonString.indexOf(word));
        int first_position = jsonString.indexOf(word) + 10;
        jsonString = jsonString.substring(first_position);
        // System.out.println(jsonString);

        //quitar la ultima parte
        String last_word = "results_per_page";
        //System.out.println(jsonString.indexOf(word)); 
        int last_position = jsonString.indexOf(last_word);
        jsonString = jsonString.substring(0, last_position);
        // System.out.println(jsonString);
        Utilities utilities = new Utilities();


        //List<BUsuario> list_usuario = new LinkedList<BUsuario>();

        try {
            //INICIA CON CON aRRAR DE OBJETOS DE TWITER
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObj = new JSONObject();
            jsonArray.put(jsonObj);

            for (int i = 0; i < jsonArray.length() - 1; i++) {   // iterate through jsonArray 
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                BPunto_delincuencia bp = new BPunto_delincuencia();

                bp.setIdpunto(jsonObject.get("id") + "t");

                boolean bandera = false;//suponemos que el twwet no pertenece a la lista de la base de datos.

                for (int j = 0; j < list_in_db.size(); j++) {//for para verificar si el twett pertenece a la base de datos o no
                    //  System.out.println("de Twiter " + bp.getIdpunto());
                    //System.out.println("de DB " + list_in_db.get(j).getIdpunto());

                    if ((list_in_db.get(j).getIdpunto() + "").equals(bp.getIdpunto() + "")) {
                        bandera = true;// tweet perteence a la base de datos
                    }

                }


                if (!bandera) {//haora ya verifica si perteence o no  pra el registro en la base de datos
                    //BPunto_delincuencia bPunto = new BPunto_delincuencia();
                    BPunto_delincuencia bPunto = new BPunto_delincuencia();
                    BUsuario bUsuario = new BUsuario();


                    bPunto.setIdpunto(jsonObject.get("id") + "t");

                    String des = jsonObject.get("text") + "";//descripcion
                    //

                    bPunto.setTipo(get_tipo(des));



                    //optener hora y fecha
                    bPunto.setFecha(utilities.get_Date(jsonObject.get("created_at") + ""));
                    bPunto.setHora(utilities.get_hour(jsonObject.get("created_at") + ""));



                    bPunto.setIdusuario(jsonObject.get("from_user_id_str") + "");
                    bPunto.setUsuario(jsonObject.get("from_user") + "");


                    //descripcion
                    bPunto.setDescripcion(des);
                    
                    bPunto.setDirec_ref("null");
                    bPunto.setDe_conocimiento("No");
                    

                    //ENTITIES
                    //System.out.println("ENTITIES");
                    JSONObject entities = jsonObject.getJSONObject("entities");

                    if (entities.toString().length() > 500) {
                        Object obj_entities = entities;
                        JSONObject jsonObject_entities = (JSONObject) obj_entities;

                        //MEDIA
                        //System.out.println("MEDIA");
                        String media = jsonObject_entities.get("media").toString();

                        JSONArray jsonArray_media = new JSONArray(media);

                        JSONObject jsonObj_media = new JSONObject();
                        jsonArray_media.put(jsonObj_media);

                        JSONObject jsonObject_media = jsonArray_media.getJSONObject(0);
                        bPunto.setImg(jsonObject_media.get("media_url").toString());

                    } else {
                        bPunto.setImg("null");
                    }
                    bPunto.setReg_de("t");
                    bPunto.setEstado(true);
                    
                    //GEO
                    //System.out.println("GEO");
                    JSONObject geo = jsonObject.getJSONObject("geo");
                    //System.out.println(geo.toString());
                    //System.out.println(geo);
                    Object obj_geo = geo;
                    JSONObject jsonObject_geo = (JSONObject) obj_geo;
                    //CORDINATES
                    String cordinates = jsonObject_geo.get("coordinates").toString();
                    cordinates = cordinates.replace("[", "");
                    cordinates = cordinates.replace("]", "");
                    String[] cord = cordinates.split(",");
                    BGeometry bGeometry = new BGeometry();
                    bGeometry.setLatitud(Double.parseDouble(cord[0]));
                    bGeometry.setLongitud(Double.parseDouble(cord[1]));
                    bPunto.setGeometry(bGeometry);
                    //Usuario
                    //bUsuario.setNombre(jsonObject.get("from_user_name") + "");
                    //bUsuario.setUsuario(jsonObject.get("from_user") + "");

                    bPunto.print();

                    list.add(bPunto);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(list.size());
        return list;
    }

    public static String callURL(String myURL) {
        System.out.println("Requested URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null) {
                urlConn.setReadTimeout(60 * 1000);
            }
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:" + myURL, e);
        }

        return sb.toString();
    }

    public String get_tipo(String des) {
        String tipo = "";
        if (des.contains("Robo")) {
            tipo = "Robo";
        } else if (des.contains("Intento de Robo")) {
            tipo = "Intento de Robo";
        } else if (des.contains("Agresion")) {
            tipo = "Agresion";
        } else if (des.contains("Homicidio")) {
            tipo = "Homicidio";
        } else if (des.contains("Secuestro")) {
            tipo = "Secuestro";
        } else if (des.contains("Accidente")) {
            tipo = "Accidente";
        } else if (des.contains("Violencia Familiar")) {
            tipo = "Violencia Familiar";
        } else {
            tipo = "Otros";
        }
        return tipo;
    }
}

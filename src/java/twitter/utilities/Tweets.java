/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static labs.CrunchifyJSON.callURL;
import static labs.CrunchifyJSON.get_geo;
import twitter.bean.BGeometry;
import twitter.bean.BPunto;

/**
 *
 * @author ruben
 */
public class Tweets {

    public List<BPunto> get_tweets(List<BPunto> list_in_db) { //list_in_db= listado de tweets en la base de datos

        List<BPunto> list = new LinkedList<BPunto>();
        String jsonString = callURL("http://search.twitter.com/search.json?q=%23aquid&include_entities=1&&geocode=-13.16074,-74.22563,1mi");
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

        try {
            //INICIA CON CON aRRAR DE OBJETOS DE TWITER
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObj = new JSONObject();
            jsonArray.put(jsonObj);

            for (int i = 0; i < jsonArray.length() - 1; i++) {   // iterate through jsonArray 
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                BPunto bp = new BPunto();
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
                    BPunto bPunto = new BPunto();
                    bPunto.setIdpunto(jsonObject.get("id") + "t");
                    bPunto.setDescripcion(jsonObject.get("text") + "");
                    //optener hora y fecha
                    bPunto.setFecha(utilities.get_Date(jsonObject.get("created_at") + ""));
                    bPunto.setHora(utilities.get_hour(jsonObject.get("created_at") + ""));




                    //ENTITIES
                    System.out.println("ENTITIES");
                    JSONObject entities = jsonObject.getJSONObject("entities");

                    if (entities.toString().length() > 500) {
                        Object obj_entities = entities;
                        JSONObject jsonObject_entities = (JSONObject) obj_entities;

                        //MEDIA
                        System.out.println("MEDIA");
                        String media = jsonObject_entities.get("media").toString();

                        JSONArray jsonArray_media = new JSONArray(media);

                        JSONObject jsonObj_media = new JSONObject();
                        jsonArray_media.put(jsonObj_media);

                        JSONObject jsonObject_media = jsonArray_media.getJSONObject(0);

                        //MEDIA URL
                        System.out.println("Media URL");
                        //System.out.println(jsonObject_media.get("media_url"));

                        bPunto.setUrl_img(jsonObject_media.get("media_url").toString());


                    } else {

                        bPunto.setUrl_img("null");

                    }

                    // bPunto.setUrl_img(get_media_url(jsonObject + ""));
                    bPunto.setUsuario(jsonObject.get("from_user") + "");
                    bPunto.setNombre(jsonObject.get("from_user_name") + "");
                    //bPunto.setFrom_user_name((String) jsonObject.get("from_user_name"));
                    bPunto.setPerfil_img((String) jsonObject.get("profile_image_url"));
                    bPunto.setEstado(true);
                    bPunto.setTipo("t");

                    //GEO
                    //System.out.println("GEO");
                    JSONObject geo = jsonObject.getJSONObject("geo");
                    System.out.println(geo.toString());
                    System.out.println(geo);
                    Object obj_geo = geo;
                    JSONObject jsonObject_geo = (JSONObject) obj_geo;
                    //CORDINATES
                    //System.out.println("CORDINATES");
                    String cordinates = jsonObject_geo.get("coordinates").toString();
                    cordinates = cordinates.replace("[", "");
                    cordinates = cordinates.replace("]", "");
                    //System.out.println(cordinates);

                    String[] cord = cordinates.split(",");

                    //System.out.println("Latitud: " + cord[0]);
                    //System.out.println("Longitud: " + cord[1]);

                    BGeometry bGeometry = new BGeometry();
                    bGeometry.setLatitud(Double.parseDouble(cord[0]));
                    bGeometry.setLongitud(Double.parseDouble(cord[1]));
                    bPunto.setGeometry(bGeometry);

                    bPunto.print();
                    //bPunto.setGeometry(get_geo(jsonObject + ""));
                    //System.out.println(bPunto.getIdpunto());
                    list.add(bPunto);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(list.size());
        return list;
    }

    public static String get_media_url(String json) {

        String first_word = "http://pbs.twimg.com/";

        int first_position = json.lastIndexOf(first_word);
        System.out.println(first_position);
        json = json.substring(first_position);
        //System.out.println("-------" + json);
        String second_word = "\",\"expanded_url\":";
        int second_position = json.lastIndexOf(second_word);
        //System.out.println(second_position);
        json = json.substring(0, second_position);
        System.out.println(json);
        return json;
    }

    public static BGeometry get_geo(String json) {
        //"geo":{"type":"Point","coordinates":[-13.16728063,-74.22774986]},
        //"profile_image_url_https":"https://si0.twimg.com/profile_images/2426796802/8hevn3ed4dpqx6l8pkh0_normal.jpeg",

        BGeometry bGeometry = new BGeometry();
        String second_word = "]},\"profile_image_url_https\":";
        int second_position = json.lastIndexOf(second_word);
        //System.out.println(second_position);
        json = json.substring(0, second_position);
        //System.out.println(json);

        if (json.contains("\"type\":\"Point\"")) {
            bGeometry.setType("Point");
            //vedifica primeros Caracteres
            String first_word = "\"type\":\"Point\",\"coordinates\":[";
            //System.out.println(jsonString.indexOf(word));
            int first_position = json.indexOf(first_word);
            //System.out.println(first_position);
            json = json.substring(first_position);

            //sacar el tamaño de este String para obtener cordenadas 
            int string_lengt = "\"type\":\"Point\",\"coordinates\":[".length();
            json = json.substring(string_lengt);

            String lat = json.substring(0, json.indexOf(","));
            //System.out.println("Lat" + lat);
            String lon = json.substring(json.indexOf(","));
            lon = lon.substring(1);
            bGeometry.setLatitud(Double.parseDouble(lat));
            bGeometry.setLongitud(Double.parseDouble(lon));
            //double[] conIsrdinates = {Double.parseDouble(lat), Double.parseDouble(lon)};

            //bGeometry.setCoordinates(conIsrdinates);
        }
        return bGeometry;
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
}

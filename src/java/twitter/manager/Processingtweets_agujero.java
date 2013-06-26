/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.manager;

import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;
import twitter.bean.BPunto_Agujero;
import twitter.datasource.BDConnecion;
import twitter.utilities.Tweets_agujeros;
import twitter.utilities.Tweets_delincuencia;

public class Processingtweets_agujero extends TimerTask {

    ManagerPuntos_agujeros md = null;

    public void run() {
        BDConnecion conexion = new BDConnecion();
        md = new ManagerPuntos_agujeros(conexion);
        List<BPunto_Agujero> list = new LinkedList<BPunto_Agujero>();

        list = md.getids_twitter();//from datat base

        System.out.println("size in data base : " + list.size());
        Tweets_agujeros tweets = new Tweets_agujeros();//cals fro get data twitter
        list = tweets.get_tweets(list);
        System.out.println("SIZE DE TWEETS QUE SE REGISTRARAN : " + list.size());

        for (int i = 0; i < list.size(); i++) {
            String resultado = md.registrar((BPunto_Agujero) list.get(i));
            System.out.println(list.get(i));

        }
    }
}

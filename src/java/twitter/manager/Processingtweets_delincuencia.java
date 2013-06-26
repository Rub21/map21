/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.manager;

import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;

import twitter.bean.BPunto_delincuencia;
import twitter.datasource.BDConnecion;
import twitter.utilities.Tweets_delincuencia;

public class Processingtweets_delincuencia extends TimerTask {

    ManagerPuntos_delincuencia md = null;

    public void run() {
        BDConnecion conexion = new BDConnecion();
        md = new ManagerPuntos_delincuencia(conexion);
        List<BPunto_delincuencia> list = new LinkedList<BPunto_delincuencia>();

        list = md.getids_twitter();//from datat base

        System.out.println("size in data base : " + list.size());
        Tweets_delincuencia tweets = new Tweets_delincuencia();//cals fro get data twitter
        list = tweets.get_tweets(list);
        System.out.println("SIZE DE TWEETS QUE SE REGISTRARAN : " + list.size());
        
         for (int i = 0; i < list.size(); i++) {
         String resultado = md.registrar((BPunto_delincuencia) list.get(i));
         System.out.println(list.get(i));
        
         }
    }
}
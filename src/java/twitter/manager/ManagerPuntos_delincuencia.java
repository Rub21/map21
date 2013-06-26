/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.manager;

import java.sql.Connection;
import java.util.List;
import twitter.bean.BPunto;
import twitter.bean.BPunto_delincuencia;
import twitter.dao.DAOPuntos;
import twitter.dao.DAOPuntos_Delincuencia;
import twitter.datasource.BDConnecion;

/**
 *
 * @author ruben
 */
public class ManagerPuntos_delincuencia {

    DAOPuntos_Delincuencia daop;
    Connection cn = null;

    public ManagerPuntos_delincuencia(BDConnecion connecion) {
        this.cn = connecion.getConnection();
    }

    public int getlast() {
        daop = new DAOPuntos_Delincuencia(cn);
        return daop.getlast();
    }

    public String registrar(BPunto_delincuencia b) {
        daop = new DAOPuntos_Delincuencia(cn);
        return daop.registrar(b);

    }

    public List<BPunto_delincuencia> getids_twitter() {
        daop = new DAOPuntos_Delincuencia(cn);
        return daop.getids_twitter();
    }

    public List listarpuntos() {
        daop = new DAOPuntos_Delincuencia(cn);
        return daop.listarpuntos();
    }

    public List listardate() {
        daop = new DAOPuntos_Delincuencia(cn);
        return daop.listardate();


    }

    public List listardate_day() {
        daop = new DAOPuntos_Delincuencia(cn);
        return daop.listardate_day();



    }

    public List listardate_month() {
        daop = new DAOPuntos_Delincuencia(cn);
        return daop.listardate_month();
    }
}

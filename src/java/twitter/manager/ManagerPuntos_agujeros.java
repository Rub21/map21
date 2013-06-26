/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.manager;

import java.sql.Connection;
import java.util.List;
import twitter.bean.BPunto_Agujero;
import twitter.bean.BPunto_delincuencia;
import twitter.dao.DAOPuntos_Agujeros;
import twitter.dao.DAOPuntos_Delincuencia;
import twitter.datasource.BDConnecion;

/**
 *
 * @author ruben
 */
public class ManagerPuntos_agujeros {

    DAOPuntos_Agujeros daop;
    Connection cn = null;

    public ManagerPuntos_agujeros(BDConnecion connecion) {
        this.cn = connecion.getConnection();
    }

    public int getlast() {
        daop = new DAOPuntos_Agujeros(cn);
        return daop.getlast();
    }

    public String registrar(BPunto_Agujero b) {
        daop = new DAOPuntos_Agujeros(cn);
        return daop.registrar(b);

    }

    public List<BPunto_Agujero> getids_twitter() {
        daop = new DAOPuntos_Agujeros(cn);
        return daop.getids_twitter();
    }

    public List listarpuntos() {
        daop = new DAOPuntos_Agujeros(cn);
        return daop.listarpuntos();
    }

    public List listardate() {
        daop = new DAOPuntos_Agujeros(cn);
        return daop.listardate();
    }

    public List listardate_day() {
          daop = new DAOPuntos_Agujeros(cn);
        return daop.listardate_day();  }

    public List listardate_month() {
          daop = new DAOPuntos_Agujeros(cn);
        return daop.listardate_month();
    }
}

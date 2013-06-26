/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.manager;

import java.sql.Connection;
import twitter.bean.BPunto_Desechos;
import twitter.bean.BPunto_delincuencia;
import twitter.dao.DAOPuntos_Desechos;
import twitter.dao.DAOPuntos_Delincuencia;
import twitter.datasource.BDConnecion;

/**
 *
 * @author ruben
 */
public class ManagerPuntos_desechos {

    DAOPuntos_Desechos daop;
    Connection cn = null;

    public ManagerPuntos_desechos(BDConnecion connecion) {
        this.cn = connecion.getConnection();
    }

    public int getlast() {
        daop = new DAOPuntos_Desechos(cn);
        return daop.getlast();
    }

    public String registrar(BPunto_Desechos b) {
        daop = new DAOPuntos_Desechos(cn);
        return daop.registrar(b);

    }
}

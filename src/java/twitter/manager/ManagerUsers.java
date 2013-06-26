/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.manager;

import java.sql.Connection;
import twitter.bean.BUsuario;
import twitter.dao.DAOLogin;
import twitter.dao.DAOUsers;
import twitter.datasource.BDConnecion;

/**
 *
 * @author ruben
 */
public class ManagerUsers {

    DAOUsers dAOUsers;
    Connection cn = null;

    public ManagerUsers(BDConnecion connecion) {
        this.cn = connecion.getConnection();
    }

    public int getlast() {
        dAOUsers = new DAOUsers(cn);
        return dAOUsers.getlast();
    }

    public boolean registrar(BUsuario b) {
        dAOUsers = new DAOUsers(cn);
        return dAOUsers.registrar(b);
    }

    public int autenticar(String usuario, String password) {
        dAOUsers = new DAOUsers(cn);
        return dAOUsers.autenticar(usuario, password);
    }

    public boolean rewrite_password(String usuario, String newp) {
        dAOUsers = new DAOUsers(cn);
        return dAOUsers.rewrite_password(usuario, newp);
    }
}

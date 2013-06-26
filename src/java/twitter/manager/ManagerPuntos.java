/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.manager;

import java.sql.Connection;
import java.util.List;
import twitter.bean.BPunto;
import twitter.dao.DAOPuntos;
import twitter.datasource.BDConnecion;

/**
 *
 * @author ruben
 */
public class ManagerPuntos {

    DAOPuntos dAOPuntoDesecho;
    Connection cn = null;

    public ManagerPuntos(BDConnecion connecion) {
        this.cn = connecion.getConnection();
    }

    public List<BPunto> getids_twitter() {
        dAOPuntoDesecho = new DAOPuntos(cn);
        return dAOPuntoDesecho.getids_twitter();
    }

    public String registrar(BPunto b) {
        dAOPuntoDesecho = new DAOPuntos(cn);
        return dAOPuntoDesecho.registrar(b);

    }

    public List listarpuntospendientes() {
        dAOPuntoDesecho = new DAOPuntos(cn);
        return dAOPuntoDesecho.listarpuntospendientes();
    }

    public List listardate() {
        dAOPuntoDesecho = new DAOPuntos(cn);
        return dAOPuntoDesecho.listardate();
    }

    public void actualizar_punto(String id_punto) {
        dAOPuntoDesecho = new DAOPuntos(cn);
        dAOPuntoDesecho.actualizar_punto(id_punto);

    }

    public List listarestadisticas() {
        dAOPuntoDesecho = new DAOPuntos(cn);
        return dAOPuntoDesecho.listarestadisticas();

    }

    public List listarpuntos() {
        dAOPuntoDesecho = new DAOPuntos(cn);
        return dAOPuntoDesecho.listarpuntos();
    }

    public int getlast() {
        dAOPuntoDesecho = new DAOPuntos(cn);
        return dAOPuntoDesecho.getlast();
    }

    public List listarestadisticas_month() {
        dAOPuntoDesecho = new DAOPuntos(cn);
        return dAOPuntoDesecho.listarestadisticas_month();
    }

    public List listarpuntosatendidos() {
        dAOPuntoDesecho = new DAOPuntos(cn);
        return dAOPuntoDesecho.listarpuntosatendidos();
    }
}

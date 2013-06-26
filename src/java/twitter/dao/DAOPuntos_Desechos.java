/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter.bean.BPunto_Desechos;

/**
 *
 * @author ruben
 */
public class DAOPuntos_Desechos {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public DAOPuntos_Desechos(Connection conn) {
        this.conn = conn;
    }

    public int getlast() {
        int num = 0;
        try {
            String sql = "select count(*) as num from puntos_desecho where reg_de='w';";
            //System.out.println("==========SQL punto= : " + sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                num = rs.getInt("num");
            }
            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error en optener posicion de punto : " + ex);
        }
        return num + 1;
    }

    public String registrar(BPunto_Desechos bp) {

        String resultado = null;
        try {

            String sql = "select insert_punto_desecho( '" + bp.getIdpunto() + "', '" + bp.getIdusuario() + "', '" + bp.getUsuario() + "', '" + bp.getFecha() + "', '" + bp.getHora() + "', '" + bp.getDescripcion() + "','" + bp.getDirec_ref() + "', '" + bp.getDe_conocimiento() + "', '" + bp.getImg() + "', '" + bp.getReg_de() + "', " + bp.isEstado() + ", " + bp.getbGeometry().getLatitud() + ", " + bp.getbGeometry().getLongitud() + ");";

            System.out.println("SQL" + sql);
            pstmt = conn.prepareStatement(sql);
            //pstmt.executeUpdate();
            pstmt.executeQuery();
            // conn.commit();
            resultado = "Registro Exitoso";
        } catch (SQLException ex) {
            Logger.getLogger(DAOPuntos.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
        return resultado;
    }
}

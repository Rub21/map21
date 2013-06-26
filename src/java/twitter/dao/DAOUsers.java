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
import twitter.bean.BUsuario;

/**
 *
 * @author ruben
 */
public class DAOUsers {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public DAOUsers(Connection conn) {
        this.conn = conn;
    }

    public int getlast() {

        int num = 0;
        try {
            String sql = "select count(*) as num from usuario;";

            System.out.println("==========SQL Usuario= : " + sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();


            while (rs.next()) {
                num = rs.getInt("num");
            }

            // pstmt.close();
            //rs.close();
        } catch (SQLException ex) {
            System.out.println("Error en optener posicion de punto : " + ex);
        }
        return num + 1;


    }

    public boolean registrar(BUsuario b) {

        boolean bandera = false;
        try {

            String sql = "INSERT INTO usuario( idusuario, correo, nombre, usuario, contrasenia, estado, rol)"
                    + "VALUES ('" + b.getIdusuario() + "','" + b.getCorreo() + "','" + b.getNombre() + "','" + b.getUsuario() + "','" + b.getContrasenia() + "'," + b.isEstado() + "," + b.getRol() + ");";
            System.out.println("SQL" + sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            /*pstmt.executeQuery();
             conn.commit();*/
            bandera = true;

        } catch (SQLException ex) {
            bandera = false;
            Logger.getLogger(DAOPuntos.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }

        return bandera;
    }

    public int autenticar(String usuario, String password) {

        int banndera = 0;
        /*
         * 1= primera ves y contreseña corecta
         * 2=primera ves y contraseña incorecta
         * 3= contraseña corecta y muchas visitas
         * 4= contrasela incorexta y muhcas veces
         */

        try {

            String sql = "SELECT contrasenia, estado   FROM usuario where usuario='" + usuario + "';";
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {

                String contrasenia = rs.getString("contrasenia");
                boolean estado = rs.getBoolean("estado");
                if (!estado) {
                    if (contrasenia.equals(password)) {
                        banndera = 1;
                    } else {
                        banndera = 2;
                        
                    }


                } else {
                    if (contrasenia.equals(password)) {
                        banndera = 3;
                    } else {
                        banndera = 4;
                    }


                }

            }

            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error en en User: " + ex);
        }
        return banndera;
    }

    public boolean rewrite_password(String usuario, String newp) {
        boolean bandera = false;
        try {

            String sql = "UPDATE usuario   SET  contrasenia='"+newp+"', estado=true WHERE usuario='"+usuario+"';";
                    
            System.out.println("SQL" + sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            /*pstmt.executeQuery();
             conn.commit();*/
            bandera = true;

        } catch (SQLException ex) {
            bandera = false;
            Logger.getLogger(DAOPuntos.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }

        return bandera;
    }
}

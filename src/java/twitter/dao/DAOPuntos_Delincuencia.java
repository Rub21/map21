/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter.bean.BEstadistica;
import twitter.bean.BGeometry;
import twitter.bean.BPunto;
import twitter.bean.BPunto_delincuencia;
import twitter.bean.BPuntos_Day;
import twitter.bean.BPuntos_Month;
import twitter.datasource.BDConnecion;

/**
 *
 * @author ruben
 */
public class DAOPuntos_Delincuencia {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public DAOPuntos_Delincuencia(Connection conn) {
        this.conn = conn;
    }

    public int getlast() {
        int num = 0;
        try {
            String sql = "select count(*) as num from puntos_delincuencia where reg_de='w';";
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

    public String registrar(BPunto_delincuencia bp) {

        String resultado = null;
        try {

            String sql = "select insert_punto_delincuencia( '" + bp.getIdpunto() + "', '" + bp.getTipo() + "', '" + bp.getIdusuario() + "', '" + bp.getUsuario() + "', '" + bp.getFecha() + "', '" + bp.getHora() + "', '" + bp.getDescripcion() + "','" + bp.getDirec_ref() + "', '" + bp.getDe_conocimiento() + "', '" + bp.getImg() + "', '" + bp.getReg_de() + "', " + bp.isEstado() + ", " + bp.getGeometry().getLatitud() + ", " + bp.getGeometry().getLongitud() + ");";

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

    public List<BPunto_delincuencia> getids_twitter() {//optiene la lista de id de los tweets que estan registrados

        List<BPunto_delincuencia> list = new LinkedList<BPunto_delincuencia>();
        try {
            String sql = "select idpunto from puntos_delincuencia where reg_de='t';";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BPunto_delincuencia bp = new BPunto_delincuencia();
                bp.setIdpunto(rs.getString("idpunto"));
                list.add(bp);
            }
            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error : " + ex);
        }
        return list;
    }

    public List listarpuntos() {

        List list = new LinkedList();
        try {
            String sql = "SELECT idpunto, tipo, idusuario, usuario, fecha, hora, descripcion, direc_ref, de_conocimiento, img, reg_de, estado, lat, lon  FROM puntos_delincuencia where estado=true;";

            System.out.println("********" + sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {

                BPunto_delincuencia bp = new BPunto_delincuencia();

                bp.setIdpunto(rs.getString("idpunto"));
                bp.setTipo(rs.getString("tipo"));
                bp.setUsuario(rs.getString("usuario"));
                bp.setFecha(rs.getString("fecha"));
                bp.setHora(rs.getString("hora"));
                bp.setDescripcion(rs.getString("descripcion"));
                bp.setReg_de(rs.getString("direc_ref"));
                bp.setDe_conocimiento(rs.getString("de_conocimiento"));
                bp.setImg(rs.getString("img"));
                bp.setReg_de(rs.getString("reg_de"));

                BGeometry bGeometry = new BGeometry();
                bGeometry.setLatitud(rs.getDouble("lat"));
                bGeometry.setLongitud(rs.getDouble("lon"));
                bGeometry.setCoordinates();
                bp.setGeometry(bGeometry);

                list.add(bp);
            }
            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error : " + ex);
        }
        return list;

    }

    public List listardate() {
        // System.out.println("ingreso aquir");
        List list = new LinkedList();
        try {

            for (int i = 1; i <= 12; i++) {
                List list_day = new LinkedList();
                String m = "";
                if (i < 9) {
                    m = "0" + i;
                } else {
                    m = i + "";
                }
                String sql = "select substring(fecha,1,2) as day   FROM puntos_delincuencia where substring(fecha,4,2)='" + m + "' GROUP BY day ORDER BY day DESC;";

                System.out.println(sql);
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();

                int contador = 0;
                System.out.println("---contador" + contador);
                while (rs.next()) {
                    list_day.add(Integer.parseInt(rs.getString("day")));
                    contador++;
                    System.out.println("---contador" + contador);
                }
                list.add(list_day);
            }
            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error : " + ex);
        }
        return list;



    }

    public List listardate_day() {

        List list = new LinkedList();
        try {
            String sql = "SELECT day,num_puntos  FROM select_date_day_delincuencia;";
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BPuntos_Day bpd = new BPuntos_Day();
                bpd.setDay(rs.getString("day"));
                bpd.setNum_puntos(rs.getInt("num_puntos"));

                list.add(bpd);
            }
            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error : " + ex);
        }
        return list;

    }

    public List listardate_month() {
   List list = new LinkedList();
        try {
            String sql = "SELECT month,num_puntos FROM select_date_month_delincuencia;";
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BPuntos_Month bpm= new BPuntos_Month();
                bpm.setMonth(rs.getString("month"));
                bpm.setNum_puntos(rs.getInt("num_puntos"));

                list.add(bpm);
            }
            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error : " + ex);
        }
        return list;
    }
}

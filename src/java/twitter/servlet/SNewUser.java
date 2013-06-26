/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import twitter.bean.BUsuario;
import twitter.datasource.BDConnecion;
import twitter.manager.ManagerUsers;

/**
 *
 * @author ruben
 */
public class SNewUser extends HttpServlet {

    ManagerUsers managerNewUser = null;
    ManagerUsers managerNewu = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        //declarte connecion to data base and context
        ServletContext ctx = this.getServletConfig().getServletContext();
        HttpSession sesion = request.getSession();

        BDConnecion conexion = new BDConnecion(ctx);
        BDConnecion con = new BDConnecion(ctx);

        //manager adn bean
        managerNewUser = new ManagerUsers(conexion);
        managerNewu = new ManagerUsers(con);

        try {

            String captcha = sesion.getAttribute("dns_security_code").toString();
            String valid_captcha = request.getParameter("captcha");

            System.out.println("--------------" + captcha);
            System.out.println("--------------" + valid_captcha);

            if (captcha.equals(valid_captcha)) {
                /*
                 System.out.println("captcha--------------" + captcha);
                 System.out.println("Valid captcha--------------" + valid_captcha);*/


                String id = managerNewu.getlast() + "u";

                BUsuario bUsuario = new BUsuario();

                bUsuario.setIdusuario(id);
                bUsuario.setCorreo(request.getParameter("correo"));
                bUsuario.setNombre(request.getParameter("nombre"));
                bUsuario.setUsuario(request.getParameter("usuario"));


                String[] abecedario = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                    "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
                String contrasenia = "";
                for (int i = 0; i < 5; i++) {

                    int numRandon = (int) Math.round(Math.random() * 26);
                    System.out.println(numRandon);
                    contrasenia += abecedario[numRandon];
                }

                bUsuario.setContrasenia(contrasenia);
                bUsuario.setEstado(false);
                bUsuario.setRol(2);
                bUsuario.setNum_ent(0);

                /*   System.out.println(bUsuario.getCorreo());
                 System.out.println(bUsuario.getNombre());
                 System.out.println(bUsuario.getUsuario());
                 System.out.println(bUsuario.getContrasenia());*/

                boolean correcto = managerNewUser.registrar(bUsuario);
                if (correcto) {
                    // Propiedades de la conexión
                    Properties props = new Properties();
                    props.setProperty("mail.smtp.host", "smtp.gmail.com");
                    props.setProperty("mail.smtp.starttls.enable", "true");
                    props.setProperty("mail.smtp.port", "587");
                    props.setProperty("mail.smtp.user", "hoyperuanoactivo@gmail.com");
                    props.setProperty("mail.smtp.auth", "true");

                    // Preparamos la sesion
                    javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);

                    // Construimos el mensaje
                    MimeMessage message = new MimeMessage(session);

                    message.setFrom(new InternetAddress("hoyperuanoactivo@gmail.com"));

                    message.addRecipient(
                            javax.mail.Message.RecipientType.TO,
                            new InternetAddress(bUsuario.getCorreo()));
                    message.setSubject("Registro Satisfactorio");
                    message.setText("Sr: " + bUsuario.getNombre()
                            + ", Su Registro se a realizado con satisfaccion, con \n   Usuario: " + bUsuario.getUsuario() + " \n  Password:  " + bUsuario.getContrasenia()
                            + " \n desde hoy podra registar todo  incidente dentro del territorio peruano.");

                    // Lo enviamos.
                    Transport t = session.getTransport("smtp");
                    t.connect("hoyperuanoactivo@gmail.com", "kiaritakiarita");
                    
                    t.sendMessage(message, message.getAllRecipients());

                    // Cierre.
                    t.close();
                    
                    response.sendRedirect("users/confirm_newuser.jsp");
                }


            } else {

                sesion.setAttribute("error_captcha", "Inserte corretamente el codigo de la imagen");
                response.sendRedirect("users/newuser.jsp");
            }


        } catch (AddressException ex) {
            Logger.getLogger(SNewUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(SNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(SNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

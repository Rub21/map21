/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadFile;
import org.apache.commons.fileupload.FileUploadException;
import twitter.bean.BGeometry;
import twitter.bean.BPunto;
import twitter.bean.BPunto_Agujero;
import twitter.datasource.BDConnecion;
import twitter.manager.ManagerPuntos;
import twitter.manager.ManagerPuntos_agujeros;
import twitter.manager.ManagerPuntos_delincuencia;

/**
 *
 * @author ruben
 */
public class SRegistrar_Agujero extends HttpServlet {

    ManagerPuntos_agujeros mp = null;
    ManagerPuntos_agujeros managerP = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileUploadException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //declarte connecion to data base and context
        ServletContext ctx = this.getServletConfig().getServletContext();
        HttpSession sesion = request.getSession();

        BDConnecion conexion = new BDConnecion(ctx);
        BDConnecion con = new BDConnecion(ctx);

        //manager adn bean
        mp = new ManagerPuntos_agujeros(conexion);
        managerP = new ManagerPuntos_agujeros(con);


        UploadBean upBean;
        //clases for upload images
        upBean = new UploadBean();
        String direccion = request.getSession().getServletContext().getRealPath("imagenesDB/");
        upBean.setFolderstore(direccion);
        MultipartFormDataRequest mrequest = new MultipartFormDataRequest(request);
        mrequest.DEFAULTENCODING = "ISO-8859-1";
        java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("yyMMddHHmmss");//fecha        
        Hashtable files = mrequest.getFiles();


        String id = mp.getlast() + "wa";//id=idpunto

        Boolean estado = true;

        System.out.println(id);

        try {
            System.out.println("entra a ");
            BPunto_Agujero bp = new BPunto_Agujero();


            bp.setIdpunto(id);
            
            bp.setTipo(mrequest.getParameter("tipo"));
            
            bp.setIdusuario("1u");
            //bp.setUsuario(sesion.getAttribute("session_usuario").toString());
            bp.setUsuario("Rub21");
            bp.setCalle(mrequest.getParameter("calle"));
                        
            bp.setFecha(mrequest.getParameter("fecha"));
            //bp.setHora(mrequest.getParameter("hora"));

            bp.setDescripcion(mrequest.getParameter("des"));
            bp.setDirec_ref(mrequest.getParameter("direccion"));
            bp.setDe_conocimiento(mrequest.getParameter("de_conocimiento"));


            bp.setReg_de("w");
            bp.setEstado(true);

            BGeometry bGeometry = new BGeometry();
            bGeometry.setLatitud(Double.parseDouble(mrequest.getParameter("lat")));
            bGeometry.setLongitud(Double.parseDouble(mrequest.getParameter("lon")));

            bp.setbGeometry(bGeometry);

            //imagen
            String url_img = "";
            String archivo = ((UploadFile) mrequest.getFiles().get("imagen")).getFileName();
            if (archivo != null) {
                int posicionPunto = archivo.indexOf(".");
                String nombreImagen = archivo.substring(0, posicionPunto);
                nombreImagen = nombreImagen + formato.format(new java.util.Date());
                String extension = archivo.substring(posicionPunto);
                nombreImagen = nombreImagen.replaceAll("\\s", "") + extension;
                ((UploadFile) mrequest.getFiles().get("imagen")).setFileName(nombreImagen);
                UploadFile file = (UploadFile) files.get("imagen");
                upBean.store(mrequest, "imagen");
                //System.out.println(" file names " + file.getFileName());

                url_img = "" + file.getFileName();
            } else {
                url_img = "null";
            }


            bp.setImg(url_img);


            managerP.registrar(bp);

            sesion.setAttribute("conf", "conf");
            response.sendRedirect("reg/confirm.html");

        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
            System.out.println("error" + ex.getMessage());
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (FileUploadException ex) {
            Logger.getLogger(twitter.servlet.SRegistrarPunto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(twitter.servlet.SRegistrarPunto.class.getName()).log(Level.SEVERE, null, ex);
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

            //getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
        } catch (FileUploadException ex) {
            Logger.getLogger(twitter.servlet.SRegistrarPunto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(twitter.servlet.SRegistrarPunto.class.getName()).log(Level.SEVERE, null, ex);
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

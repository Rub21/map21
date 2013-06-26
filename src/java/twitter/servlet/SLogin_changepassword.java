/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import twitter.datasource.BDConnecion;
import twitter.manager.ManagerUsers;

/**
 *
 * @author ruben
 */
public class SLogin_changepassword extends HttpServlet {
    
    ManagerUsers managerUsers = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        
        ServletContext ctx = this.getServletConfig().getServletContext();
        HttpSession sesion = request.getSession();
        BDConnecion conexion = new BDConnecion(ctx);
        managerUsers = new ManagerUsers(conexion);
        
        try {
            
            String usuario = sesion.getAttribute("usuario_first").toString();
            //String oldp = request.getParameter("oldpassword");
            String newp = request.getParameter("newpassword");
            String confp = request.getParameter("confirmpassword");


            // Password Conicide en la Base de Datos.
            //password_first
            
            
            if (newp.equals(confp)) {
                
                boolean confirmacion = managerUsers.rewrite_password(usuario, newp);
                
                if (confirmacion) {
                    
                    sesion.setAttribute("session_usuario", usuario);
                    response.sendRedirect("reg/registrar21.jsp");
                }
            }else{
                
            sesion.setAttribute("error_cofirn_passsword", "Password no coinciden");
            response.sendRedirect("users/change_password.jsp");
            
            }

            //managerUsers.changepassword();

            
            
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
        processRequest(request, response);
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
        processRequest(request, response);
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

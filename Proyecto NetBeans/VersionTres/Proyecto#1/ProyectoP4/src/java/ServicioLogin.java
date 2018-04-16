/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.DAO.ConjuntoOferentes;

/**
 *
 * @author freddycra
 */
public class ServicioLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            // Hay que verificar que la información del servidor
            // especifique la dirección correcta.

            ConjuntoOferentes gUsuarios = ConjuntoOferentes.obtenerInstancia();
            boolean usuarioValido = false;

            String usuario = request.getParameter("usuario");
            String password = request.getParameter("password");

            if (usuario != null && password != null) {
                usuarioValido = gUsuarios.verificarOferente(
                        usuario, password);
            }

            if (usuarioValido) {

                HttpSession sesion = request.getSession(true);
                sesion.setAttribute("usuario", usuario);

                // ** Por completar.

                // Actualiza los datos del usuario actual
                // en la base de datos.
                // En este momento los datos del usuario se han
                // verificado, pero no existe la instancia de
                // la clase del usuario. La instancia existe en
                // el bean, pero no corresponde aún con los datos
                // del nuevo usuario.

                // ------------------------------------------------

                // Fija el tiempo de expiración de la sesión
                // en 3 minutos, independienemente de lo especificado
                // en la configuración de la aplicación.

                sesion.setMaxInactiveInterval(60 * 3);
                request.getRequestDispatcher("Oferente.jsp").forward(
                        request, response);

            } else {

                // Aquí se puede redirigir la página a una diferente
                // dependiendo del tipo de error. Por ejemplo, se puede
                // utilizar una página para solicitar al usuario que
                // se registre en el sitio.

                request.getRequestDispatcher("errorIngreso.jsp?error=2").forward(
                        request, response);
            }

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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

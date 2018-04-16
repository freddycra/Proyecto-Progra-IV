package servicios;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DAO.ConjuntoOferentes;
import modelo.Oferente;

/**
 *
 * @author krist
 */
@WebServlet(name = "RegistroEmpresa", urlPatterns = {"/RegistroEmpresa"})
public class RegistroOferente extends HttpServlet {

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

        try {
            int identificacion = Integer.parseInt(request.getParameter("identificacion"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String nacionalidad = request.getParameter("Nacionalidad");
            String correo = request.getParameter("correo");
            int telefono = Integer.parseInt(request.getParameter("telefono"));
            String direccion = request.getParameter("direccion");
            java.util.Date fecha = new Date();

            Oferente e = new Oferente(identificacion, nombre, apellido, nacionalidad, telefono,correo, direccion, 2);
            ConjuntoOferentes.obtenerInstancia().agregar(e);
        } catch (NumberFormatException ex) {
            //Manejar error
            response.sendRedirect("RegistrarOferente.jsp");
        }
        response.sendRedirect("Ofernte.jsp");
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

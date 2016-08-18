package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class datos extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String nombre = request.getParameter("nombre");
        String apaterno = request.getParameter("apaterno");
        String amaterno = request.getParameter("amaterno");
        String sexo = request.getParameter("sexo");
        String color = request.getParameter("colores");
        HttpSession sesion = request.getSession();
        sesion.setAttribute("nombre", nombre);
        sesion.setAttribute("apaterno", apaterno);
        sesion.setAttribute("amaterno", amaterno);
        sesion.setAttribute("sexo", sexo);
        sesion.setAttribute("color", color);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Datos del formulario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Datos ingresados por el usuario</h1>");
            out.println("<p>Nombre: "+nombre);
            out.println("<br>Apellido paterno: "+apaterno);
            out.println("<br>Apellido materno: "+amaterno);
            out.println("<br>Sexo: "+sexo);
            out.println("<br>Color: "+color);
            out.println("<br>Los datos se agregarán a la sesión</p>");
            out.println("<a href=\"Practica4/sesion\">Mostrar datos de sesión</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}

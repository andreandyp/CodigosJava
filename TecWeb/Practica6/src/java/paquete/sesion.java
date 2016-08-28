package paquete;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class sesion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sesion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Datos de la sesión</h1>");
            out.println("<p>Nombre: "+sesion.getAttribute("nombre"));
            out.println("<br>Apellido paterno: "+sesion.getAttribute("apaterno"));
            out.println("<br>Apellido materno: "+sesion.getAttribute("amaterno"));
            out.println("<br>Sexo: "+sesion.getAttribute("sexo"));
            out.println("<br>Color: "+sesion.getAttribute("color")+"</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
package paquete;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/plain;charset=UTF-8");
                String ruta = getServletContext().getRealPath("/")+request.getParameter("ruta");
        try (PrintWriter out = response.getWriter()) {
            if(checkConforme(ruta))
                out.println("Esta bien conformado");
            else
                out.println("Mal conformado");
            if(checkValido(ruta))
                out.println("Válido");
            else
                out.println("Inválido");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet1 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    public boolean checkConforme(String sURL){
        boolean conforme = false;
        try {
            SAXBuilder builder = new SAXBuilder();
            builder.build(sURL);
            conforme = true;
        } catch (JDOMException | IOException ex) {
            ex.getMessage();
        }
        return conforme;
    }
    public boolean checkValido(String sURL){
        boolean conforme = false;
        try {
            SAXBuilder builder = new SAXBuilder();
            builder.setValidation(true);
            builder.build(sURL);
            conforme = true;
        } catch (JDOMException | IOException ex) {
            ex.getMessage();
        }
        return conforme;
    }
}
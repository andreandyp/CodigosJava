package paquete;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class etiqueta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String etiqueta = request.getParameter("etiqueta");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<script type=\"text/javascript\">");
            out.println("</script>");
            out.println("<title>Etiquetas</title>");            
            out.println("</head>");
            out.println("<body>");
            switch(etiqueta){
                case "fieldset":
                    out.println("<form>");
                    out.println("<fieldset>");
                    out.println("<legend>Ingresar al sistema</legend>");
                    out.println("Correo electrónico: <input type=\"email\"><br>");
                    out.println("Contraseña: <input type=\"password\"><br>");
                    out.println("</fieldset>");
                    out.println("</form>");
                    break;
                case "figcaption":
                    out.println("<p>La bandera del Esperanto es una bandera verde, con un recuadro"
                            + "blanco que tiene dentro de sí una estrella verde que simboliza los 5 continentes</p>");
                    out.println("<figure>");
                    out.println("<img src='esperanto.png' alt='Esperantisto flago'/>");
                    out.println("<figcaption>Bandera esperantista</figcaption>");
                    out.println("</figure>");
                    out.println("El verde simboliza la paz");
                    break;
                case "font":
                    out.println("<p>Ya no se usa font, pero servía para establecer propiedades como el color y el tamaño del texto</p>");
                    break;
                case "footer":
                    out.println("<footer>");
                    out.println("<p>Creado por André Michel");
                    out.println("</footer>");
                    break;
                case "hx":
                    out.println("<h1>Distintos</h1>");
                    out.println("<h2>tamaños</h2>");
                    out.println("<h3>de</h3>");
                    out.println("<h4>encabezado</h4>");
                    out.println("<h5>de</h5>");
                    out.println("<h6>página</h6>");
                    break;
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
}
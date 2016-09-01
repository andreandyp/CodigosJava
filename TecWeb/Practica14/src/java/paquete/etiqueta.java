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
            out.println("<base href=\"https://andreandyp.github.io/imagenes/\" target=\"_blank\">");
            out.println("<title>Etiquetas</title>");            
            out.println("</head>");
            out.println("<body>");
            switch(etiqueta){
                case "base":
                    out.println("<img src=\"fondo.jpg\" alt=\"Mi fondo\">");
                    break;
                case "b":
                    out.println("<b>Antes se usaba para poner en negritas</b><br>");
                    out.println("<strong>Ahora se usa <code>strong</code></strong>");
                    break;
                case "bdo":
                    out.println("<p>Este texto se lee de izquierda a derecha</p>");
                    out.println("<bdo dir=\"rtl\">Este de derecha a izquierda</bdo>");
                    break;
                case "blockquote":
                    out.println("Sirve para...");
                    out.println("<blockquote>...dejar sangría. Con Bootstrap se ve mejor</blockquote>");
                    break;
                case "br":
                    out.println("Un misero<br>salto de página");
                    break;
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

}
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
            out.println("<title>Etiquetas</title>");            
            out.println("</head>");
            out.println("<body>");
            switch(etiqueta){
                case "button":
                    out.println("<button>Dentro de <code>button</code> puedes insertar <strong>codigo</strong><h1> HTML</h1></button>");
                    break;
                case "canvas":
                    out.println(" <canvas width=\"320\" height=\"240\" style=\"border:1px solid #000000;\">\n</canvas> ");
                    break;
                case "caption":
                    out.println("<table border=1>");
                    out.println("<caption>Caption sirve para poner titulo a una tabla</caption>");
                    out.println("<tr><td>Da igual</td><td>el numero</td></tr>");
                    out.println("<tr><td>de</td><td>celdas</td></tr>");
                    out.println("</table>");
                    break;
                case "code":
                    out.println("Creo que <code>code</code> es bastante obvio... pero bueno");
                    out.println("<br><code>console.log(\"Saluton\");</code>");
                    break;
                case "cite":
                    out.println("<p><cite>Unua livro</cite> de Zamenhof contenia los primeros bocetos del esperanto</p>");
                    break;
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

}
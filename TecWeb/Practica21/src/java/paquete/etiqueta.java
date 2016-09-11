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
                case "dd":
                    out.println("<dl>");
                    out.println("<dt>Esperanto</dt>");
                    out.println("<dd>Idioma internacional</dd>");
                    out.println("<dt>Español</dt>");
                    out.println("<dd>2° idioma mas hablado</dd>");
                    out.println("<dt>Náhuatl</dt>");
                    out.println("<dd>Idioma nativo de México</dd>");
                    out.println("</dl>");
                    break;
                case "del":
                    out.println("<p>Tecnologías para la web es una materia <del>horrible</del> <ins>increíble</ins>.</p>");
                    break;
                case "details":
                    out.println("<details>");
                    out.println("Creado por André Michel Pozos<br>");
                    out.println("@AndreAndyP<br>");
                    out.println("<a href='https://andreandyp.github.io'>Sitio web</a>");
                    out.println("</details>");
                    break;
                case "dfn":
                    out.println("<p><dfn>Spark</dfn> es un framework para crear Webapps en Java EE como si fuera Node.js con Express.</p>");
                    break;
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
}
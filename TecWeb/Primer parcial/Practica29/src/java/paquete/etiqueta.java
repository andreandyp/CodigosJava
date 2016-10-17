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
                case "header":
                    out.println("<header>");
                    out.println("<h1>Ayuda a mantener una estructura</h1>");
                    out.println("<h2>Por ejemplo, en un artículo de noticias</h2>");
                    out.println("</header>");
                    break;
                case "hgroup":
                    out.println("<hgroup>");
                    out.println("<h1>Permite definir un título...</h1>");
                    out.println("<h2>...junto con subtítulos...</h2>");
                    out.println("<h3>...y subsubtítulos</h3>");
                    out.println("</hgroup>");
                    break;
                case "hr":
                    out.println("<p>Una línea horizontal... y ya</p>");
                    out.println("<hr>");
                    break;
                case "i":
                    out.println("<i>");
                    out.println("Servía para poner letras en italicas, ahora se usa CSS");
                    out.println("</i>");
                    break;
                case "iframe":
                    out.println("<iframe src='http://andreandyp.github.io'>");
                    out.println("<p>Tu navegador no soporta frames internos");
                    out.println("</iframe>");
                    break;
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
}
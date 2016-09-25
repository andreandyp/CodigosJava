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
                case "div":
                    out.println("<div style='background-color: red; left: 20px; position: fixed;'>Dividir la página</div>");
                    out.println("<div style='background-color: blue; right: 20px; position: fixed;'>en fragmentos</div>");
                    break;
                case "dl":
                    out.println("<dl>");
                    out.println("<dt>Esperanto</dt>");
                    out.println("<dd>Idioma internacional</dd>");
                    out.println("<dt>Español</dt>");
                    out.println("<dd>2° idioma mas hablado</dd>");
                    out.println("<dt>Náhuatl</dt>");
                    out.println("<dd>Idioma nativo de México</dd>");
                    out.println("</dl>");
                    break;
                case "em":
                    out.println("<em>Convetir a cursivas</em>");
                    break;
                case "embed":
                    out.println("<embed src=\"circulos.swf\">");
                    break;
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
}
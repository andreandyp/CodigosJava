package paquete;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class video extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opc = request.getParameter("cancion");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Etiqueta video</title>");            
            out.println("</head>");
            out.println("<body>");
            switch(opc){
                case "opc1":
                    out.println("Reproduciendo: This is what you came for - Calvin Harris ft. Rihanna");
                    out.println("<video width=\"1280\" height=\"720\" controls>\n" +
"                <source src=\"This is what you came for.mp4\" type=\"video/mp4\">\n" +
"                Si ves esto, es porque no conoces HTML5\n" +
"            </video>");
                    break;
                case "opc2":
                    out.println("Reproduciendo: Work from Home - Fifth Harmony");
                    out.println("<video width=\"1280\" height=\"720\" controls>\n" +
"                <source src=\"Work from home.mp4\" type=\"video/mp4\">\n" +
"                Si ves esto, es porque no conoces HTML5\n" +
"            </video>");
                    break;
                case "opc3":
                    out.println("Reproduciendo: Cheap Thrills - Sia ft. Sean Paul");
                    out.println("<video width=\"1280\" height=\"720\" controls>\n" +
"                <source src=\"Cheap Thrills.mp4\" type=\"video/mp4\">\n" +
"                Si ves esto, es porque no conoces HTML5\n" +
"            </video>");
                    break;
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

}

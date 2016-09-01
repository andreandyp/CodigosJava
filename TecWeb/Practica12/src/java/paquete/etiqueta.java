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
                case "abbr":
                    out.println("<p>La <strong><abbr title=\"Federación Mexicana de Esperanto\">FEM</abbr></strong> se encarga de administrar el servicio de pasaporte en México.</p>");
                    break;
                case "address":
                    out.println("<address>\n" +
"                <br>Av. Juan de Dios Bátiz esq. Av. Miguel Othón de Mendizábal\n" +
"                <br>Col. Lindavista. \n" +
"                <br>Gustavo A. Madero.\n" +
"                <br>Ciudad de México\n" +
"                <br>C. P. 07738. \n" +
"            </address>");
                    break;
                case "area":
                    out.println("<img src=\"repos.PNG\" alt=\"Mi repositorios de Github\" usemap=\"#repos\"/>\n" +
"                        <map name=\"repos\">\n" +
"                            <area shape=\"rect\" coords=\"0,0,730,60\" href=\"https://github.com/andreandyp/andreandyp.github.io\" alt=\"Página web\">\n" +
"                            <area shape=\"rect\" coords=\"0,60,730,120\" href=\"https://github.com/andreandyp/CursoMVACSharp\" alt=\"Curso C#\">\n" +
"                            <area shape=\"rect\" coords=\"0,120,730,180\" href=\"https://github.com/andreandyp/CursoMVAJS\" alt=\"Curso JS\">\n" +
"                            <area shape=\"rect\" coords=\"0,180,730,240\" href=\"https://github.com/andreandyp/DesarrolloWindows10UWP\" alt=\"Curso Win10 UWP\">\n" +
"                            <area shape=\"rect\" coords=\"0,240,730,300\" href=\"https://github.com/andreandyp/DiscretasBases\" alt=\"Discretas Bases\">\n" +
"                        </map>");
                    break;
                case "artiside":
                    out.println("<section>\n" +
"                            <article>\n" +
"                                <h1>Twitter Bootstrap</h1>\n" +
"                                <p>Twitter Bootstrap es un framework que usa CSS, Javascript y jQuery para diseñar\n" +
"                                    sitios web fácilmente sin meterse mucho con CSS.</p>\n" +
"                            </article>\n" +
"                        </section>\n" +
"                        <aside>Esperanto versio.</aside>");
                    break;
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

}
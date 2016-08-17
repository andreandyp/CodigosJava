package prueba;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class mostrar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Mostrar cosas mediante un servlet.</title>");            
            out.println("</head>");
            out.println("<body>");
            String cosa = request.getParameter("cosa");
            out.println("<h1>Mostrando "+cosa+"</h1>");
            switch(cosa){
                case "imagen":
                    out.println("<img src=\"pinguino.jpg\" alt=\"Imagen\" />");
                    break;
                case "applet":
                    out.println("<applet code=\"papp\" archive=\"AppJavaEE.jar\" width=\"200\" height=\"100\"></applet>");
                    break;
                case "flash":
                    out.println("<embed src=\"circulos.swf\">");
                    break;
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    /*@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }*/
}

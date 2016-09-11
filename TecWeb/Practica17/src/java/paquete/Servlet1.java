package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
out.println("<frameset cols=\"80,*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"  <frame src=\"http://localhost:8080/Practica17/izq\" name=\"leftFrame\" scrolling=\"NO\" noresize title=\"izquierdo\">\n" +
"  <frame src=\"http://localhost:8080/Practica17/cen\" name=\"mainFrame\" title=\"principal\">\n" +
"</frameset>\n" +
"<noframes><body>\n" +
"</body></noframes>");
            out.println("</html>");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
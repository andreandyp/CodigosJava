package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class audio extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cancion = request.getParameter("cancion");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet audio</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("Reproduciendo: ");
            switch (cancion) {
                case "opc1":
                    out.println("Keep us together - Jeesie J<br>");
                    out.println("<audio controls>");
                    out.println("<source src=\"Keep Us Together.mp3\" type=\"audio/mpeg\"/>");
                    break;
                case "opc2":
                    out.println("Rather be - Clean Bandit ft. Jess Glynne<br>");
                    out.println("<audio controls>");
                    out.println("<source src=\"Rather Be.mp3\" type=\"audio/mpeg\"/>");
                    break;
                case "opc3":
                    out.println("White Flag - Dido<br>");
                    out.println("<audio controls>");
                    out.println("<source src=\"White Flag.mp3\" type=\"audio/mpeg\"/>");
                    break;
            }
            out.println("Si ves esto, quiere decir que tu navegador es viejo");
            out.println("</audio>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}

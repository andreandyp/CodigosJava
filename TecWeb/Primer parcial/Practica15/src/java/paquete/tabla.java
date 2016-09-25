package paquete;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class tabla extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        int columnas = Integer.parseInt(sesion.getAttribute("columnas").toString());
        int filas = Integer.parseInt(sesion.getAttribute("filas").toString());
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet tabla</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<table border=\"1\">");
            for(int i = 1; i <= filas; i++){
                out.println("<tr>");
                for (int j = 1; j <= columnas; j++) {
                    out.println("<td>"+request.getParameter("par"+i+"_"+j)+"</td>");
                }
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}

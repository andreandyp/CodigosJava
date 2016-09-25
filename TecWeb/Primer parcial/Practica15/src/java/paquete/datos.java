
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class datos extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int columnas = Integer.parseInt(request.getParameter("columnas"));
        int filas = Integer.parseInt(request.getParameter("filas"));
        HttpSession sesion = request.getSession();
        sesion.setAttribute("columnas", columnas);
        sesion.setAttribute("filas", filas);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet tabla</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form method=\"post\" action=\"tabla\">");
            for(int i = 1; i <= filas; i++){
                for (int j = 1; j <= columnas; j++) {
                    out.println("<input type=\"text\" name='par"+i+"_"+j+"' placeholder='"+i+j+"'/>");
                }
                out.println("<br>");
            }
            out.println("<input type=\"submit\" value=\"Crear tabla\"/>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
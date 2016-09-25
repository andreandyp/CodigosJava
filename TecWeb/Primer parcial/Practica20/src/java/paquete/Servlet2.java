
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        int enlaces = Integer.parseInt(sesion.getAttribute("enlaces").toString());
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            for(int i = 0; i < enlaces; i++){
                //out.println("<a href='"+request.getParameter("enlace"+i)+"' target='principal'>Enlace "+i+" </a>");
                out.println("<a href='"+request.getParameter("enlace"+i)+"' target='mainframe'>ENLACE1"+i+" </a>");
                out.println("<br>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

}
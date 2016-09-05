
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet0 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int enlaces = Integer.parseInt(request.getParameter("numenlaces"));
        HttpSession sesion = request.getSession();
        sesion.setAttribute("enlaces", enlaces);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet0</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form action=\"Servlet1\" method=\"post\">");
            for(int i = 0; i < enlaces; i++){
                out.println("<input type=\"text\" name='enlace"+i+"' value='http://www.'/>");
                out.println("<br>");
            }
            out.println("<input type=\"submit\" value=\"Hacer frames\"/>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}

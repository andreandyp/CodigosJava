package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class lista extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int elementos = Integer.parseInt(request.getParameter("nelementos"));
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet lista</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<ul>");
            for(int i = 1; i <= elementos; i++){
                out.println("<li>"+request.getParameter("elemento"+i)+"</li>");
            }    
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}


package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class canvas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int numero = Integer.parseInt(request.getParameter("numero"));
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet canvas</title>");
            out.println("<script type=\"text/javascript\" src=\"jquery-2.2.4.min.js\"></script>");
            out.println("<script type=\"text/javascript\">");
            out.println("$(function () {");
            out.println("var canvas = document.getElementById('Dibujos');");
            out.println("var contexto = canvas.getContext('2d');");
            for (int i = 1; i <= numero; i++) {
                out.println("contexto.beginPath();");
                out.println("contexto.arc("+request.getParameter("x"+i)+","+request.getParameter("y"+i)+","+request.getParameter("r"+i)+",0,2*Math.PI);");
                out.println("contexto.stroke();");
            }
            out.println("});");
            out.println("</script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<canvas id='Dibujos' width='1024' height='600'></canvas>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}

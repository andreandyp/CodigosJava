package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Servlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String tem = "",trans = "";
        int tema = (int) (Math.random()*10)+1;
        int transicion = (int) (Math.random()*5)+1;
        switch(tema){
            case 1:
                tem = "white";
                break;
            case 2:
                tem = "solarized";
                break;
            case 3:
                tem = "league";
                break;
            case 4:
                tem = "moon";
                break;
            case 5:
                tem = "sky";
                break;
            case 6:
                tem = "night";
                break;
            case 7:
                tem = "beige";
                break;
            case 8:
                tem = "blood";
            case 9:
                tem = "simple";
                break;
            case 10:
                tem = "serif";
                break;
        }
        switch(transicion){
            case 1:
                trans = "convex";
                break;
            case 2:
                trans = "slide";
                break;
            case 3:
                trans = "concave";
                break;
            case 4:
                trans = "fade";
                break;
            case 5:
                trans = "zoom";
                break;
        }
        response.getWriter().write("<script type=\"text/javascript\">");
        response.getWriter().write("Reveal.configure({"
                +"transition: '"+trans+"',"
                +"theme: '"+tem+ "'});");
        response.getWriter().write("</script>");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

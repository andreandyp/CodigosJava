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
        response.setContentType("text/plain;charset=UTF-8");
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Methods","GET,POST");
        response.addHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        String cosa = request.getParameter("cosa");
        String tem = "",trans = "";
        if(cosa.equals("tema")){
            int tema = (int) (Math.random()*10)+1;
            switch (tema) {
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
            response.getWriter().write(tem);
        }else{
            int transicion = (int) (Math.random() * 5) + 1;
            switch (transicion) {
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
            response.getWriter().write(trans);
        }
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

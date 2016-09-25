
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet1 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        int enlaces = Integer.parseInt(sesion.getAttribute("enlaces").toString());
        String parametros = "";
        for(int i = 0; i < enlaces; i++){
                parametros += "enlace"+i+"="+request.getParameter("enlace"+i)+"&";
            }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");   
            out.println("</head>");
            out.println("<frameset cols='80,*' frameborder='yes' border='1' framespacing='0' >");
            //out.println("<frame src='Servlet2?"+parametros+"'  name='izquierdo' scroling='No' noresize='noresize' title='leftframe' />");
            //out.println("<frame src='frame.html' name='principal' id='principal' title='mainframe' />");
            out.println("<frame src='Servlet2?"+parametros+"'  name='leftframe' scroling='No' noresize='noresize' id='left' title='leftframe' />");
            out.println("<frame src='frame.html' name='mainframe' id='mainframe' title='mainframe' />");
            out.println("</frameset>");
            out.println("<noframes>");
            out.println("<body>");
            out.println("</body>");
            out.println("</noframes>");
            out.println("</html>");
        }
    }

}

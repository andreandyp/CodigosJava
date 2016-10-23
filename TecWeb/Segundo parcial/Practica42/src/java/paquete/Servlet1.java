package paquete;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opciones[] = new String[3];
        opciones = request.getParameterValues("opciones");
        String fondo = "url(";
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servelt1</title>");   
            out.println("</head>");
            out.println("<style type='text/css'>");
            out.println("#prueba{");
            for(String prop : opciones){
                if(prop.equals("fondo")){
                    if(request.getParameter("imagen-f").equals("nimagen"))
                        fondo = "";
                    else
                        fondo += request.getParameter("imagen-f")+".jpg)";
                    out.println("background-color: "+request.getParameter("color-f")+";");
                    out.println("background-image: "+fondo+";");
                    out.println("background-repeat: "+request.getParameter("repimagen-f")+";");
                    out.println("background-position: "+request.getParameter("pos-f")+";");
                }
                if(prop.equals("borde")){
                    out.println("border: "+request.getParameter("tam-b")+"px "+request.getParameter("estilo-b")+" "+request.getParameter("color-b")+";");
                    out.println("border-radius: "+request.getParameter("rad-b")+"px;");
                }
                if(prop.equals("sombra")){
                    out.println("box-shadow: "+request.getParameter("tam-s")+"px "+request.getParameter("tam-s")+"px "+request.getParameter("tam-s")+"px "+request.getParameter("color-s")+";");
                }
            }
            out.println("}");
            out.println("</style>");
            out.println("<body>");
            out.println("<div id='prueba' style='width: 75%; height: 600px; margin: 0 auto;'> ");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
        catch(Exception e){
            System.out.println("Vales verga 7n7 (Enrique lleva hoy mi sudadera, tonto)");
            System.out.println(e.getMessage());
        }
    }
}

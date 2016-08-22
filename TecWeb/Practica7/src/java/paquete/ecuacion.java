package paquete;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class ecuacion extends HttpServlet {
    String x1,x2;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int a = Integer.parseInt(request.getParameter("a"));
        int b = Integer.parseInt(request.getParameter("b"));
        int c = Integer.parseInt(request.getParameter("c"));
        Double determinante = new Double((b*b)-(4*a*c));

        resolver(a,b,c,determinante);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resolver ecuaciones de 2° grado</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Resultados</h1>");
            out.println("x1 = "+x1);
            out.println("<br>");
            out.println("x2 = "+x2);
            out.println("</body>");
            out.println("</html>");
        }
    }
    private void resolver(int a, int b, int c, Double determinante) {
        if(determinante < 0){
            determinante = determinante*-1;
            String real = Double.toString(-b/2*a);
            String imaginario = Double.toString(Math.sqrt(determinante)/(2*a));
            x1 = real+"+"+imaginario+"i";
            x2 = real+"-"+imaginario+"i";
        }
        else{
            x1 = Double.toString((-b+Math.sqrt(determinante))/(2*a));
            x2 = Double.toString((-b-Math.sqrt(determinante))/(2*a));
        }
    }
}
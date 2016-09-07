package paquete;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class etiqueta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String etiqueta = request.getParameter("etiqueta");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<script type=\"text/javascript\">");
            out.println("function save()\n" +
                        "{\n" +
                        "alert(\"Código JS\");\n" +
                        "}");
            out.println("</script>");
            out.println("<title>Etiquetas</title>");            
            out.println("</head>");
            out.println("<body>");
            switch(etiqueta){
                case "colg":
                    out.println("<table border=1>");
                    out.println("<colgroup>");
                    out.println("<col span=\"3\" style=\"background-color: green;\">");
                    out.println("<col style=\"background-color: red;\">");
                    out.println("</colgroup>");
                    out.println("<tr>");
                    out.println("<td>A</td>");
                    out.println("<td>B</td>");
                    out.println("<td>C</td>");
                    out.println("<td>D</td>");
                    out.println("<td>E</td>");
                    out.println("<td>F</td>");
                    out.println("</tr>");
                    out.println("</table>");
                    break;
                case "command":
                    out.println("<menu>");
                    out.println("<command type=\"command\" label=\"Save\" onclick=\"save()\">Save</command>");
                    out.println("</menu>");
                    break;
                case "datalist":
                    out.println("<form method=\"get\">");
                    out.println("<input list=\"idiomas\" name=\"idioma\">");
                    out.println("<datalist id=\"idiomas\">");
                    out.println("<option value=\"Español\">");
                    out.println("<option value=\"Francés\">");
                    out.println("<option value=\"Portugués\">");
                    out.println("<option value=\"Esperanto\">");
                    out.println("<option value=\"Náhuatl\">");
                    out.println("</datalist>");
                    out.println("<input type=\"submit\" value=\"Enviar\">");
                    out.println("</form>");
                    break;
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

}
package paquete;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Element raiz,hijo,nieto;
        raiz = new Element("html");
        hijo = new Element("head");
        nieto = new Element("title");
        nieto.setText("Servlet Servlet1");
        hijo.addContent(nieto);
        nieto = new Element("style");
        nieto.setAttribute("type","text/css");
        nieto.setText("p{"+request.getParameter("reglas")+"}");
        hijo.addContent(nieto);
        raiz.addContent(hijo);
        hijo = new Element("body");
        nieto = new Element("p");
        nieto.setText(request.getParameter("contenido"));
        hijo.addContent(nieto);
        raiz.addContent(hijo);
        
        String ruta = request.getServletContext().getRealPath("/");

        Document newdoc = new Document(raiz);
        XMLOutputter fmt = new XMLOutputter();
        FileWriter writer;
        writer = new FileWriter(ruta+"/archivo.html");
        fmt.output(newdoc, writer);
        writer.flush();
        writer.close();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<a href=\"archivo.html\">Ir al archivo</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
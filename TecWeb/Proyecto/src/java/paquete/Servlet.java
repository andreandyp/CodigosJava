package paquete;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
public class Servlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GenerarJSON</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GenerarJSON at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String a = request.getParameter("a");
        String b = request.getParameter("b");
        String c = request.getParameter("c");
        SAXBuilder builder = new SAXBuilder();
        File xml = new File(getServletContext().getRealPath("/")+"andre.xml");
        try{
            Document document = (Document) builder.build(xml);
            Element raiz = document.getRootElement();
            Element nuevo = new Element("grafica");
            nuevo.setAttribute("disponible","true");
            nuevo.addContent(new Element("a").setText(a));
            nuevo.addContent(new Element("b").setText(b));
            nuevo.addContent(new Element("c").setText(c));
            raiz.addContent(nuevo);
            
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xml.delete();
            FileWriter escritor = new FileWriter(getServletContext().getRealPath("/")+"andre.xml");
            xmlOutput.output(document, escritor);
            escritor.flush();
            escritor.close();
            response.getWriter().write("Guardado");
        }
        catch(JDOMException | IOException ex){
            System.out.println(ex.getMessage());
            response.getWriter().write("Error en el archivo");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

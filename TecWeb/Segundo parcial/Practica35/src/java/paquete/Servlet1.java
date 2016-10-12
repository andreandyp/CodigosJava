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
        raiz = new Element("struts-config");
        hijo = new Element("form-beans");
        raiz.addContent(hijo);
        hijo = new Element("global-exceptions");
        raiz.addContent(hijo);
        hijo = new Element("global-forwards");
        nieto = new Element("forward");
        nieto.setAttribute("name","welcome");
        nieto.setAttribute("path","/Welcome.do/");
        hijo.addContent(nieto);
        raiz.addContent(hijo);
        hijo = new Element("action-mappings");
        nieto = new Element("action");
        nieto.setAttribute("path","/Welcome");
        nieto.setAttribute("forward","/welcomeStruts.jsp/");
        hijo.addContent(nieto);
        raiz.addContent(hijo);
        hijo = new Element("controller");
        hijo.setAttribute("processorClass","org.apache.struts.tiles.TilesRequestProcessor");
        raiz.addContent(hijo);
        hijo = new Element("message-resources");
        hijo.setAttribute("parameter","com/myapp/struts/ApplicationResource");
        raiz.addContent(hijo);
        hijo = new Element("plug-in");
        hijo.setAttribute("className","org.apache.struts.tiles.TilesPlugin");
        nieto = new Element("set-property");
        nieto.setAttribute("property","definitions-config");
        nieto.setAttribute("value","/WEB-INF/tiles-defs.xml");
        hijo.addContent(nieto);
        nieto = new Element("set-property");
        nieto.setAttribute("property","moduleAware");
        nieto.setAttribute("value","true");
        hijo.addContent(nieto);
        raiz.addContent(hijo);
        hijo = new Element("plug-in");
        hijo.setAttribute("className","org.apache.struts.validator.ValidatorPlugIn");
        nieto = new Element("set-property");
        nieto.setAttribute("property","pathnames");
        nieto.setAttribute("value","/WEB-INF/validator-rules.xml,/WEB-INF/validation.xml");
        hijo.addContent(nieto);
        raiz.addContent(hijo);
        
        
        //hoja.setText("Contenido del nodo");

        String ruta = getServletConfig().getServletContext().getRealPath("/");

        Document newdoc = new Document(raiz);
        XMLOutputter fmt = new XMLOutputter();
        FileWriter writer = new FileWriter(ruta+"/xml1.xml");
        
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
            out.println("<h1>XML Struts generado</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
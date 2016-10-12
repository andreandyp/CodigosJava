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
        Element raiz = new Element("raiz");
        Element hoja = new Element("hoja");
        hoja.setAttribute("num", "4");
        hoja.setText("Contenido del nodo");
        raiz.addContent(hoja);

        Document newdoc = new Document(raiz);
        XMLOutputter fmt = new XMLOutputter();
        FileWriter writer = new FileWriter("C:/Users/Public/MalditoXML.xml");
        //File archivoXML = new File("C:/Users/alumno/AppData/Roaming/NetBeans/8.1/config/GF_4.1.1/domain1/config/archivoXML.xml");
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
            out.println("<h1>Maldito XML generado</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}

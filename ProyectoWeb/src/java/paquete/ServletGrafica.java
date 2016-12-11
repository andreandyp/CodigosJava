package paquete;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
@WebServlet("/ServletGrafica")
public class ServletGrafica extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String a = request.getParameter("a");
        String b = request.getParameter("b");
        String c = request.getParameter("c");
        HttpSession sesion = request.getSession();
        String tipo = (String) sesion.getAttribute("tipo");
        Usuario  u = (Usuario) sesion.getAttribute("key");
        String usuario = u.getUser();
        if(!tipo.equals("profesor")){
            response.getWriter().write("No eres un profesor, no puedes guardar el diagrama.");
            return;
        }
        String ruta = getServletContext().getRealPath("diagramas")+"/"+usuario+".xml";
        SAXBuilder builder = new SAXBuilder();
        File xml = new File(ruta);
        try{
            Document document = (Document) builder.build(xml);
            Element raiz = document.getRootElement();
            List lista = raiz.getChildren("grafica");
            Element ultimo = (Element) lista.get(lista.size()-1);
            Element nuevo = new Element("grafica");
            int id = Integer.parseInt(ultimo.getAttributeValue("id"));
            ++id;
            nuevo.setAttribute("id",Integer.toString(id));
            nuevo.setAttribute("disponible","true");
            nuevo.setAttribute("grupo","0");
            nuevo.addContent(new Element("a").setText(a));
            nuevo.addContent(new Element("b").setText(b));
            nuevo.addContent(new Element("c").setText(c));
            raiz.addContent(nuevo);
            
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xml.delete();
            FileWriter escritor = new FileWriter(ruta);
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
        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

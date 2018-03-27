package paquete;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author AndreAndyP
 */
@WebServlet(name = "ProfeDiagramas", urlPatterns = {"/ProfeDiagramas"})
public class ProfeDiagramas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        Usuario  u = (Usuario) sesion.getAttribute("key");
        String usuario = u.getUser();
        String ruta = getServletContext().getRealPath("diagramas")+"/"+usuario+".xml";
        SAXBuilder builder = new SAXBuilder();
        File xml = new File(ruta);
        try {
            StringBuilder json = new StringBuilder("{");
            response.setContentType("text/json");
            Document document = (Document) builder.build(xml);
            Element raiz = document.getRootElement();
            List lista = raiz.getChildren("grafica");
            Element nodo;
            for (int i = 1; i < lista.size(); i++) {
                nodo = (Element) lista.get(i);
                if(nodo.getAttribute("disponible").getValue().equals("true")){
                    json.append("'id':'").append(nodo.getAttributeValue("id")).append("',");
                    json.append("{").append("'a':'").append(nodo.getChild("a").getText()).append("',");
                    json.append("'b':'").append(nodo.getChild("b").getText()).append("',");
                    json.append("'c':'").append(nodo.getChild("c").getText()).append("'},");
                }
            }
            json.append("}");
            response.getWriter().print(json);
        } catch (JDOMException ex) {
            response.getWriter().write(ex.getMessage());
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}

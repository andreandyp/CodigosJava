package controlador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


@WebServlet("/Servlet_Administrador_Erase")
public class Servlet_Administrador_Erase extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		String id= request.getParameter("id");
		PrintWriter out= response.getWriter();
		
		try {
			SAXBuilder builder = new SAXBuilder();
			File archivoXML = new File(request.getServletContext().getRealPath("/")+"xml_login.xml");
			Document documento=builder.build(archivoXML);
			Element raiz = documento.getRootElement();
			List<Element> nodos= raiz.getChildren();
			
			for (int i = 0; i < nodos.size(); i++) {
				Element elemento= nodos.get(i);
				
				if(elemento.getAttributeValue("id").equals(id)){
					out.print("usuario eliminado"+elemento.getAttributeValue("id"));
					raiz.removeContent(elemento);
				}
			}
			
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(documento, new FileWriter(archivoXML));
			
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}

}

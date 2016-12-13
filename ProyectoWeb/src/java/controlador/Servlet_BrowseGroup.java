package controlador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.Element;
import org.jdom.JDOMException;



@WebServlet("/Servlet_BrowseGroup")
public class Servlet_BrowseGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String cadena=request.getParameter("parametros");
		
		StringTokenizer st = new StringTokenizer(cadena,",;",false);
		String []parametros=new String[st.countTokens()];
		int i=0;
		while (st.hasMoreTokens()) {  
			parametros[i++]=st.nextToken();
		}
		
		try {
			SAXBuilder builder=new SAXBuilder();
			File archivoXML = new File(request.getServletContext().getRealPath("/")+"xmlgrupos.xml");
			Document documento=builder.build(archivoXML);
			Element raiz = documento.getRootElement();
			List<Element> nodos= raiz.getChildren();
			boolean existegrupo=false;
			
			if (nodos.size()== 0) {
				Element grupo=new Element("n_grupo");
				grupo.setAttribute(new Attribute("alumnos", parametros[1]));
				grupo.setAttribute(new Attribute("nombregrupo", parametros[0]));
				raiz.addContent(grupo);
				XMLOutputter xmlOutput = new XMLOutputter();
				xmlOutput.setFormat(Format.getPrettyFormat());
				FileWriter x = new FileWriter(archivoXML);
			xmlOutput.output(documento, x);
                        x.flush();
                        x.close();
				out.print("grupo agregado");
				
			}
			else{
				for (int j = 0; j < nodos.size() ; j++) {
					Element elementos=(Element) nodos.get(j);
					if(elementos.getAttributeValue("nombregrupo").equals(parametros[0])){
						existegrupo= true;
					}
				}
				
				if (!existegrupo) {
					Element grupo=new Element("n_grupo");
					grupo.setAttribute(new Attribute("alumnos", parametros[1]));
					grupo.setAttribute(new Attribute("nombregrupo", parametros[0]));
					raiz.addContent(grupo);
					XMLOutputter xmlOutput = new XMLOutputter();
					xmlOutput.setFormat(Format.getPrettyFormat());
					FileWriter x = new FileWriter(archivoXML);
			xmlOutput.output(documento, x);
                        x.flush();
                        x.close();
					out.print("grupo agregado");
				}
				
				else {
					out.print("el grupo ya existe");
				}
			}
			
		}catch (JDOMException e) {
			e.printStackTrace();
		}
		
		
	}

}

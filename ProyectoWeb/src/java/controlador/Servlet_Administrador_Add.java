package controlador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.List;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;



@WebServlet("/Servlet_Administrador_Add")
public class Servlet_Administrador_Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String cadena = request.getParameter("parametros");
		String tipo= request.getParameter("tipo");
		StringTokenizer st = new StringTokenizer(cadena,",;",false);
		String []parametros=new String[st.countTokens()];
		int i=0;
		while (st.hasMoreTokens()) {  
			parametros[i++]=st.nextToken();
		}
		
		Usuario nombreuser= new Usuario(parametros[1], parametros[3]);
		boolean correo1=false, usuario1=false;
		
		
		try {
			SAXBuilder builder=new SAXBuilder();
			File archivoXML = new File(request.getServletContext().getRealPath("/")+"xml_login.xml");
			Document documento=builder.build(archivoXML);
			Element raiz = documento.getRootElement();
			List<Element> nodos= raiz.getChildren("usuario");
			for (int j = 0; j < nodos.size() ; j++) {
				Element elementos=(Element) nodos.get(j);
				if (nombreuser.existeUsuario(elementos.getChildText("n_usuario"))) {
					usuario1=true;
				}
				
				if (elementos.getChildText("correo").equals(parametros[2])) {
					correo1= true;
				}
			}
			
			if (usuario1 && correo1) {
				out.print("si existe");
			}
			
			if (usuario1 && correo1==false) {
				out.print("si existe");
			}
			
			if (usuario1==false && correo1) {
				out.print("existe correo");
			}

			if (usuario1==false && correo1==false) {

				Element finalnode= nodos.get(nodos.size()-1);
				int id= Integer.parseInt(finalnode.getAttributeValue("id"))+1;
				Element usuario= new Element("usuario");
				usuario.setAttribute(new Attribute("id", Integer.toString(id)));
				usuario.setAttribute(new Attribute("tipo",tipo));
					Element nombre=new Element("nombre");
							nombre.setText(parametros[0]);
					Element nomusr=new Element("n_usuario");
							nomusr.setText(parametros[1]);
					Element correo=new Element("correo");
							correo.setText(parametros[2]);
					Element pass=new Element("pass");
							pass.setText(parametros[3]);
				usuario.addContent(nombre);
				usuario.addContent(correo);
				usuario.addContent(nomusr);
				usuario.addContent(pass);
				
				raiz.addContent(usuario);
				XMLOutputter xmlOutput = new XMLOutputter();
				xmlOutput.setFormat(Format.getPrettyFormat());
                                FileWriter archivo = new FileWriter(archivoXML);
				xmlOutput.output(documento, archivo);
                                archivo.flush();
                                archivo.close();
				out.print("usuario agregado");
                                if(tipo.equals("profesor")){
                                    Diagramas d = new Diagramas(parametros[1]);
                                    d.crearArchivo(request.getServletContext().getRealPath("diagramas")+"/");
                                }
                                
			}
			
			
		} catch (JDOMException e){
			e.printStackTrace();
		}
		
		
	}

}

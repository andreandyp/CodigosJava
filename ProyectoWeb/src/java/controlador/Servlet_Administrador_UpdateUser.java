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

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import modelo.Usuario;

@WebServlet("/Servlet_Administrador_UpdateUser")
public class Servlet_Administrador_UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cadena = request.getParameter("parametros");
		StringTokenizer st = new StringTokenizer(cadena,",;",false);
		String []parametros=new String[st.countTokens()];
		int i=0;
		while (st.hasMoreTokens()) {  
			parametros[i++]=st.nextToken();
		}
		
		String id= request.getParameter("id");
		String tipo=request.getParameter("tipo");
		response.setContentType("ext/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		Usuario user= new Usuario(parametros[1], parametros[3]);
		boolean usuario1=false, correo1=false;
		
		try{
			SAXBuilder builder=new SAXBuilder();
			File archivoXML = new File(request.getServletContext().getRealPath("/")+"xml_login.xml");
			Document documento=builder.build(archivoXML);
			Element raiz = documento.getRootElement();
			List<Element> nodos= raiz.getChildren("usuario");
			for (int j = 0; j < nodos.size(); j++) {
				Element elementos=(Element) nodos.get(j);
				if (!elementos.getAttributeValue("id").equals(id)) {
					if (user.existeUsuario((elementos.getChildText("n_usuario")))) {
						usuario1= true;
					}
					if (elementos.getChildText("correo").equals(parametros[2])) {
						correo1= true;
					}
					
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

			if (usuario1==false && correo1==false){
				Element elemento=  nodos.get(Integer.parseInt(id)-1);
				elemento.getChild("nombre").setText(parametros[0]);
				elemento.getAttribute("tipo").setValue(tipo);
				elemento.getChild("n_usuario").setText(parametros[1]);
				elemento.getChild("correo").setText(parametros[2]);
				elemento.getChild("pass").setText(parametros[3]);
				out.print("editado");
				XMLOutputter xmlOutput = new XMLOutputter();
				xmlOutput.setFormat(Format.getPrettyFormat());
				FileWriter x = new FileWriter(archivoXML);
			xmlOutput.output(documento, x);
                        x.flush();
                        x.close();
			}
			
			
			
			
			
		} catch (JDOMException e){
			e.printStackTrace();
		}
	}

}

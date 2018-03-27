package controlador;

import java.io.File;
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


@WebServlet("/Servlet_Administrador_Read")
public class Servlet_Administrador_Read extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String tabla;
		
		try{
			SAXBuilder builder=new SAXBuilder();
			File archivoXML = new File(request.getServletContext().getRealPath("/")+"xml_login.xml");
			Document documento=builder.build(archivoXML);
			 Element raiz=documento.getRootElement();
			 List<Element> nodos= raiz.getChildren();
			 tabla="<table style=\"border:1px solid black; \">\n";
			 tabla += "<tr><th>Nombre</th><th>Nombre de Usuario</th><th>Correo</th><th>Contraseña</th><th>Tipo</th></tr>\n";
			 for (int i = 0; i < nodos.size() ; i++) {
				 Element elemento= nodos.get(i);
				 tabla+="<tr><td style=\"border:1px solid black; \">";
				 tabla+=elemento.getChild("nombre").getText(); 
				 tabla+="</td>";
				 tabla+="<td style=\"border:1px solid black; \">"+elemento.getChild("n_usuario").getText()+"</td>";
				 tabla+="<td style=\"border:1px solid black; \">"+elemento.getChild("correo").getText()+"</td>";
				 tabla+="<td style=\"border:1px solid black; \">"+elemento.getChild("pass").getText()+"</td>";
				 tabla+="<td style=\"border:1px solid black; \">"+elemento.getAttributeValue("tipo")+"</td></tr>\n";
			}
			 tabla+="</table>";
			 out.print(tabla);
           
        }catch(JDOMException e){
            e.printStackTrace();
        }
	
	}
	



}

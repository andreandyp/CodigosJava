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



@WebServlet("/Servlet_Administrador_EditUser")
public class Servlet_Administrador_EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id= request.getParameter("id");
		String tabla="<table style=\"border:1px solid black; \">\n";
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		try {
			SAXBuilder builder=new SAXBuilder();
			File archivoXML = new File(request.getServletContext().getRealPath("/")+"xml_login.xml");
			Document documento=builder.build(archivoXML);
			Element raiz = documento.getRootElement();
			List<Element> nodos= raiz.getChildren();
			for (int i = 0; i < nodos.size() ; i++) {
				Element elemento= nodos.get(i);
				if (elemento.getAttributeValue("id").equals(id)) {
					tabla += "<tr><th>Editar Usuario</th><th>"+elemento.getChildText("n_usuario")+"</th></tr>";
					tabla+="<tr><td style=\"border:1px solid black; \">Nombre</td>";
					tabla+="<td style=\"border:1px solid black;\"><input type=\"text\" name='valid' value=\""+elemento.getChildText("nombre")+"\"></td><td><span name='validacion' class='alerta'></span></td></tr>\n";
					tabla+="<tr><td>Nombre de Usuario</td><td style=\"border:1px solid black;\"><input type=\"text\" name='valid' value=\""+elemento.getChildText("n_usuario")+"\" ><span name='validacion' class='alerta'></span></td></tr>\n";
					tabla+="<tr><td>Correo</td><td style=\"border:1px solid black;\"><input type=\"text\" name='valid'value=\""+elemento.getChildText("correo")+"\"><span name='validacion' class='alerta'></span></td></tr>\n";
					tabla+="<tr><td>Contraseña</td><td style=\"border:1px solid black;\"><input type=\"text\" name='valid' value=\""+elemento.getChildText("pass")+"\"><span name='validacion' class='alerta'></span></td></tr>\n";
					tabla+="<tr><td><b>Tipo de Usuario</b></td></tr>\n";
					tabla+="<tr><td><input type='radio' value='administrador' name='tipo' checked>Administrador</td></tr>\n";
					tabla+="<tr><td><input type='radio' value='profesor' name='tipo'>Profesor</td></tr>\n";
					tabla+="<tr><td><input type='radio' value='estudiante' name='tipo' checked>Estudiante</td></tr>\n";
					tabla+="<tr><td><button type=\"button\" onclick=\"actualizarUsuario("+id+")\"> Actualizar Usuario </button></td>";
					tabla+="<td><a href=\"javascript:editar()\">Regresar</a></td></tr>\n";
				}
			}
			 tabla+="</table>";
			
		} catch (JDOMException e){
			e.printStackTrace();
		}
		out.println(tabla);
		
}
}
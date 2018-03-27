package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import modelo.Usuario;



@WebServlet("/Servlet_login")
public class Servlet_login extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out=response.getWriter();
		String user=request.getParameter("user");
		String password=request.getParameter("pass");
		Usuario usuario_java = new Usuario(user, password);
		boolean compruebausuario=false,compruebapass= false;
		String tipousr="";
		
		try {
			
			SAXBuilder builder = new SAXBuilder();
			File archivoXML = new File(request.getServletContext().getRealPath("/")+"xml_login.xml");
			Document documento=builder.build(archivoXML);
			
			Element raiz = documento.getRootElement();
			List lista= raiz.getChildren("usuario");
			 for(int i=0;i<lista.size();i++)
             {
              Element usuario=(Element)lista.get(i);
              Attribute atributousuario =  usuario.getAttribute("tipo");
              tipousr=atributousuario.getValue();
              if(compruebausuario= usuario_java.existeUsuario(usuario.getChildText("n_usuario"))){
				compruebapass = usuario_java.compruebaPass(usuario.getChildText("pass")); 
					break;
				}
              
             }
			
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		
		
		if (compruebausuario && compruebapass) {
			out.print("Bienvenido "+tipousr);
			HttpSession session= request.getSession();
			session.setAttribute("key", usuario_java);
			session.setAttribute("key1", tipousr);
                        session.setAttribute("tipo", tipousr);
		}
		
		else if(compruebausuario && (compruebapass==false))
			out.print("Contraseña invalida");
		
		else {
			out.print("Usuario Invalido");
		}
		
	
	}

}

package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;


@WebServlet("/Servlet_Get_User")
public class Servlet_Get_User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out= response.getWriter();
		try{
			HttpSession session= request.getSession();
			Usuario usr =(Usuario) session.getAttribute("key");
			String tipo= (String)session.getAttribute("key1");
			out.print(tipo+" ");
			out.print(usr.getUser());
		}
		
		catch (NullPointerException e) {
			out.print("Error: "+e.getMessage());
		}
		
		
	}

	

}

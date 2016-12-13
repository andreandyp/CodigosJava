package modelo;

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


@WebServlet("/Servlet_Administrador_ReadGrupos")
public class Servlet_Administrador_ReadGrupos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String tabla;
		
		try{
			SAXBuilder builder=new SAXBuilder();
			File archivoXML = new File(request.getServletContext().getRealPath("/")+"xmlgrupos.xml");
			Document documento=builder.build(archivoXML);
			 Element raiz=documento.getRootElement();
			 List<Element> nodos= raiz.getChildren();
			 tabla="<table style=\"border:1px solid black; \">\n";
			 tabla += "<tr><th>Nombre Grupo</th><th>Alumnos inscritos</th></tr>\n";
			 if(nodos.size()!=0){
				 for (int i = 0; i < nodos.size() ; i++) {
					 Element elemento= nodos.get(i);
					 tabla+="<tr><td style=\"border:1px solid black; \">";
					 tabla+=elemento.getAttributeValue("nombregrupo"); 
					 tabla+="</td>";
					 tabla+="<td style=\"border:1px solid black; \">"+elemento.getAttributeValue("alumnos")+"</td>";
	
				}
				 tabla+="</table>";
				 out.print(tabla);
			 }
			 
           
        }catch(JDOMException e){
            e.printStackTrace();
        }
		
	}

}

package paquete;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            String uRequest,cRequest;
            uRequest = request.getParameter("usuario");
            cRequest = request.getParameter("clave");
            
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(getServletContext().getRealPath("/")+"/datos.json"));
            JSONObject jsonObject = (JSONObject) obj;
            
            JSONArray msg = (JSONArray) jsonObject.get("admins");
            Iterator iterator = msg.iterator();
            JSONObject usuarioobj;
            String usuario,clave;
                
		while (iterator.hasNext()) {
                    usuarioobj = (JSONObject) iterator.next();
                    usuario = (String) usuarioobj.get("usuario");
                    clave = (String) usuarioobj.get("clave");
                    if(usuario.equals(uRequest) && clave.equals(cRequest)){
                        out.println("Datos correctos");
                        break;
                    }
                    System.out.println("hue");
                }
                out.println("Datos incorrectos");
        }catch (ParseException ex) {
            response.sendError(500,"Error en el JSON");
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
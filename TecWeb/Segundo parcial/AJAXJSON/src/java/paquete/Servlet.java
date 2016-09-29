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
            String usuarioR,claveR,usuarioA,claveA,usuarioU,claveU,resultado = "";
            JSONObject adm,usu,archivoJSON;
            JSONArray admins,usuarios;
            Iterator iter1,iter2;
            usuarioR = request.getParameter("usuario");
            claveR = request.getParameter("clave");
            
            JSONParser parser = new JSONParser();
            Object json = parser.parse(new FileReader(getServletContext().getRealPath("/")+"/datos.json"));
            archivoJSON = (JSONObject) json;
            admins = (JSONArray) archivoJSON.get("admins");
            usuarios = (JSONArray) archivoJSON.get("usuarios");
            iter1 = admins.iterator();
            iter2 = usuarios.iterator();
            
            while (iter1.hasNext() && iter2.hasNext()) {
                adm = (JSONObject) iter1.next();
                usu = (JSONObject) iter2.next();
                usuarioA = (String) adm.get("usuario");
                claveA = (String) adm.get("clave");
                usuarioU = (String) usu.get("usuario");
                claveU = (String) usu.get("clave");
                if((usuarioA.equals(usuarioR) && claveA.equals(claveR)) || (usuarioU.equals(usuarioR) && claveU.equals(claveR))){
                    resultado = "Datos correctos";
                    break;
                }else
                    resultado = "Datos incorrectos";
            }
            out.println(resultado);
            
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
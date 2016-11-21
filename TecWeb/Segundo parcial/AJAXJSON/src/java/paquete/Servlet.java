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
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            String usuarioR,claveR,usuarioU,claveU,tipoU,respuesta = "";
            JSONArray sistema;
            JSONObject usu;
            Iterator iter1;
            usuarioR = request.getParameter("usuario");
            claveR = request.getParameter("clave");
            
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(new FileReader(getServletContext().getRealPath("/")+"/datos.json"));
            sistema = (JSONArray) json.get("sistema");
            iter1 = sistema.iterator();
            
            while (iter1.hasNext()) {
                usu = (JSONObject) iter1.next();
                usuarioU = (String) usu.get("usuario");
                claveU = (String) usu.get("clave");
                tipoU = (String) usu.get("tipo");
                if((usuarioU.equals(usuarioR) && claveU.equals(claveR))){
                    respuesta = tipoU;
                    break;
                }else{
                    respuesta = "";
                }
            }
            response.getWriter().write(respuesta); 
        }catch (IOException | ParseException ex) {
            response.getWriter().write("null"); 
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
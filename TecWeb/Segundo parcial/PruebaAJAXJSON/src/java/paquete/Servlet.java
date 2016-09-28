package paquete;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(getServletContext().getRealPath("/")+"/datos.json"));
            JSONObject jsonObject = (JSONObject) obj;
            String uRequest,uJSON,cRequest,cJSON;
            uRequest = request.getParameter("usuario");
            uJSON = (String) jsonObject.get("usuario");
            cRequest = request.getParameter("clave");
            cJSON = (String) jsonObject.get("clave");
            if(uRequest.equals(uJSON) && cRequest.equals(cJSON)){
                out.println("Datos correctos");
            }
            else{
                response.sendError(500, "Datos incorrectos");
            }
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
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
public class OperacionesABCC extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OperacionesABCC</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OperacionesABCC at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operacion = request.getParameter("operacion");
        String usuario = request.getParameter("usuario");
        switch(operacion){
            case "consulta":
                consulta(usuario,request,response);
                break;
            case "cambios":
                String nClave = request.getParameter("nClave");
                cambios(usuario,nClave,request,response);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void consulta(String usuario,HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        try{
            String usuarioU,claveU,tipoU,respuesta = "";
            JSONArray sistema;
            JSONObject usu;
            Iterator iter1;
            
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(new FileReader(getServletContext().getRealPath("/")+"/datos.json"));
            sistema = (JSONArray) json.get("sistema");
            iter1 = sistema.iterator();
            
            while (iter1.hasNext()) {
                usu = (JSONObject) iter1.next();
                usuarioU = (String) usu.get("usuario");
                claveU = (String) usu.get("clave");
                tipoU = (String) usu.get("tipo");
                if(usuarioU.equals(usuario)){
                    respuesta = "{\"clave\":\""+claveU+"\",\"tipo\":\""+tipoU+"\"}";
                    break;
                }else{
                    respuesta = "";
                }
            }
            response.getWriter().write(respuesta);
        }
        catch(IOException | ParseException e){
            response.getWriter().write("error");
        }
    }

    private void cambios(String usuario, String nClave, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/plain;charset=UTF-8");
        try{
            String usuarioU,claveU,tipoU,respuesta = "";
            JSONArray sistema;
            JSONObject usu,nuevo;
            Iterator iter1;
            
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(new FileReader(getServletContext().getRealPath("/")+"/datos.json"));
            sistema = (JSONArray) json.get("sistema");
            iter1 = sistema.iterator();
            
            while (iter1.hasNext()) {
                usu = (JSONObject) iter1.next();
                usuarioU = (String) usu.get("usuario");
                if(usuarioU.equals(usuario)){
                    nuevo = new JSONObject();
                    nuevo.put("clave", nClave);
                    
                    respuesta = "Cambio exitoso";
                    break;
                }else{
                    respuesta = "";
                }
            }
            response.getWriter().write(respuesta);
        }
        catch(IOException | ParseException e){
            response.getWriter().write("error");
        }
    }

}

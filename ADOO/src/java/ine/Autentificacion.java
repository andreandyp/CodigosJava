package ine;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class Autentificacion extends HttpServlet {
    Votante vot;
    HttpSession sesion;
    BaseINE base;
    
    Autentificacion(){
        base = new BaseINE();
        base.abrirConexion();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clave = request.getParameter("clave");
        boolean validado = Boolean.parseBoolean(request.getParameter("valido"));
        response.setContentType("text/plain;charset=UTF-8");
        boolean existe = false,huella;
        if(validado){
            existe = iniciarConClave(clave,request,response);
        }else{
            huella = iniciarConHuella(clave,request,response);
        }
        response.getWriter().write(Boolean.toString(existe));
    }
    
    private boolean iniciarConClave(String clave, HttpServletRequest request, HttpServletResponse response){
        
        //Aquí va la clave
        //Bien "hardcodeado"
        
        return true;
    }

    private boolean iniciarConHuella(String clave, HttpServletRequest request, HttpServletResponse response) {
        sesion = request.getSession(true);
        sesion.setAttribute("claveElec", clave);
        return true;
    }

}
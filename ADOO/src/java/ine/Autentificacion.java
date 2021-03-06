package ine;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class Autentificacion extends HttpServlet {
    Votante vot;
    HttpSession sesion;
    BaseINE base;
    
    public Autentificacion(){
        base = new BaseINE();
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
        boolean existe = false;
        if(!validado){
            existe = iniciarConClave(clave,request,response);
        }else{
            existe = iniciarConHuella(clave,request,response);
        }
        response.getWriter().write(Boolean.toString(existe));
    }
    
    private boolean iniciarConClave(String clave, HttpServletRequest request, HttpServletResponse response){
        try {
            base.abrirConexion();
            if((vot = base.buscarClave(clave)) != null){
                base.cerrarConexion();
                return true;
            }else{
                base.cerrarConexion();
                return false;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    private boolean iniciarConHuella(String clave, HttpServletRequest request, HttpServletResponse response) {
        sesion = request.getSession(true);
        sesion.setAttribute("Usuario", vot);
        return true;
    }

}
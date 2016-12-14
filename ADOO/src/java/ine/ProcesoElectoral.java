package ine;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class ProcesoElectoral extends HttpServlet {
    CasillaINE cINE;
    BoletaElectoral [] boletas;
    public ProcesoElectoral(){
        boletas = new BoletaElectoral[3];
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String tipo = ((Votante)sesion.getAttribute("Usuario")).getCasilla();
        cINE = new CasillaINE(tipo);
        cINE.crearBoletas();
    }


}

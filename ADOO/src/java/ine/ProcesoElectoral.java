package ine;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class ProcesoElectoral extends HttpServlet {
    private CasillaINE cINE;
    private BoletaElectoral be;
    private int numVotos;
    
    public ProcesoElectoral(){
        numVotos = 0;
        cINE = new CasillaINE();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;UTF-8");
        StringBuilder json = new StringBuilder("{");
        HttpSession sesion = request.getSession();
        switch(numVotos){
            case 0:
                json.append("\"Eleccion\":\"Presidente\",");
                json.append("\"PRI\":\"Enrique Peña Nieto\",");
                json.append("\"PAN\":\"Josefin Vazquez Mota\",");
                json.append("\"PRD\":\"Andrés Manuel López Obrador\",");
                json.append("\"MORENA\":\"Martí Batres\",");
                json.append("\"MC\":\"Andrés Manuel López Obrador\",");
                json.append("\"PT\":\"Andrés Manuel López Obrador\",");
                json.append("\"PVEM\":\"Enrique Peña Nieto\",");
                json.append("\"PANAL\":\"Gabriel Quadri\",");
                json.append("\"PH\":\"Margarita Zavala\",");
                json.append("\"PES\":\"Sergio Sotelo Félix\"}");
                break;
            case 1:
                json.append("\"Eleccion\":\"Jefe de Gobierno del Distrito Federal\",");
                json.append("\"PRI\":\"Beatriz Paredes Rangel\",");
                json.append("\"PAN\":\"Isabel Miranda de Wallace\",");
                json.append("\"PRD\":\"Miguel Ángel Mancera\",");
                json.append("\"MORENA\":\"Martí Batres\",");
                json.append("\"MC\":\"Miguel Ángel Mancera\",");
                json.append("\"PT\":\"Miguel Ángel Mancera\",");
                json.append("\"PVEM\":\"Beatriz Paredes Rangel\",");
                json.append("\"PANAL\":\"Rosario Guerra Díaz\",");
                json.append("\"PH\":\"Ricardo Anaya\",");
                json.append("\"PES\":\"Marta Sahagún\"}");
                break;
            case 2:
                json.append("\"Eleccion\":\"Diputados\",");
                json.append("\"PRI\":\"Manlio Fabio Beltrones\",");
                json.append("\"PAN\":\"Santiago Creel\",");
                json.append("\"PRD\":\"Jesus Zambrano\",");
                json.append("\"MORENA\":\"Manuel Barlett\",");
                json.append("\"MC\":\"Alejandra Barrales\",");
                json.append("\"PT\":\"Alejandra Barrales\",");
                json.append("\"PVEM\":\"Manuel Velasco\",");
                json.append("\"PANAL\":\"Elba Esther Gordillo\",");
                json.append("\"PH\":\"Guillermo Padrés\",");
                json.append("\"PES\":\"Hector Suárez\"}");
                break;
            case 3:
                ((Votante)sesion.getAttribute("Usuario")).setVotoEmitido(true);
                response.getWriter().write("true");
                return;
        }
        ((Votante)sesion.getAttribute("Usuario")).setNumVotos(numVotos);
        response.getWriter().write(json.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        if(((Votante)sesion.getAttribute("Usuario")).isVotoEmitido()){
            BaseProceso bp = new BaseProceso();
            try{
                bp.abrirConexion();
                bp.subirVotos(cINE);
                bp.cerrarConexion();
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            return;
        }
        String tipo = ((Votante)sesion.getAttribute("Usuario")).getCasilla();
        if(cINE.getTipo() == null){
            cINE.setTipo(tipo);
        }
        String partido = request.getParameter("partido");
        String candidato = request.getParameter("candidato");
        be = new BoletaElectoral(0,partido,candidato);
        cINE.emitirVoto(be);
        numVotos = cINE.getNumVoto();
        ((Votante)sesion.getAttribute("Usuario")).setNumVotos(numVotos);
    }


}

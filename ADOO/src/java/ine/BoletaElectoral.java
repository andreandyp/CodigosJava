package ine;
public class BoletaElectoral {
    private int claveBoleta;
    private final String partido;
    private final String candidato;

    public BoletaElectoral(int claveBoleta, String partido, String candidato) {
        this.claveBoleta = claveBoleta;
        this.partido = partido;
        this.candidato = candidato;
    }

    public int getClaveBoleta() {
        return claveBoleta;
    }

    public String getPartido() {
        return partido;
    }

    public String getCandidato() {
        return candidato;
    }

    public void setClaveBoleta(int claveBoleta) {
        this.claveBoleta = claveBoleta;
    }
    
}

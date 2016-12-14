package ine;
public class Votante {
    private final String claveElec;
    private final char sexo;
    private final String nombre;
    private final int estado;
    private final int seccion;
    private String casilla;
    private boolean votoEmitido;

    public Votante(String claveElec, String nombre, char sexo, int estado, int seccion) {
        this.claveElec = claveElec;
        this.nombre = nombre;
        this.sexo = sexo;
        this.estado = estado;
        this.seccion = seccion;
        obtenerCasilla();
    }

    public String getClaveElec() {
        return claveElec;
    }

    public char getSexo() {
        return sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEstado() {
        return estado;
    }

    public int getSeccion() {
        return seccion;
    }

    public boolean isVotoEmitido() {
        return votoEmitido;
    }

    public String getCasilla() {
        return casilla;
    }

    private void obtenerCasilla() {
        switch(this.estado){
            case 1:
            case 2:
            case 8:
            case 9:
            case 10:
            case 13:
            case 20:
            case 21:
            case 23:
            case 25:
            case 28:
            case 29:
            case 30:
            case 32:
                String [] nombres = this.nombre.split(" ");
                char inicial = nombres[nombres.length - 2].charAt(0);
                this.casilla = (inicial >= 97 && inicial <= 101)?"Básica":
                               (inicial >= 102 && inicial <= 106)?"Contigua 1":
                               (inicial >= 107 && inicial <= 111)?"Contigua 3":
                               (inicial >= 112 && inicial <= 116)?"Contigua 4":"Contigua 5";
                break;
            default:
                this.casilla = "Básica";
        }
    }
    
}

package ine;
public class Votante {
    private final String nombre;
    private final String claveElec;
    private final int estado;
    private final int seccion;
    private boolean votoEmitido;

    public Votante(String nombre, String claveElec, int estado, int seccion) {
        this.nombre = nombre;
        this.claveElec = claveElec;
        this.estado = estado;
        this.seccion = seccion;
    }
}

package ine;
public class Votante {
    private final String claveElec;
    private final char sexo;
    private final String nombre;
    private final int estado;
    private final int seccion;
    private boolean votoEmitido;

    public Votante(String claveElec, char sexo, String nombre, int estado, int seccion) {
        this.claveElec = claveElec;
        this.sexo = sexo;
        this.nombre = nombre;
        this.estado = estado;
        this.seccion = seccion;
    }
}

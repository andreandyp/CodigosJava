package fuentes;
public class Persona {
    private String nombre;
    private String celular;
    public Persona(String nombre, String celular){
        this.nombre = nombre;
        this.celular = celular;
    }
    public String getCelular() {
        return celular;
    }
    public String getNombre() {
        return nombre;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setNombresetCelular(String nombre, String celular){
        this.nombre = nombre;
        this.celular = celular;
    }
    
    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", celular=" + celular + '}';
    }
}

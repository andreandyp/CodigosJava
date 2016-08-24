package zootopia;
public class Perezoso {
    private String nombre;
    int velocidad;

    @Override
    public String toString() {
        return "Perezoso{" + "nombre=" + nombre + ", velocidad=" + velocidad + '}';
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVelocidad() {
        return velocidad;
    }
}
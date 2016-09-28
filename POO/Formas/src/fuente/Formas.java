package fuente;
public class Formas extends Object {
    //De mayor a menor restricción
    private int id;
    int sides;
    protected String color;
    public String nombre;

    public Formas(int id, int sides, String color, String nombre) {
        this.id = id;
        this.sides = sides;
        this.color = color;
        this.nombre = nombre;
    }
    
}
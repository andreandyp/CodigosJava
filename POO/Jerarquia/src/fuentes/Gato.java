package fuentes;
public class Gato extends Animal implements Mascota {
    private String nombre;

    public Gato(String nombre) {
        super(4);
        this.nombre = nombre;
    }

    public Gato() {
        this("Animalencio");
    }

    @Override
    public void comer() {
        System.out.println("Odio la comida que me da el humano");
    }

    @Override
    public String obtenerNombre() {
        return nombre;
    }

    @Override
    public void establecerNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void jugar() {
        System.out.println("No me gusta jugar con el humano");
    }
       
}
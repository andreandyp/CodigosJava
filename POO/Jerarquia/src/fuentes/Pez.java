package fuentes;
public class Pez extends Animal implements Mascota{
    private String nombre;

    public Pez() {
        super(0);
        nombre = "Pez-Cadete";
    }

    @Override
    public void comer() {
        System.out.println("Que (glu) rica (glu) pescadina :Q_____");
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
        System.out.println("Glu glu :D");
    }
    
    @Override
    public void caminar(){
        System.out.println("No (glu) puedo (glu) caminar (glu) porque (glu) no tengo (glu) piernas (glu) :'(");
    }
    
}

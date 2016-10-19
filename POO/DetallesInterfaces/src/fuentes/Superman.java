package fuentes;

/**
 *
 * @author André
 */
public class Superman implements Volador{

    @Override
    public void despegar() {
        System.out.println("Despegando...");
    }

    @Override
    public void volar() {
        System.out.println("Mida mami mida etoy volando :D");
    }

    @Override
    public void aterrizar() {
        System.out.println("Aterrizaje de emergencia D:");
    }

    @Override
    public void detenerse() {
        System.out.println("¡ALTO!");
    }

    @Override
    public void chocar() {
        System.out.println("Ya valió madres :S");
    }
}

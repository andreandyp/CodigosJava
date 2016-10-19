package fuentes;
public interface Volador {
    /**
     * Altura constante de vuelo
     */
    final int ALTURA = 0xF; //Binarios :')
    //public static final int
    void despegar();
    void volar();
    void aterrizar();
    void detenerse();
    void chocar();
    //public abstract void
}

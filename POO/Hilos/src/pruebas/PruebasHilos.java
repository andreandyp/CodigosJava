package pruebas;
import fuentes.Hilo;
import fuentes.OtroHilo;

public class PruebasHilos {
    public static void main(String[] args) {
        Hilo hilo1,hilo2;
        hilo1 = new Hilo();
        hilo2 =  new Hilo();
        hilo1.start();
        hilo1.setPriority(Thread.);
        hilo2.start();
        OtroHilo jue = new OtroHilo();
        jue.run();
    }
}

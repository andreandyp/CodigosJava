package pruebas;
import fuentes.*;
public class Pruebas {
    public static void main(String[] args) {
        String numero = Integer.toBinaryString(Volador.ALTURA);
        System.out.println(numero);
        
        Volador v = new Superman();
        Superman sm = new Superman();
        sm.volar();
        v.chocar();
        System.out.println(v.ALTURA);
    }
}

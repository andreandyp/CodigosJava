package pruebas;

import fuentes.*;

public class Prueba {
    public static void main(String[] args) {
        Cliente yo = new Cliente("Andy");
        CuentaDeAhorros ca = new CuentaDeAhorros(500,0.50);
        yo.agregarCuenta(ca);
        CuentaDeCheques cc = new CuentaDeCheques(100,20000);
        yo.agregarCuenta(cc);
        System.out.println(yo.obtenerInfo());
    }
}

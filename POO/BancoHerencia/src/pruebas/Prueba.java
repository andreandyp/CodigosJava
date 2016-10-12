package pruebas;

import fuentes.*;

public class Prueba {
    public static void main(String[] args) {
        CuentaDeAhorros hue = new CuentaDeAhorros(10000,0.15);
        hue.consultar();
        CuentaDeCheques jue = new CuentaDeCheques(50000);
        jue.retirar(1000);
    }
}

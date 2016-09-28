package pruebas;

import fuentes.Taxi;

public class Transporte {
    public static void main(String[] args) {
        Taxi uber = new Taxi("1234567","102-SNU",2015,150);
        System.out.println(uber);
    }
}

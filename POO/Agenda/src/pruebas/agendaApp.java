package pruebas;

import fuentes.Agenda;
import fuentes.Persona;

public class agendaApp {
    public static void main(String[] args) {
        Agenda a1 = new Agenda();
        a1.getAgenda().add(new Persona("Hue","5562904651"));
        //a1.getAgenda().remove(0);
        System.out.println("A1: "+a1.getAgenda());
        a1.buscarNombre("Hue");
    }
}

package pruebas;

import fuentes.Persona;

public class PPersona {
    public static void main(String[] args) {
        Persona p = new Persona("André","5593043123");
        System.out.println("Yo: "+p);
        p.setNombresetCelular("Gizela", "5584332671");
        System.out.println("Teléfono: "+p.getCelular());
    }
}

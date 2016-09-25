package pruebas;

import java.util.ArrayList;
import fuentes.Persona;
public class PAgenda {
    public static void main(String[] args) {
        ArrayList<Persona> agenda = new ArrayList();
        /*Persona amigas[] = new Persona[3];
        amigas[0] = new Persona("Gisela","5543905612");
        amigas[1] = new Persona("Abby","5567298764");
        amigas[2] = new Persona("Quexa","5500193576");*/
        
        Persona p1 = new Persona("André","5585510098");
        agenda.add(p1);
        agenda.add(new Persona("Gizela","5555760911"));
        System.out.println(agenda);
        agenda.remove(p1);
        agenda.remove(1);
        System.out.println(agenda.contains(p1));
    }
}
package fuentes;

import java.util.ArrayList;

public class Agenda {
    private ArrayList <Persona> agenda;
    public Agenda(){
        agenda = new ArrayList();
    }
    public ArrayList <Persona> getAgenda(){
        return agenda;
    }
    public void buscarNombre(String nombre){
        for (int i = 0; i < agenda.size(); i++) {
            if(nombre.equals(agenda.get(i).getNombre())){
                System.out.println("Si existe: "+agenda.get(i));
            }
        }
    }
}

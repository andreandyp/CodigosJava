package fuentes;
import java.util.ArrayList;
public class ColeccionCadenas {
    private ArrayList<String> nombres;

    public ColeccionCadenas() {
        this.nombres = new ArrayList();
    }
    public void agregarNombre(String nombre){
        nombres.add(nombre);
    }
    public void quitarNombre(String nombre){
        nombres.remove(nombre);
    }
    public String verNombre(int i){
        return nombres.get(i);
    }
    public ArrayList<String> verNombres(){
        return nombres;
    }
    public void cambiar(int pos, String nNombre){
        nombres.set(pos, nNombre);
    }
}

package leercadenas;
import javax.swing.JOptionPane;
public class LeerCadenas extends JOptionPane {
    public static void main(String[] args) {
        /*String nombre;
        //Empezando con Swing
        nombre = showInputDialog(null, "Introduce tu nombre","Hola", 1);
        showMessageDialog(null, "Tu nombre es "+nombre);*/
        
        String edad = showInputDialog(null, "Inserta tu edad","Saluton", 2);
        int entero = Integer.parseInt(edad);
        showMessageDialog(null, "En un año tendrás "+(++entero));
    }
}
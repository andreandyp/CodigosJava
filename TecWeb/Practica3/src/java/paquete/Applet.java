package paquete;
import javax.swing.JApplet;
import javax.swing.JLabel;
public class Applet extends JApplet {
    public void init() {
        JLabel etiqueta = new JLabel("Saluton!");
        add(etiqueta);
    }
}
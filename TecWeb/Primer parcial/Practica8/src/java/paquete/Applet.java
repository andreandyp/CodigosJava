package paquete;
import javax.swing.JApplet;
import javax.swing.JLabel;
public class Applet extends JApplet {
    @Override
    public void init() {
        JLabel etiqueta = new JLabel("Hola");
        add(etiqueta);
        //public void paint(Graphics g){
        //g.drawString("Hola",50,25);}
    }
}
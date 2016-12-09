package fuentes;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Citas {
    JFrame ventana;
    
    public Citas(String titulo){
        ventana = new JFrame(titulo);
        ventana.setBounds(0,0,320,240);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        ventana.setLayout(null);       
    }
}

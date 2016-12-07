package fuentes;

import javax.swing.JFrame;

public class Citas {
    
    public Citas(boolean ver){
        if(ver)
            crearCitas();
        else
            verCitas();
    }
    private void verCitas(){
        JFrame ver = new JFrame("Ver citas disponibles");
        ver.setBounds(0,0,320,240);
        ver.setVisible(true);
        ver.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        ver.setLayout(null);
    }
    private void crearCitas(){
        JFrame ver = new JFrame("Crear una cita");
        ver.setBounds(0,0,320,240);
        ver.setVisible(true);
        ver.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        ver.setLayout(null);
    }
}

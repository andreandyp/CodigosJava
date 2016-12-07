package fuentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Interfaz extends JFrame implements ActionListener {
    JButton ver,crear;
    public Interfaz(){
        super("Autos André");
        setBounds(0,0,320,240);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        componentes();
    }
    private void componentes(){
        ver = new JButton("Ver citas");
        ver.setBounds(0,0,100,50);
        ver.addActionListener(this);
        this.add(ver);
        
        crear = new JButton("Crear citas");
        crear.setBounds(200, 0, 100, 50);
        crear.addActionListener(this);
        this.add(crear);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == crear){
            new Citas(true);
        }else{
            new Citas(false);
        }
    }
}

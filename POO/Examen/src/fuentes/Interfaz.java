package fuentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Interfaz extends JFrame implements ActionListener {
    JButton verN,crear,verF;
    public Interfaz(){
        super("Autos André");
        setBounds(0,0,320,240);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        componentes();
    }
    private void componentes(){
        verN = new JButton("Ver citas por nombre");
        verN.setBounds(0,0,200,20);
        verN.addActionListener(this);
        this.add(verN);
        
        verF = new JButton("Ver citas por fecha");
        verF.setBounds(0,20,200,20);
        verF.addActionListener(this);
        this.add(verF);
        
        crear = new JButton("Crear citas");
        crear.setBounds(0, 80, 200, 20);
        crear.addActionListener(this);
        this.add(crear);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == crear){
            new CrearCitas("Crear una cita");
        }else{
            if(e.getSource() == verN)
                new VerCitas("Ver las citas por nombre",true);
            else
                new VerCitas("Ver las citas por fecha",false);
        }
    }
}

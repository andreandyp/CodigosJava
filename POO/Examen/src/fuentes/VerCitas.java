package fuentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VerCitas extends Citas implements ActionListener {
    JLabel lfecha,lcliente;
    JTextField fecha,cliente;
    JButton consultar;
    private boolean nombre;
    public VerCitas(String titulo,boolean nombre) {
        super(titulo);
        this.nombre = nombre;
        crearInterfaz();
    }

    private void crearInterfaz() {
        lfecha = new JLabel("Por fecha:");
        lcliente = new JLabel("Por cliente:");
        lfecha.setBounds(0, 0, 75, 20);
        lcliente.setBounds(0, 0, 75, 20);
        fecha = new JTextField();
        cliente = new JTextField();
        fecha.setBounds(100, 0, 200, 20);
        cliente.setBounds(100, 0, 200, 20);
        consultar = new JButton("Consultar citas");
        consultar.setBounds(0, 40, 120, 30);
        consultar.addActionListener(this);
        if(nombre){
            ventana.add(lcliente);
            ventana.add(cliente);
        }else{
            ventana.add(lfecha);
            ventana.add(fecha);
        }
        ventana.add(consultar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == consultar){
            
        }
    }
    
    
}

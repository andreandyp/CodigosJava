package fuentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CrearCitas extends Citas implements ActionListener {

    JTextField nombre, telefono, email, marca, modelo, placas,fecha;
    JLabel lnombre, ltelefono, lemail, lmarca, lmodelo, lplacas,lfecha;
    JButton enviar;

    public CrearCitas(String titulo) {
        super(titulo);
        crearInterfaz();
    }

    private void crearInterfaz() {
        nombre = new JTextField();
        telefono = new JTextField();
        email = new JTextField();
        marca = new JTextField();
        modelo = new JTextField();
        placas = new JTextField();
        fecha = new JTextField();
        nombre.setBounds(100, 0, 200, 20);
        telefono.setBounds(100, 20, 200, 20);
        email.setBounds(100, 40, 200, 20);
        marca.setBounds(100, 80, 200, 20);
        modelo.setBounds(100, 100, 200, 20);
        placas.setBounds(100, 120, 200, 20);
        fecha.setBounds(100, 140, 200, 20);
        lnombre = new JLabel("Nombre:");
        ltelefono = new JLabel("Telefono:");
        lemail = new JLabel("E-mail:");
        lmarca = new JLabel("Marca:");
        lmodelo = new JLabel("Modelo:");
        lplacas = new JLabel("Placas:");
        lfecha = new JLabel("Fecha (dd/mm/aaaa)");
        lnombre.setBounds(0, 0, 100, 20);
        ltelefono.setBounds(0, 20, 100, 20);
        lemail.setBounds(0, 40, 100, 20);
        lmarca.setBounds(0, 80, 100, 20);
        lmodelo.setBounds(0, 100, 100, 20);
        lplacas.setBounds(0, 120, 100, 20);
        lfecha.setBounds(0, 140, 100, 20);
        enviar = new JButton("Crear cita");
        enviar.setBounds(0, 160, 100, 20);
        enviar.addActionListener(this);
        ventana.add(nombre);
        ventana.add(telefono);
        ventana.add(email);
        ventana.add(marca);
        ventana.add(modelo);
        ventana.add(placas);
        ventana.add(fecha);
        ventana.add(lnombre);
        ventana.add(ltelefono);
        ventana.add(lemail);
        ventana.add(lmarca);
        ventana.add(lmodelo);
        ventana.add(lplacas);
        ventana.add(lfecha);
        ventana.add(enviar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enviar){
            Cliente c = new Cliente(nombre.getText(),telefono.getText(),email.getText());
            Auto a = new Auto(marca.getText(),modelo.getText(),placas.getText());
            Archivos xml = new Archivos(c,a);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date fecha = formato.parse(this.fecha.getText());
                xml.agendarCita(formato.format(fecha));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(ventana, "Inserta una fecha correcta","Error",1);
            }
            
        }
    }
}

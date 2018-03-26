package clondropbox;
import java.awt.Color;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
public class Cliente extends JFrame implements ActionListener{
    private JLabel etiqueta, etiqueta2;
    private JButton examinar;
    private JList listaServidor;
    private DefaultListModel dlm = new DefaultListModel();
    private DragListener d;
    private Servicio conexion;
    
    public Cliente(){
        try{
            this.establecerConexion();
            initComponents();
        }
        catch(Exception e){
            System.out.println("Valió barriga: "+e.getMessage());
        }
        connectToDragDrop();
    }
    
    public void establecerConexion(){
        conexion = new Servicio();
    }
    public void initComponents() throws IOException{
        this.setLayout(null);
        
        etiqueta = new JLabel("Selecciona archivos con el botón o arrastralos hasta esta ventana");
        etiqueta2 = new JLabel("Da doble click algún archivo de la lista para descargarlo desde el servidor");
        etiqueta.setBounds(0, 0, 640, 18);
        etiqueta2.setBounds(0, 18, 640, 18);
        
        listaServidor = new JList();
        listaServidor.setBounds(150, 40, 200, 400);
        listaServidor.setBackground(Color.white);
        this.prepararLista();
        listaServidor.setModel(dlm);
        listaServidor.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    conexion.recibirArchivo(dlm.getElementAt(index).toString());
                }
            }
        });
        
        examinar = new JButton("Examinar");
        examinar.setBounds(0, 40, 100, 30);
        
        examinar.addActionListener(this);
        
        this.add(etiqueta);
        this.add(etiqueta2);
        this.add(listaServidor);
        this.add(examinar);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int repetitions = 0;
        repetitions++;
        if(ae.getSource() == this.examinar){
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Abrir archivo");
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jfc.setMultiSelectionEnabled(true);
            int succesful = jfc.showOpenDialog(this);
            if(succesful == JFileChooser.APPROVE_OPTION){
                File fs[] = jfc.getSelectedFiles();
                for(File f: fs){
                    conexion.enviarArchivo(f);
                }
            }  
        }
        this.prepararLista();
    }
    private void prepararLista(){
        try{
            dlm.clear();
            ArrayList<String> archivos = conexion.obtenerListaArchivos();
            for(String archivo : archivos){
                dlm.addElement(archivo);
            }
        }catch(Exception e){
            System.out.println("Valió barriga: "+e.getMessage());
        }
        
    }
    private void connectToDragDrop(){
        d = new DragListener(this.listaServidor, this.conexion, this.dlm);
        
        new DropTarget(this, (DropTargetListener) d);
    }
    
    public static void main(String[] args) {
        Cliente f = new Cliente();
        f.setTitle("Envío de archivos con sockets");
        f.setBounds(185, 95, 640, 480);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
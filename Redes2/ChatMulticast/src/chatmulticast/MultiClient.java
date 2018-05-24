package chatmulticast;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import com.vdurmont.emoji.EmojiParser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
 
public class MultiClient extends JFrame implements ActionListener{
    private String nombre = "";
    private HiloEntrada entrada;
    private final JEditorPane texto;
    private final JLabel tag1, tag2, tag3;
    private final JButton enviar, limpiar, archivo;
    private final JList mensajes;
    private final JTable emojis;
    private final DefaultListModel listaMensajes = new DefaultListModel();
    private final DefaultTableModel listaemojis = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
           return false;
        }
    };
    private final JScrollPane scroll, scrollemojis;
    private MulticastSocket socket;
    public static final String MCAST_ADDR  = "230.0.0.1";
    public static final int MCAST_PORT = 9013;
    private InetAddress grupo;
            
    public MultiClient(){
        this.inicializarChat();
        super.setLayout(null);
        super.setSize(new Dimension(640, 480));
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setTitle("Chat multicast");
        texto = new JEditorPane();
        tag1 = new JLabel("Escribe aquí");
        tag2 = new JLabel("Bienvenido "+nombre);
        tag3 = new JLabel("Mensajes recibidos");
        enviar = new JButton("Enviar");
        limpiar = new JButton("Limpiar");
        archivo = new JButton("Enviar archivos");
        mensajes = new JList();
        emojis = new JTable();
        scroll = new JScrollPane(mensajes);
        scrollemojis = new JScrollPane(emojis);
        tag1.setBounds(10, 35, 200, 15);
        tag2.setBounds(10, 10, 200, 15);
        tag3.setBounds(320, 35, 200, 15);
        texto.setBounds(10, 60, 300, 100);
        texto.setFont(new Font("Segoe UI Emoji", 0, 14));
        enviar.setBounds(10, 170, 100, 25);
        archivo.setBounds(120, 170, 100, 25);
        limpiar.setBounds(320, 330, 100, 25);
        enviar.addActionListener(this);
        archivo.addActionListener(this);
        limpiar.addActionListener(this);
        mensajes.setModel(listaMensajes);
        mensajes.setLayoutOrientation(JList.VERTICAL);
        mensajes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mensajes.setFont(new Font("Segoe UI Emoji", 0, 14));
        emojis.setModel(listaemojis);
        emojis.setSelectionBackground(Color.WHITE);
        new HiloEmojis(listaemojis).start();
        emojis.setFont(new Font("Segoe UI Emoji", 0, 14));
        emojis.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount() == 2){
                    int fila = emojis.rowAtPoint(e.getPoint());
                    int columna = emojis.columnAtPoint(e.getPoint());
                    texto.setText(texto.getText()+emojis.getValueAt(fila, columna)+" ");
                }
            }
        });
        scroll.setBounds(320, 60, 300, 260);
        scrollemojis.setBounds(10, 200, 300, 230);
        
        super.add(tag1);
        super.add(tag2);
        super.add(tag3);
        super.add(texto);
        super.add(enviar);
        super.add(archivo);
        super.add(limpiar);
        super.add(scroll);
        super.add(scrollemojis);
        super.setVisible(true);
    }
    
    private void inicializarChat(){
        try {
            entrada = new HiloEntrada(listaMensajes);
            socket = new MulticastSocket(MCAST_PORT);
            grupo = InetAddress.getByName(MCAST_ADDR);
            socket.joinGroup(grupo);
        } catch (IOException ex) {
            System.out.println("Valió barriga: "+ex.getMessage());
            System.exit(-1);
        }
        while(nombre.equals("")){
            nombre = JOptionPane.showInputDialog(this, "", "Selecciona un nombre", JOptionPane.QUESTION_MESSAGE);
            if(nombre == null){
                System.exit(0);
            }
        }
        entrada.start();
    }
    
    
    public static void main(String[] args) {
        new MultiClient();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(enviar)){
            String mensaje = nombre+" dice: "+EmojiParser.parseToHtmlDecimal(texto.getText());
            DatagramPacket paquete = new DatagramPacket(mensaje.getBytes(), mensaje.length(), grupo, MCAST_PORT);
            try {
                socket.setTimeToLive(32);
                socket.send(paquete);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
            finally{
                texto.setText("");
                texto.update(texto.getGraphics());
            }
        }
        else if(e.getSource().equals(limpiar)){
            listaMensajes.clear();
        }
        else if(e.getSource().equals(archivo)){
            try{
                JFileChooser jfc = new JFileChooser();
                jfc.setDialogTitle("Abrir archivo");
                jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                jfc.setMultiSelectionEnabled(false);
                int succesful = jfc.showOpenDialog(this);
                if(succesful == JFileChooser.APPROVE_OPTION){
                    String clave = "archivoarchivoarchivo";
                    DatagramPacket paqClave = new DatagramPacket(clave.getBytes(), clave.length(), grupo, MCAST_PORT);
                    socket.send(paqClave);

                    File archivo = jfc.getSelectedFile();
                    clave = archivo.getName();
                    paqClave = new DatagramPacket(clave.getBytes(), clave.length(), grupo, MCAST_PORT);
                    socket.send(paqClave);
                    paqClave = new DatagramPacket(nombre.getBytes(), nombre.length(), grupo, MCAST_PORT);
                    socket.send(paqClave);
                    DataInputStream entradaArchivo = new DataInputStream(new FileInputStream(archivo.getAbsolutePath()));
                    int sent = 0, t;
                    long p, size = archivo.length();
                    while(sent < size){
                        byte data[] = new byte[1024];
                        t = entradaArchivo.read(data);
                        DatagramPacket paquete = new DatagramPacket(data, t, grupo, MCAST_PORT);
                        socket.setTimeToLive(32);
                        socket.send(paquete);
                        sent += t;
                        p = (sent*100/size);
                        System.out.println("Progreso: "+p+"%");
                    }
                    entradaArchivo.close();
                    clave = "finfinfin";
                    paqClave = new DatagramPacket(clave.getBytes(), clave.length(), grupo, MCAST_PORT);
                    socket.send(paqClave);
                }
            }catch(IOException ex){
                System.out.println("Valió barriga el archivo: "+ex.getMessage());
            }
        }
    }

}
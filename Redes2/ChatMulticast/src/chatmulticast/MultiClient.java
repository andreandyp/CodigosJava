package chatmulticast;
import com.vdurmont.emoji.Emoji;
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
import com.vdurmont.emoji.EmojiManager;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Collection;
import javax.swing.JEditorPane;
 
public class MultiClient extends JFrame implements ActionListener{
    private String nombre = "";
    private HiloEntrada entrada;
    private JEditorPane texto;
    private JLabel tag1, tag2, tag3;
    private JButton enviar, limpiar;
    private JList mensajes, emojis;
    private DefaultListModel listaMensajes = new DefaultListModel();
    private DefaultListModel listaemojis = new DefaultListModel();
    private JScrollPane scroll, scrollemojis;
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
        mensajes = new JList();
        emojis = new JList();
        scroll = new JScrollPane(mensajes);
        scrollemojis = new JScrollPane(emojis);
        tag1.setBounds(10, 35, 200, 15);
        tag2.setBounds(10, 10, 200, 15);
        tag3.setBounds(320, 35, 200, 15);
        texto.setBounds(10, 60, 300, 100);
        texto.setFont(new Font("Segoe UI Emoji", 0, 14));
        enviar.setBounds(10, 170, 100, 25);
        limpiar.setBounds(320, 330, 100, 25);
        enviar.addActionListener(this);
        limpiar.addActionListener(this);
        mensajes.setModel(listaMensajes);
        mensajes.setLayoutOrientation(JList.VERTICAL);
        mensajes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mensajes.setFont(new Font("Segoe UI Emoji", 0, 14));
        Font fuentes[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        emojis.setModel(listaemojis);
        emojis.setLayoutOrientation(JList.VERTICAL);
        emojis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        emojis.setFont(new Font("Segoe UI Emoji", 0, 14));
        scroll.setBounds(320, 60, 300, 260);
        scrollemojis.setBounds(10, 200, 100, 250);
        Collection<Emoji> emojis = EmojiManager.getAll();
        System.out.println(emojis.size());
        for(Emoji e : emojis){
            listaemojis.addElement(e.getUnicode());
        }
        super.add(tag1);
        super.add(tag2);
        super.add(tag3);
        super.add(texto);
        super.add(enviar);
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
            System.out.println("Paq "+paquete.getLength());
            System.out.println("Mens "+mensaje.length());
            try {
                socket.setTimeToLive(32);
                socket.send(paquete);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
            texto.setText("");
        }
        else if(e.getSource().equals(limpiar)){
            listaMensajes.clear();
        }
    }

}
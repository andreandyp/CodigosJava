package chatmulticast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.DefaultListModel;

public class HiloEntrada extends Thread {
    private MulticastSocket socket;
    private DefaultListModel lista;
    private LinkedList<String> mensajes;
    public static final String MCAST_ADDR  = "230.0.0.1";
    public static final int MCAST_PORT = 9013;
    public static final int DGRAM_BUF_LEN = 1024;
    private InetAddress group;

    public HiloEntrada(DefaultListModel lista) throws UnknownHostException, IOException {
        group = InetAddress.getByName(MCAST_ADDR);
        socket = new MulticastSocket(MCAST_PORT);
        socket.joinGroup(group);
        this.lista = lista;
    }
    
    public HiloEntrada(LinkedList mensajes) throws IOException{
        group = InetAddress.getByName(MCAST_ADDR);
        socket = new MulticastSocket(MCAST_PORT);
        socket.joinGroup(group);
        this.mensajes = mensajes;
    }
    
    
    @Override
    public void run() {
        StringBuilder entrante;
        while(true){
            byte[] buffer = new byte[DGRAM_BUF_LEN];
            DatagramPacket recv = new DatagramPacket(buffer, buffer.length);

            try {
                socket.receive(recv);
            } catch (IOException ex) {
                System.out.println("Valió barriga: "+ex.getMessage());
            }
            
            SimpleDateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            entrante = new StringBuilder("<html><head></head><body>");
            entrante.append(recv.getAddress()).append(":").append(recv.getPort());
            entrante.append(" a las ").append(fechaHora.format(new Date())).append("<br>");
            byte [] data = recv.getData();
            entrante.append(new String(data)).append("</body></html>");
            
            if(lista != null){
                lista.addElement(entrante.toString());
            }else{
                mensajes.add(entrante.toString());
            }
        }
    }
    
}

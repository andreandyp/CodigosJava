package chatmulticast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.LinkedList;

public class MultiServer{
    public static final String MCAST_ADDR = "230.0.0.1";
    public static final int MCAST_PORT = 9013;
    public static final int DGRAM_BUF_LEN = 512;
    private final ArrayList<String> participantes = new ArrayList();
    private final LinkedList<String> listaMensajes = new LinkedList();
    private HiloEntrada entrada;
    private InetAddress grupo = null;
    
    public MultiServer(){
        try {
            grupo = InetAddress.getByName(MCAST_ADDR);
            entrada = new HiloEntrada(listaMensajes);
            ejecutarServicio();
        } catch (IOException ex) {
            System.out.println("Valió barriga: "+ex.getMessage());
            System.exit(-1);
        }
    }
    public static void main(String[] args) {
        new MultiServer();
    }
    
    private void ejecutarServicio() throws IOException{
        MulticastSocket socket = new MulticastSocket(MCAST_PORT);
        socket.joinGroup(grupo);
        while(true){
            if(listaMensajes.isEmpty()){
                continue;
            }
            
            String mensaje;
            while( (mensaje = listaMensajes.poll()) != null){
                DatagramPacket paquete = new DatagramPacket(mensaje.getBytes(), mensaje.length(), grupo, MCAST_PORT);
                socket.send(paquete);
            }
        }
    }
}

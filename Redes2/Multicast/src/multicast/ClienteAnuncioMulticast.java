package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteAnuncioMulticast {
    public static void main(String[] args) {
        try{
            int puerto = 1234;
            String dir = "229.1.1.1";
            MulticastSocket ms = new MulticastSocket(1234);
            InetAddress grupo = null;
            
            grupo = InetAddress.getByName(dir);
            ms.setReuseAddress(true);
            ms.joinGroup(grupo);
            System.out.println("Unido al grupo, comienza envío de anuncios");
            String mensaje = "Anuncio multicast";
            byte[]b = mensaje.getBytes();
            while(true){
                Object o = new Object();
                DatagramPacket p = new DatagramPacket(b, b.length, grupo, 5678);
                ms.receive(p);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorAnuncioMulticast.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
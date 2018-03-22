package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorAnuncioMulticast {
    public static void main(String[] args) {
        try{
            int puerto = 1234;
            String dir = "229.1.1.1";
            MulticastSocket ms = new MulticastSocket(5678);
            InetAddress grupo = null;
            
            grupo = InetAddress.getByName(dir);
            ms.setReuseAddress(true);
            ms.joinGroup(grupo);
            System.out.println("Unido al grupo, comienza envío de anuncios");
            String mensaje = "Anuncio multicast";
            byte[]b = mensaje.getBytes();
            while(true){
                DatagramPacket p = new DatagramPacket(b, b.length, grupo, puerto);
                ms.send(p);
                
                Thread.currentThread().sleep(1000);
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ServidorAnuncioMulticast.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
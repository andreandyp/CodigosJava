package udp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServidorUDP {
    public static void main(String[] args) {
        int puerto = 8000;
        DatagramPacket dp = null;
        DatagramSocket ds = null;
        ObjectInputStream ois = null;
        ObjetoUDP objeto = null;
        
        try{
            ds = new DatagramSocket(puerto);
            System.out.println("Servidor UDP iniciado en el puerto "+ds.getLocalPort());
            System.out.println("Recibiendo datos...");
            for(int i = 0; i < 10; i++){
                dp = new DatagramPacket(new byte[1024],1024);
                ds.receive(dp);
                System.out.println("Datagrama recibido... extrayendo información");
                System.out.println("Host remoto:"+dp.getAddress().getHostAddress()+":"+dp.getPort());
                System.out.println("Datos del paquete:");
                ois = new ObjectInputStream(new ByteArrayInputStream(dp.getData()));
                objeto = (ObjetoUDP) ois.readObject();
                System.out.println("N: "+objeto.getN());
                System.out.println("Total: "+objeto.getTotal());
                System.out.println("Msj: "+new String(objeto.getB()));
                ois.close();
            }
        } catch (SocketException ex) {
            System.err.println("Valió barriga el socket: "+ex.getMessage());
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Valió barriga otra cosa: "+ex.getMessage());
        }
    }
}
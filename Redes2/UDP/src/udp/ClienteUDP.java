package udp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteUDP {
    public static void main(String[] args) {
        int puerto = 8000;
        DatagramPacket dp = null;
        DatagramSocket dc = null;
        ObjectOutputStream oos = null;
        ByteArrayOutputStream bos=null;
        ObjetoUDP objeto = null;
        
        try{
            dc = new DatagramSocket();
            dp = new DatagramPacket(new byte[1024],1024);
            InetAddress direccion = InetAddress.getByName("127.0.0.1");
            dp.setAddress(direccion);
            dp.setPort(puerto);
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            byte[] buf = new byte[1024];
            objeto = new ObjetoUDP(1, 1, "12345".getBytes());
            oos.writeObject(objeto);
            oos.flush();
            buf = bos.toByteArray();
            dp.setData(buf);
            dc.send(dp);
            
            System.out.println("Datagrama enviado con los datos:");
            System.out.println("N: "+objeto.getN());
            System.out.println("de un total de: "+objeto.getTotal());
            System.out.println("Msj: "+new String(objeto.getB()));
            oos.close();
        }
        catch(SocketException | UnknownHostException ex) {
            System.err.println("Valió barriga el socket o el servidor: "+ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Valió barriga otra cosa: "+ex.getMessage());
        }
    }
}

package chatmulticast;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
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
        FileOutputStream fos;
        while(true){
            byte[] buffer = new byte[DGRAM_BUF_LEN];
            DatagramPacket recv = new DatagramPacket(buffer, buffer.length);

            try {
                socket.receive(recv);
            } catch (IOException ex) {
                System.out.println("Valió barriga: "+ex.getMessage());
            }
            
            byte data[] = new byte[recv.getLength()];
            for(int i = 0; i < recv.getLength(); i++){
                data[i] = recv.getData()[i];
            }
            String mensaje = new String(data);
            if(mensaje.equals("archivoarchivoarchivo")){
                try {
                    DataOutputStream recibirArchivo = new DataOutputStream(new FileOutputStream("C:\\Users\\andre\\Desktop\\Benchmarking.pdf"));
                    while(true){
                        byte[] buff = new byte[DGRAM_BUF_LEN];
                        DatagramPacket entrada = new DatagramPacket(buff, buff.length);

                        System.out.println("anets");
                        socket.receive(entrada);
                        System.out.println("espera");
                        byte datos[] = new byte[entrada.getLength()];
                        for(int i = 0; i < entrada.getLength(); i++){
                            datos[i] = entrada.getData()[i];
                        }
                        String clave = new String(datos);
                        System.out.println(clave);
                        if(clave.equals("finfinfin")){
                            System.out.println("Aquí llega");
                            recibirArchivo.close();
                            break;
                        }else{
                            System.out.println("Recibe");
                            recibirArchivo.write(datos, 0, datos.length);
                        }
                    
                    }
                } catch (IOException ex) {
                    System.out.println("Valió barriga: "+ex.getMessage());
                }
            }else{
                SimpleDateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                entrante = new StringBuilder("<html><head></head><body><p>");
                entrante.append(recv.getAddress()).append(":").append(recv.getPort());
                entrante.append(" a las ").append(fechaHora.format(new Date())).append("<br>");
                entrante.append(new String(data)).append("</p></body></html>");
                if(lista != null){
                    lista.addElement(entrante.toString());
                }else{
                    mensajes.add(entrante.toString());
                }
            }
        }
    }
    
}

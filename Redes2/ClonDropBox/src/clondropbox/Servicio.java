package clondropbox;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
public class Servicio {
    private Socket socket;
    private DataInputStream recibir;
    private DataOutputStream enviar;
    private ArrayList<String> archivosServidor;
    
    public Servicio(){
        try{
            String host = "localhost";
            int port = 12345;
            socket = new Socket(host, port);
            recibir = new DataInputStream(socket.getInputStream());
            enviar = new DataOutputStream(socket.getOutputStream());
            archivosServidor = new ArrayList<String>();
            
            System.out.println("Conectado con: "+socket.getInetAddress());
        } catch (IOException ex) {
            System.out.println("Valió barriga: "+ex.getMessage());
        }
        
    }
    
    public ArrayList<String> obtenerListaArchivos() throws IOException{
        archivosServidor.clear();
        String nombre = "";
        while( !(nombre = recibir.readUTF()).equals("fin")){
            archivosServidor.add(nombre);
        }
        return archivosServidor;
    }
    
    public void recibirArchivo(String nombre){
        try {
            enviar.writeBoolean(true);
            enviar.writeUTF(nombre);
            
            long rec = 0, size = recibir.readLong();
            DataOutputStream recibirArchivo = new DataOutputStream(new FileOutputStream("C:\\Users\\andre\\Desktop\\Recibidos\\"+nombre));
            int t;
            while(rec < size){
                byte b[] = new byte[1024];
                t = recibir.read(b);
                recibirArchivo.write(b, 0, t);
                recibirArchivo.flush();
                rec += t;
            }
            recibirArchivo.close();
            System.out.println("Archivo recibido de servidor");
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
    }
    public void enviarArchivo(File file){
        try{
            enviar.flush();
            DataInputStream entradaArchivo = new DataInputStream(new FileInputStream(file.getAbsolutePath()));
            String str = file.getName();
            enviar.writeBoolean(false);
            enviar.writeUTF(str);
            enviar.writeLong(file.length());
            enviar.flush();
            int sent = 0, t;
            long p, size = file.length();
            while(sent < size){
                byte data[] = new byte[1024];
                t = entradaArchivo.read(data);
                enviar.write(data, 0, t);
                enviar.flush();
                sent += t;
                p = (sent*100/size);
                System.out.println("Progreso: "+p+"%");
            }

            System.out.println("Archivo enviado");
            entradaArchivo.close();
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
    }
}
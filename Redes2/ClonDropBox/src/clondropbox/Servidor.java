package clondropbox;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Servidor {
    final String DIRECTORIO_TRABAJO = "DatosApp";
    DataInputStream recibir;
    DataOutputStream enviar;
    Socket socket;
    public Servidor(){
        try{
            ServerSocket ss = new ServerSocket(12345);
            System.out.println("Servidor iniciado, esperando clientes...");
            socket = ss.accept();
            System.out.println("Cliente conectado");
            
            while(true){
                recibir = new DataInputStream(socket.getInputStream());
                enviar = new DataOutputStream(socket.getOutputStream());
                enviarArchivos(new File(DIRECTORIO_TRABAJO), enviar);
                enviar.writeUTF("fin");
                System.out.println("Esperando modo...");
                if(recibir.readBoolean()){
                    String nombre = recibir.readUTF();

                    String ruta = DIRECTORIO_TRABAJO+"/"+nombre;
                    File archivo = new File(ruta);
                    DataInputStream disArchivo = new DataInputStream(new FileInputStream(archivo));
                    
                    long tamaño = archivo.length();
                    enviar.writeLong(tamaño);
                    int sent = 0, t;
                    long p;
                    while(sent < tamaño){
                        byte data[] = new byte[1024];
                        t = disArchivo.read(data);
                        enviar.write(data, 0, t);
                        enviar.flush();
                        sent += t;
                        p = (sent*100/tamaño);
                        System.out.println("Progreso: "+p+"%");
                    }
                    disArchivo.close();
                    
                    System.out.println("Archivo "+nombre+" enviado a cliente!!!");
                    continue;
                }
                
                String name = recibir.readUTF();
                long size = recibir.readLong();
                String ruta = DIRECTORIO_TRABAJO+"/"+name;
                DataOutputStream recibirArchivo = new DataOutputStream(new FileOutputStream(ruta));
                long rec = 0;
                int t;
                while(rec < size){
                    byte b[] = new byte[1024];
                    t = recibir.read(b);
                    recibirArchivo.write(b, 0, t);
                    recibirArchivo.flush();
                    rec += t;
                }

                System.out.println("Cliente: "+socket.getInetAddress()+":"+socket.getPort()+ " envió el archivo: "+ name);
                recibirArchivo.close();
                if(socket.isClosed()){
                    System.out.println("Se cerró el socket");
                    return;
                }
            }
        }
        catch(IOException e){
            System.out.println("Error: "+ e.getMessage());
        }
    }
    public static void main(String[] args) {
        new Servidor();
    }
    
    private void enviarArchivos(final File folder, final DataOutputStream canal) throws IOException{
        for(File archivo : folder.listFiles()){
            canal.writeUTF(archivo.getName());
        }
    }
}

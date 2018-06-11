package clientehttp;

import java.io.IOException;
import java.net.ServerSocket;

public class ClienteHTTP {
    private static final int PUERTO = 1338;
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(PUERTO);
            System.out.println("Servidor en http://localhost:"+PUERTO);
            while(true){
                HiloRespuesta respuesta = new HiloRespuesta(ss.accept());
                System.out.println("Atendiendo solicitud");
                respuesta.start();
            }
        } catch (IOException ex) {
            System.out.println("Valió barriga: "+ex.getMessage());
        }
    }    
}

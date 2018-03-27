package examen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedList;

public class Servidor {
    private final String ip;
    private String baseActual = "pruebas";
    private final int puerto;
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private HashMap <String, HashMap> bases = new HashMap<String, HashMap>();
    //private HashMap <String, LinkedList> tablas;
            
    public Servidor(String ip, int puerto) throws UnknownHostException, IOException {
        this.ip = ip;
        this.puerto = puerto;
        serverSocket = new ServerSocket(puerto, 50, InetAddress.getByName(ip));
        System.out.println("Servidor listo en: "+ip+":"+puerto);
        bases.put(baseActual, new HashMap<String, LinkedList<Object> >());
        this.iniciar();
    }
    
    private void iniciar() throws IOException{
        socket = serverSocket.accept();
        System.out.println("Cliente conectado");
        entrada = new DataInputStream(socket.getInputStream());
        salida = new DataOutputStream(socket.getOutputStream());
        
        while(true){
            switch(entrada.readInt()){
                case 1:
                    System.out.println("Operación crear");
                    this.crear(entrada.readInt(), entrada.readUTF());
                    break;
                case 2:
                    System.out.println("Operación mostrar");
                    this.usar(entrada.readUTF());
                    break;
                case 3:
                    System.out.println("Operación mostrar");
                    this.mostrar(entrada.readInt(), entrada.readUTF());
                    break;
                case 4:
                    System.out.println("Operación borrar");
                    this.borrar(entrada.readInt(), entrada.readUTF());
            }
        }
    }
    
    private void crear(int operacion, String nombre) throws IOException{
        if(operacion == 1){
            if(bases.containsKey(nombre)){
                enviarMensaje(false, "La base ya existe");
            }
            else{
                bases.put(nombre, new HashMap<String, LinkedList<Object> >());
                enviarMensaje(true, "Base '"+nombre+"' creada");
            }
        }
        else{
            int tamaño = entrada.readInt();
            byte[] b = new byte[tamaño];
            entrada.read(b);
            String[] campos = new String(b).split(" ");
            if(bases.get(baseActual).containsKey(nombre)){
                enviarMensaje(false, "La tabla ya existe");
            }else{
                Compilador javac = new Compilador(nombre, campos);
                if(javac.compilar()){
                    bases.get(baseActual).put(nombre, new LinkedList<Object>());
                    enviarMensaje(true, "Tabla creada");
                }
                else{
                    enviarMensaje(false, "No se pudo crear la tabla. Campos incorrectos");
                }
            }
        }
    }
    
    private void usar(String nombreBD) throws IOException {
        System.out.println("Operación usar");
        if(bases.containsKey(nombreBD)){
            baseActual = nombreBD;
            salida.writeUTF(nombreBD);
            enviarMensaje(true, "Base "+nombreBD+" seleccionada");
        }else{
            salida.writeUTF("");
            enviarMensaje(false, "La base no existe");
        }
    }
    
    private void mostrar(int operacion, String nombre) throws IOException {
        StringBuilder resultados = new StringBuilder();
        if(operacion == 1){
            for(String base : bases.keySet()){
                resultados.append("\n");
                resultados.append(base);
            }
            enviarMensaje(true, resultados.toString());
        }
        else if(operacion == 2){
            for(Object tabla : bases.get(baseActual).keySet()){
                resultados.append("\n");
                resultados.append(tabla.toString());
            }
            enviarMensaje(true, resultados.toString());
        }
        else{
            System.out.println("Mostrar tabla "+nombre);
        }
    }
    
    private void borrar(int operacion, String nombre) throws IOException {
        if(operacion == 1){
            if(bases.containsKey(nombre)){
                bases.remove(nombre);
                enviarMensaje(true, "Base '"+nombre+"' borrada");
            }
            else{
                enviarMensaje(false, "La base no existe");
            }
        }
        else{
            if(bases.get(baseActual).containsKey(nombre)){
                bases.get(baseActual).remove(nombre);
                enviarMensaje(true, "Tabla '"+nombre+"' borrada");
            }
            else{
                enviarMensaje(false, "La tabla no existe");
            }
        }
    }
    
    private void enviarMensaje(boolean resultado, String mensaje) throws IOException{
        salida.writeBoolean(resultado);
        salida.writeUTF(mensaje);
    }
    
    public static void main(String[] args) {
        try {
            new Servidor("127.0.0.1", 1336);
        } catch (IOException ex) {
            System.out.println("Valió barriga: "+ex.getMessage());
        }
    }

}

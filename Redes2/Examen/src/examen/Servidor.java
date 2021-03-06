package examen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Servidor {
    private String baseActual = "pruebas";
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private HashMap <String, HashMap> bases = new HashMap<String, HashMap>();
            
    public Servidor(String ip, int puerto) throws UnknownHostException, IOException, MalformedURLException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, NoSuchFieldException {
        serverSocket = new ServerSocket(puerto, 50, InetAddress.getByName(ip));
        System.out.println("Servidor listo en: "+ip+":"+puerto);
        bases.put(baseActual, new HashMap<String, LinkedList<Object> >());
        
        //Depuración:
        bases.get(baseActual).put("Prueba", new LinkedList<Object>());
        ((LinkedList) bases.get(baseActual).get("Prueba")).add(new Prueba((byte) 1,(short) 31, 20, 100L, 9.0F, 9.0, true, 'S', "\"Susy\""));
        this.iniciar();
    }
    
    private void iniciar() throws IOException, MalformedURLException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, NoSuchFieldException{
        socket = serverSocket.accept();
        System.out.println("Cliente conectado");
        entrada = new DataInputStream(socket.getInputStream());
        salida = new DataOutputStream(socket.getOutputStream());
        
        while(true){
            switch(entrada.readInt()){
                case 1:
                    System.out.println("Operación crear");
                    crear(entrada.readInt(), entrada.readUTF());
                    break;
                case 2:
                    System.out.println("Operación usar");
                    usar(entrada.readUTF());
                    break;
                case 3:
                    System.out.println("Operación mostrar");
                    mostrar(entrada.readInt(), entrada.readUTF());
                    break;
                case 4:
                    System.out.println("Operación borrar");
                    borrar(entrada.readInt(), entrada.readUTF());
                    break;
                case 5:
                    System.out.println("Operación insertar");
                    insertar(entrada.readUTF());
                    break;
                case 6:
                    System.out.println("Operación actualizar");
                    actualizar(entrada.readUTF());
                    break;
                case 7:
                    System.out.println("Operación buscar");
                    buscar(entrada.readUTF());
                    break;
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
            if(!bases.containsKey(nombre)){
                enviarMensaje(false, "La base no existe");
                return;
            }
            for(Object tabla : bases.get(nombre).keySet()){
                resultados.append("\n");
                resultados.append(tabla.toString());
            }
            enviarMensaje(true, resultados.toString());
        }
        else{
            if(!bases.get(baseActual).containsKey(nombre)){
                enviarMensaje(false, "La tabla no existe");
                return;
            }
            for(Object registro : ((LinkedList) bases.get(baseActual).get(nombre))){
                resultados.append("\n");
                resultados.append(registro.toString());
            }
            enviarMensaje(true, resultados.toString());
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
    
    private void insertar(String nombreTabla) throws IOException, MalformedURLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        int longitud = entrada.readInt();
        byte []b = new byte[longitud];
        entrada.read(b);
        ArrayList<String> datos = new ArrayList<String>(Arrays.asList(new String(b).split("\r")));
        
        if(bases.get(baseActual).containsKey(nombreTabla)){
            Compilador c = new Compilador(nombreTabla);
            Object registro = c.instanciar(datos);
            if(registro == null){
                enviarMensaje(false, "Datos incorrectos");
                return;
            }
            ((LinkedList) bases.get(baseActual).get(nombreTabla)).add(registro);
            enviarMensaje(true, "Inserción exitosa");
        }
        else{
            enviarMensaje(false, "La tabla no existe. Agrega antes la tabla correspondiente");
        }        
    }
    
    private void actualizar(String nombreTabla) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException{
        int longitud = entrada.readInt();
        byte []b = new byte[longitud];
        entrada.read(b);
        ArrayList<String> datos = new ArrayList<String>(Arrays.asList(new String(b).split("\r")));
        if(bases.get(baseActual).containsKey(nombreTabla)){
            LinkedList tabla = (LinkedList) bases.get(baseActual).get(nombreTabla);
            
            Compilador c = new Compilador(nombreTabla);
            Object actualizable = c.buscar(tabla, datos.get(datos.size() - 2), datos.get(datos.size() - 1));
            if(actualizable == null){
                enviarMensaje(false, "El objeto no se encuentra");
                return;
            }
            datos.remove(datos.size() - 1);
            datos.remove(datos.size() - 1);
            ((LinkedList) bases.get(baseActual).get(nombreTabla)).remove(actualizable);
            Object nuevo = c.actualizar(actualizable, datos);
            ((LinkedList) bases.get(baseActual).get(nombreTabla)).add(nuevo);
            enviarMensaje(true, "Actualización exitosa");
        }else{
            enviarMensaje(false, "La tabla no existe");
        }
    }
    
    private void buscar(String nombreTabla) throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException{
        int longitud = entrada.readInt();
        byte []b = new byte[longitud];
        entrada.read(b);
        ArrayList<String> datos = new ArrayList<String>(Arrays.asList(new String(b).split("\r")));
        if(bases.get(baseActual).containsKey(nombreTabla)){
            LinkedList tabla = (LinkedList) bases.get(baseActual).get(nombreTabla);
            
            Compilador c = new Compilador(nombreTabla);
            Object actualizable = c.buscar(tabla, datos.get(datos.size() - 2), datos.get(datos.size() - 1));
            if(actualizable == null){
                enviarMensaje(false, "El objeto no se encuentra");
                return;
            }
            enviarMensaje(true, actualizable.toString());
        }else{
            enviarMensaje(false, "La tabla no existe");
        }
    }
    
    private void enviarMensaje(boolean resultado, String mensaje) throws IOException{
        salida.writeBoolean(resultado);
        salida.writeUTF(mensaje);
    }
    
    public static void main(String[] args) {
        try {
            new Servidor("127.0.0.1", 1336);
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            System.out.println("Valió barriga: "+ex.getMessage());
            ex.printStackTrace();
        } catch (IllegalArgumentException | NoSuchFieldException ex) {
            System.out.println("Valió barriga: "+ex.getMessage());
            ex.printStackTrace();
        }
    }

}

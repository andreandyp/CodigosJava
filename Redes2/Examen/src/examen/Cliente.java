package examen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
    private final Scanner teclado;
    private String baseActual = "pruebas";
    private Socket socket;
    private DataInputStream entrada;
    private DataOutputStream salida;
    
    public Cliente(int puerto) throws IOException{
        teclado = new Scanner(System.in);
        //System.out.print("Sácate la IP que vengo bien TCP: ");
        //String ip = teclado.nextLine();
        String ip = "127.0.0.1";
        this.inicializar(ip, puerto);
        this.leerComandos();
    }
    
    private void inicializar(String ip, int puerto) throws UnknownHostException, IOException{
        socket = new Socket(InetAddress.getByName(ip), puerto);
        entrada = new DataInputStream(socket.getInputStream());
        salida = new DataOutputStream(socket.getOutputStream());
    }
    
    private void leerComandos() throws IOException{
        StringBuilder comando = new StringBuilder("");
        while(!comando.toString().equals("salir")){
            boolean resComando = false;
            System.out.print("Escribe un comando ("+baseActual+"): ");
            comando.insert(0, teclado.nextLine());
            String[] parametros = comando.toString().replace('(', ' ').replace(')', ' ').replace(",", "").toLowerCase().split(" ");
            switch(parametros[0]){
                case "crear":
                    resComando = this.crear(parametros);
                    break;
                case "usar":
                    resComando = this.usar(parametros);
                    break;
                case "mostrar":
                    resComando = this.mostrar(parametros);
                    break;
                case "borrar":
                    resComando = this.borrar(parametros);
                    break;
                case "insertar":
                    resComando = this.insertar(parametros);
                    break;
                case "actualizar":
                    //resComando = this.actualizar(parametros);
                    break;
                case "salir":
                    System.out.println("Bye");
                    return;
                default:
                    System.out.println("Comando inválido");
                    break;
            }
            if(resComando){
                recibirRespuesta();
            }
            comando.delete(0, comando.length());
        }
    }

    private boolean crear(String[] parametros) throws IOException {
        if(parametros.length < 3){
            System.out.println("Comando 'crear' inválido");
            return false;
        }
        if(parametros[1].equals("base")){
            salida.writeInt(1);
            salida.writeInt(1);
            salida.writeUTF(parametros[2]);
        }else if(parametros[1].equals("tabla")){
            salida.writeInt(1);
            salida.writeInt(2);
            salida.writeUTF(parametros[2]);
            String [] campos = new String[parametros.length - 3];
            for(int i = 0; i < parametros.length - 3; i++){
                campos[i] = parametros[i + 3];
            }
            byte []b = String.join(" ", campos).getBytes();
            salida.writeInt(b.length);
            salida.write(b);
        }else{
            System.out.println("Comando 'crear' inválido");
            return false;
        }
        
        return true;
    }
    
    private boolean usar(String[] parametros) throws IOException {
        salida.writeInt(2);
        salida.writeUTF(parametros[1]);
        String nuevaBase = entrada.readUTF();
        if(!nuevaBase.equals("")){
            baseActual = nuevaBase;
        }
        return true;
    }

    private boolean mostrar(String[] parametros) throws IOException {
        if(parametros[1].equals("todo")){
            salida.writeInt(3);
            salida.writeInt(1);
            salida.writeUTF("");
        }else if(parametros[1].equals("base")){
            if(parametros.length < 3){
                System.out.println("Comando 'mostrar' inválido");
                return false;
            }
            salida.writeInt(3);
            salida.writeInt(2);
            salida.writeUTF(parametros[2]);
        }else if(parametros[1].equals("tabla")){
            if(parametros.length < 3){
                System.out.println("Comando 'mostrar' inválido");
                return false;
            }
            salida.writeInt(3);
            salida.writeInt(3);
            salida.writeUTF(parametros[2]);
        }
        else{
            System.out.println("Comando 'mostrar' inválido");
            return false;
        }
        
        return true;
    }

    private boolean borrar(String[] parametros) throws IOException {
        if(parametros.length < 3){
            System.out.println("Comando 'borrar' inválido");
            return false;
        }
        if(parametros[1].equals("base")){
            if(parametros[2].equals(baseActual)){
                System.out.println("No puedes borrar la base en uso");
                return false;
            }
            salida.writeInt(4);
            salida.writeInt(1);
            salida.writeUTF(parametros[2]);
        }else if(parametros[1].equals("tabla")){
            salida.writeInt(4);
            salida.writeInt(2);
            salida.writeUTF(parametros[2]);
        }else{
            System.out.println("Comando 'borrar' inválido");
            return false;
        }
        
        return true;
    }

    private void actualizar(String[] parametros) {
        for(String n : parametros){
            System.out.println(n);
        }
    }
    
    private boolean insertar(String[] parametros) throws IOException {
        salida.writeInt(5);
        salida.writeUTF(parametros[parametros.length - 1]);
        
        ArrayList<String> campos = new ArrayList<String>();
        for(int i = 1; i < parametros.length; i++){
            if(parametros[i].equals("")){
                continue;
            }
            if(parametros[i].equals("en")){
                break;
            }
            campos.add(parametros[i]);
        }
        byte []b = String.join(" ", campos).getBytes();
        salida.writeInt(b.length);
        salida.write(b);
            
        return true;
    }
    
    public static void main(String[] args) {
        try {
            new Cliente(1336);
        } catch (IOException ex) {
            System.out.println("Valió barriga: "+ex.getMessage());
        }
    }

    private void recibirRespuesta() throws IOException {
        boolean resultado = entrada.readBoolean();
        System.out.print(resultado ? "Operación exitosa. " : "Operación fallida. ");
        System.out.println("Mensaje: "+entrada.readUTF());
    }
}

package examen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            ArrayList<String> palabras = analizador(comando.toString(), "[a-zA-ZñÑ0-9]+");     
            if(palabras.size() == 0){
                System.out.println("Comando inválido");
                comando.delete(0, comando.length());
                continue;
            }
            
            switch(palabras.get(0)){
                case "crear":
                    if(palabras.size() >= 3){
                        resComando = this.crear(palabras);
                    }
                    else{
                        System.out.println("Comando 'crear' incorrecto");
                    }
                    break;
                case "usar":
                    if(palabras.size() == 2){
                        resComando = this.usar(palabras.get(1));
                    }
                    else{
                        System.out.println("Comando 'usar' incorrecto");
                    }
                    break;
                case "mostrar":
                    if(palabras.size() >= 2){
                        resComando = this.mostrar(palabras.get(1), palabras.size() == 3 ? palabras.get(2): null);
                    }
                    else{
                        System.out.println("Comando 'mostrar' incorrecto");
                    }
                    break;
                case "borrar":
                    if(palabras.size() == 3){
                        resComando = this.borrar(palabras.get(1), palabras.get(2));
                    }
                    else{
                        System.out.println("Comando 'borrar' incorrecto");
                    }
                    break;
                case "insertar":
                    if(palabras.size() >= 3 && palabras.get(1).equals("en")){
                        resComando = this.insertar(palabras.get(2), comando.toString());
                    }
                    else{
                        System.out.println("Comando 'insertar' incorrecto");
                    }
                    
                    break;
                case "actualizar":
                    if(palabras.size() >= 3 && palabras.get(1).equals("en")){
                        resComando = this.actualizar(palabras.get(2), comando.toString());
                    }
                    else{
                        System.out.println("Comando 'actualizar' incorrecto");
                    }
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

    private boolean crear(ArrayList<String> parametros) throws IOException {
        if(parametros.get(1).equals("base")){
            salida.writeInt(1);
            salida.writeInt(1);
            salida.writeUTF(parametros.get(2));
        }else if(parametros.get(1).equals("tabla")){
            if(parametros.size() - 3 == 0 || (parametros.size() - 3) % 2 != 0){
                System.out.println("Campos de tabla incorrectos");
                return false;
            }
            salida.writeInt(1);
            salida.writeInt(2);
            salida.writeUTF(parametros.get(2));
            String [] campos = new String[parametros.size() - 3];
            for(int i = 0; i < parametros.size() - 3; i++){
                campos[i] = parametros.get(i+3);
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
    
    private boolean usar(String nombreBase) throws IOException {
        salida.writeInt(2);
        salida.writeUTF(nombreBase);
        String nuevaBase = entrada.readUTF();
        if(!nuevaBase.equals("")){
            baseActual = nuevaBase;
        }
        return true;
    }

    private boolean mostrar(String cosa, String nombre) throws IOException {
        if(cosa.equals("bases")){
            salida.writeInt(3);
            salida.writeInt(1);
            salida.writeUTF("");
        }else if(cosa.equals("base")){
            if(nombre == null){
                System.out.println("Comando 'mostrar' inválido");
                return false;
            }
            salida.writeInt(3);
            salida.writeInt(2);
            salida.writeUTF(nombre);
        }else if(cosa.equals("tabla")){
            if(nombre == null){
                System.out.println("Comando 'mostrar' inválido");
                return false;
            }
            salida.writeInt(3);
            salida.writeInt(3);
            salida.writeUTF(nombre);
        }
        else{
            System.out.println("Comando 'mostrar' inválido");
            return false;
        }
        
        return true;
    }

    private boolean borrar(String cosa, String nombre) throws IOException {
        if(cosa.equals("base")){
            if(nombre.equals(baseActual)){
                System.out.println("No puedes borrar la base en uso");
                return false;
            }
            salida.writeInt(4);
            salida.writeInt(1);
            salida.writeUTF(nombre);
        }else if(cosa.equals("tabla")){
            salida.writeInt(4);
            salida.writeInt(2);
            salida.writeUTF(nombre);
        }else{
            System.out.println("Comando 'borrar' inválido");
            return false;
        }
        
        return true;
    }
    
    private boolean insertar(String nombreTabla, String com) throws IOException {
        salida.writeInt(5);
        salida.writeUTF(nombreTabla);
        ArrayList<String> params = analizador(com, "(\\\".*\\\")|(\\d+[lL]?\\.*\\d*[fF]?)|(true|false)|(\\'.*\\')");
        if(params.size() != 0){
            byte []b = String.join("\r", params).getBytes();
            salida.writeInt(b.length);
            salida.write(b);
            return true;
        }else{
            System.out.println("Faltan parámetros");
            return false;
        }
    }
    
    private boolean actualizar(String nombreTabla, String com) throws IOException {
        ArrayList<String> params = analizador(com, "(\\d+[lL]?\\.?\\d*[fF]?)|(\\\"([a-zA-Z0-9ñÑ]+\\s?)+\\\")|([a-zA-Z0-9ñÑ]+)|(true|false)|(\\'.*\\')");
        params.remove(0);
        params.remove(0);
        params.remove(0);
        salida.writeInt(6);
        salida.writeUTF(nombreTabla);
        if(params.size() > 0 && params.size() % 2 == 0 && params.contains("donde")){
            params.remove(params.size() - 2);
            byte []b = String.join("\r", params).getBytes();
            salida.writeInt(b.length);
            salida.write(b);
            return true;
        }else{
            System.out.println("Faltan parámetros");
            return false;
        }
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

    private ArrayList<String> analizador(String com, String regex) {
        Pattern expreg = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher buscador = expreg.matcher(com);
        ArrayList<String> palabras = new ArrayList<String>();
        while(buscador.find()){
            palabras.add(buscador.group());
        }
        
        return palabras;
    }

    private ArrayList <String> obtenerParametros(String params, String convetir) {
        ArrayList <String> parametros = new ArrayList<String>(Arrays.asList(params.replace(convetir, "").split(" ")));
        return parametros;
    }
}

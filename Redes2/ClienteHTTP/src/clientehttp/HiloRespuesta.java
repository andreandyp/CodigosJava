package clientehttp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class HiloRespuesta extends Thread{
    private final Socket socket;
    public HiloRespuesta(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            System.out.println("Cabeceras HTTP del cliente: ");
            String linea;
            while((linea = in.readLine()) != null){
                System.out.println(linea);
                if(linea.isEmpty()){
                    break;
                }
            }
            Calendar fecha = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            String html = "<!DOCTYPE html><html lang='es'><head><title>Hola</title></head><body><h1>Hola</h1></body>";
            
            out.write("HTTP/1.1 200 OK \r\n");
            out.write("Server: Java sockets \r\n");
            out.write("Content-Type: text/html; charset=utf-8\r\n");
            out.write("Content-Language: es\r\n");
            out.write("Content-Length: "+html.length()+"\r\n");
            out.write("Last-modified: "+dateFormat.format(fecha.getTime())+"\r\n");
            fecha.add(Calendar.DATE, 1);
            out.write("Expires: "+dateFormat.format(fecha.getTime())+"\r\n");
            out.write("\r\n");
            out.write(html);
            
            out.close();
            in.close();
            socket.close();
            System.out.println("Cliente atendido");
        } catch (IOException ex) {
            System.out.println("Valió barriga: "+ex.getMessage());
        }
    }
}

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
            String linea, verbo;
            verbo = in.readLine();
            System.out.println(verbo);
            int longitud = 0;
            while(!(linea = in.readLine()).equals("")){
                System.out.println(linea);
                if(linea.contains("Content-Length")){
                    String lon = linea.replace("Content-Length: ", "");
                    longitud = Integer.parseInt(lon);
                }
            }
            
            String html;
            if(verbo.contains("GET")){
                html = this.responder("GET", longitud, in);
            } else if(verbo.contains("POST")){
                html = this.responder("POST", longitud, in);
            } else if(verbo.contains("PUT")){
                html = this.responder("PUT", longitud, in);
            } else if(verbo.contains("PATCH")){
                html = this.responder("PATCH", longitud, in);
            }else if(verbo.contains("DELETE")){
                html = this.responder("DELETE", longitud, in);
            }else{
                html = this.responder("CUSTOM", longitud, in);
            }
            Calendar fecha = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            
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
    
    private String responder(String verboHTTP, int contentLength, BufferedReader in) throws IOException{
        StringBuilder html = new StringBuilder("<!DOCTYPE html><html lang='es'><head><title>Respuesta</title></head><body><h1>Método ");
        html.append(verboHTTP);
        html.append("</h1>");
        for(int i = 0; i < contentLength; i++){
            char caracter = (char) in.read();
            if(caracter == '&'){
                html.append("<br>");
            }
            else{
                html.append(caracter);
            }
        }
        html.append("</body></html>");
        return html.toString();
    }
}

package examenurl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ExamenURL{
    private final ReentrantLock rl = new ReentrantLock();
    private final ArrayList<String> registro = new ArrayList();
    private final LinkedBlockingQueue<String> pendientes = new LinkedBlockingQueue();
    private final String url;
    private int profundidad;
    
    public ExamenURL() throws InterruptedException{
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingresa una URL: ");
        url = teclado.nextLine();
        System.out.println("Inserta el nivel de profundidad: (Máximo 2)");
        profundidad = teclado.nextInt();
        if(profundidad > 2){
            System.out.println("Excediste el límite. Se reajustará la profundidad a 2");
            profundidad = 2;
        }
        
        System.out.println("Obteniendo enlaces");
        pendientes.put(this.url);
        obtenerEnlaces(url, profundidad);
        System.out.println("Descargando... ");
        
        ExecutorService executor = Executors.newFixedThreadPool(64);
        for(int i = 0; i < 64; i++){
            executor.execute(new Hilo(rl, registro, pendientes, i));
        }
        
        System.out.println("Esperando a que todo termine. Enlaces obtenidos: "+pendientes.size());
        executor.shutdown();
        while(!executor.isTerminated()){}
        System.out.println("Ya acabó");
        
        System.out.println("Enlaces descargados: "+registro.size());
    }
    public static void main(String[] args) {
        try {
            new File("descargas\\assets\\js").mkdirs();
            new File("descargas\\assets\\css").mkdirs();
            new ExamenURL();
        } catch (InterruptedException ex) {
            System.out.println("Valió barriga: "+ex.getMessage());
        }
    }
    
    public void obtenerEnlaces(String url, int profundidad){
        try{
            Document doc = Jsoup.connect(url).ignoreHttpErrors(false).get();
            Elements enlaces = doc.select("a[href^='http']");
            for(Element enlace : enlaces){
                pendientes.put(enlace.attr("href"));
                if(profundidad != 0){
                    obtenerEnlaces(enlace.attr("href"), profundidad - 1);    
                }
            }
        }
        catch(IOException e){
            System.out.println("No se pudo descargar: "+e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Valió barriga: "+e.getMessage());
        }
    }
    
    
}
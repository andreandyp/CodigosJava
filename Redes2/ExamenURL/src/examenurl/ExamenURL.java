package examenurl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ExamenURL{
    private ReentrantLock rl;
    private ArrayList<String> registro;
    private LinkedBlockingQueue<String> pendientes;
    
    public ExamenURL() throws InterruptedException{
        String url = "https://andreandyp.github.io";
        rl = new ReentrantLock();
        registro = new ArrayList();
        pendientes = new LinkedBlockingQueue();
        obtenerEnlaces(url);
        ExecutorService executor = Executors.newScheduledThreadPool(registro.size());
        
        for(String enlace : pendientes){
            executor.execute(new Hilo(enlace, rl, registro, pendientes));
        }
        executor.shutdown();
        while(!executor.isTerminated()){
            
        }
        System.out.println("Ya acabó");
    }
    public static void main(String[] args) {
        try {
            new ExamenURL();
        } catch (InterruptedException ex) {
            System.out.println("Valió barriga: "+ex.getMessage());
        }
    }
    
    public void obtenerEnlaces(String url) throws InterruptedException{
        try{
            Document doc =  Jsoup.connect(url).ignoreHttpErrors(false).get();
            Elements enlaces = doc.select("a[href^='http']");
            for(Element enlace : enlaces){
                pendientes.put(enlace.attr("href"));
            }
        }
        catch(IOException e){
            System.out.println("Valió barriga: "+e.getMessage());
        }
        
    }
}
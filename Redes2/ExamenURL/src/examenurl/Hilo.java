package examenurl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Hilo implements Runnable{
    private String url;
    private final ReentrantLock rl;
    private final ArrayList<String> registro;
    private final LinkedBlockingQueue<String> pendientes;
    private final int numHilo;
    
    public Hilo(ReentrantLock rl, ArrayList<String> registro, LinkedBlockingQueue<String> pendientes, int hilo) throws InterruptedException{
        this.rl = rl;
        this.registro = registro;
        this.pendientes = pendientes;
        this.numHilo = hilo;
    }
    
    @Override
    public void run() {
        while(true){
            this.url = pendientes.poll();
            try{
                rl.lock();
                if(this.url == null){
                    System.out.println("Fin de hilo: "+this.numHilo);
                    rl.unlock();
                    break;
                }
                rl.unlock();
                
                while(registro.contains(this.url)){
                    this.url = pendientes.poll();
                }
                
                Document doc =  Jsoup.connect(this.url).ignoreHttpErrors(false).get();
                Elements enlaces = doc.select("a[href^='http']");
                for(Element enlace : enlaces){
                    enlace.attr("href", analizador(enlace.attr("href"), "(?<=//).+")+".html");
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(analizador(this.url,"(?<=//).+")+".html"));
                writer.write(doc.outerHtml());
                writer.close();
                registro.add(this.url);
            }
            catch(IOException ex) {
                System.out.println("Valió barriga: "+ex.getMessage());
            }
        }
    }
    private String analizador(String com, String regex) {
        Pattern expreg = Pattern.compile(regex);
        Matcher buscador = expreg.matcher(com);
        if(buscador.find()){
            return buscador.group().replace('/', '-');
        }else{
            return "hue";
        }
    }
    
}
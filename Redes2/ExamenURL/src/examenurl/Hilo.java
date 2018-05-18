package examenurl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
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
    private final String url;
    private final ReentrantLock rl;
    private final ArrayList<String> registro;
    private final LinkedBlockingQueue<String> pendientes;
    
    public Hilo(String url, ReentrantLock rl, ArrayList<String> registro, LinkedBlockingQueue<String> pendientes){
        this.url = url;
        this.rl = rl;
        this.registro = registro;
        this.pendientes = pendientes;
    }
    
    @Override
    public void run() {
        try{
            rl.lock();
            if(registro.contains(this.url)){
                return;
            }
            rl.unlock();
            Document doc =  Jsoup.connect(this.url).ignoreHttpErrors(false).get();
            Elements enlaces = doc.select("a[href^='http']");
            for(Element enlace : enlaces){
                pendientes.put(enlace.attr("href"));
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(analizador(this.url,"(?<=//).+")+".html"));
            writer.write(doc.outerHtml());
            writer.close();
        }
        catch(IOException | InterruptedException ex) {
            System.out.println("Valió barriga: "+ex.getMessage());
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
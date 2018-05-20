package examenurl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        int profundidad = 0;
        rl = new ReentrantLock();
        registro = new ArrayList();
        pendientes = new LinkedBlockingQueue();
        System.out.println("Obteniendo enlaces");
        obtenerEnlaces(url, profundidad);
        System.out.println("Descargando... ");
        ExecutorService executor = Executors.newScheduledThreadPool(32);
        for(int i = 0; i < 32; i++){
            executor.execute(new Hilo(rl, registro, pendientes, i));
        }
        System.out.println("Esperando a que todo termine");
        executor.shutdown();
        while(!executor.isTerminated()){
            
        }
        System.out.println("Ya acabó");
        System.out.println("Enlaces descargados: "+registro.size());
    }
    public static void main(String[] args) {
        try {
            new ExamenURL();
        } catch (InterruptedException ex) {
            System.out.println("Valió barriga: "+ex.getMessage());
        }
    }
    
    public void obtenerEnlaces(String url, int profundidad){
        try{
            Document doc =  Jsoup.connect(url).ignoreHttpErrors(false).get();
            Elements enlaces = doc.select("a[href^='http']");
            for(Element enlace : enlaces){
                pendientes.put(enlace.attr("href"));
                if(profundidad != 0){
                    obtenerEnlaces(enlace.attr("href"), profundidad - 1);    
                }
            }
            System.out.println("Enlaces obtenidos: "+pendientes.size());
            for(Element enlace: enlaces){
                enlace.attr("href", analizador(enlace.attr("href"), "(?<=//).+")+".html");
            }
            
            Elements archivosjs = doc.select("script");
            for(Element js : archivosjs){
                String ruta = js.attr("src");
                String nuevaRuta = "descargas\\assets\\js\\"+ruta.substring(ruta.lastIndexOf("/")+1);
                
                URL url2 = new URL(url+"/"+ruta);
                try{
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url2.openStream()));
                
                    BufferedWriter writer = new BufferedWriter(new FileWriter(nuevaRuta));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                    }
                    js.attr("src", "assets/js/"+ruta.substring(ruta.lastIndexOf("/")+1));
                    reader.close();
                    writer.close();
                }
                catch(Exception ex){
                    continue;
                }
            }
            
            Elements archivosCSS = doc.select("link");
            for(Element css : archivosCSS){
                String ruta = css.attr("href");
                String nuevaRuta = "descargas\\assets\\css\\"+ruta.substring(ruta.lastIndexOf("/")+1);

                URL url2 = new URL(url+"/"+ruta);
                
                try{
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url2.openStream()));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(nuevaRuta));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                    }
                    css.attr("href", "assets/css/"+ruta.substring(ruta.lastIndexOf("/")+1));
                    reader.close();
                    writer.close();
                }catch(Exception e){
                    continue;
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter("descargas\\index.html"));
            writer.write(doc.outerHtml());
            writer.close();
        }
        catch(IOException e){
            System.out.println("No se pudo descargar: "+e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Valió barriga: "+e.getMessage());
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
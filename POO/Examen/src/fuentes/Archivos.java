package fuentes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Archivos {
    private Cliente c;
    private Auto a;

    public Archivos(Cliente c, Auto a) {
        this.c = c;
        this.a = a;
    }
    
    public boolean agendarCita(String fecha){
        File f = new File("/archivo.xml");
        SAXBuilder builder = new SAXBuilder();
        try{
            Document doc = (Document) builder.build(f);
            Element raiz = doc.getRootElement();
            List citas = raiz.getChildren();
            for (int i = 0; i < citas.size(); i++) {
                Element cita = (Element) citas.get(i);
                if(cita.getAttribute("fecha").getValue().equals(fecha) && cita.getAttribute("id").getValue().equals("48")){
                    return false;
                }else{
                    
                }
            }
        }
        catch(JDOMException | IOException e){
            
        }
        
    }
}

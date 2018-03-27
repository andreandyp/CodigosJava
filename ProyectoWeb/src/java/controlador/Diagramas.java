package controlador;

import java.io.FileWriter;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

class Diagramas {
    private String nombreXML;

    public Diagramas(String parametro) {
        nombreXML = parametro;
    }
    public void crearArchivo(String ruta) throws IOException{
        Element raiz = new Element("graficador");
        Element graf = new Element("grafica");
        graf.setAttribute("id", "0");
        graf.setAttribute("disponible", "false");
        graf.setAttribute("grupo", "0");
        raiz.addContent(graf);
	Document doc = new Document(raiz);
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        FileWriter archivo = new FileWriter(ruta+nombreXML+".xml");
	xmlOutput.output(doc, archivo);
        archivo.flush();
        archivo.close();
    }
    
}

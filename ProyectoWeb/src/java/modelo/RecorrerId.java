package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;



public class RecorrerId {
	
	public static void recorre(String ruta) throws IOException{
		
		try{
			SAXBuilder builder=new SAXBuilder();
			File archivoXML = new File(ruta);
			Document documento=builder.build(archivoXML);
			Element raiz=documento.getRootElement();
			List<Element> nodos= raiz.getChildren();
			for (int i = 0; i < nodos.size(); i++) {
				Element elemento= nodos.get(i);
				if (Integer.parseInt(elemento.getAttributeValue("id"))== i+1) {
					continue;
				}
				
				else{
					elemento.getAttribute("id").setValue(Integer.toString(i+1));
				}
			}
		
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(documento, new FileWriter(archivoXML));
			
			 
		}catch(JDOMException e){
            e.printStackTrace();
        }
		
	}

}

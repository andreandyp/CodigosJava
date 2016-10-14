
package fuentes;

import java.util.Date;

public class Figura {
    private String color;
    private boolean relleno;
    private Date fechaCreacion;
    
    protected Figura(){
        color = "#FFF";
        relleno = true;
        fechaCreacion = new Date();
    }
    
    public String obtenerColor(){
        return color;
    }
    
    public void establecerColor(String color){
        this.color = color;
    }
}

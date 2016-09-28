
package fuente;

/**
 *
 * @author alumno
 */
public class Circulo extends Formas {
    private double radio;

    public Circulo(double radio, int id, int sides, String color, String nombre) {
        super(id, sides, color, nombre);
        this.radio = radio;
    }
    
    public double getRadio(){
        return radio;
    }
    public void setRadio(double radio){
        this.radio = radio;
    }
    
}

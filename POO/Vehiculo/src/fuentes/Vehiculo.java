
package fuentes;


public class Vehiculo {
    protected String matricula;
    protected int modelo;
    protected int potenciaCV;

    public Vehiculo(String matricula, int modelo, int potenciaCV) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.potenciaCV = potenciaCV;
    }
    
    public Vehiculo(){
        matricula = "102-SNU";
        modelo = 2015;
        potenciaCV = 0;
    }
    
    public String getMatricula() {
        return matricula;
    }

    public int getModelo() {
        return modelo;
    }

    public int getPotenciaCV() {
        return potenciaCV;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public void setPotenciaCV(int potenciaCV) {
        this.potenciaCV = potenciaCV;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "matricula=" + matricula + ", modelo=" + modelo + ", potenciaCV=" + potenciaCV + '}';
    }
}

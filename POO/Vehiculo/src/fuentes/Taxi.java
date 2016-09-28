package fuentes;
public class Taxi extends Vehiculo {
    
    private String numeroLicencia;

    public Taxi(String numeroLicencia, String matricula, int modelo, int potenciaCV) {
        super(matricula, modelo, potenciaCV);
        this.numeroLicencia = numeroLicencia;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    @Override
    public String toString() {
        return "Taxi{" + "numeroLicencia=" + numeroLicencia + '}'+"\n"+super.toString();
    }
}
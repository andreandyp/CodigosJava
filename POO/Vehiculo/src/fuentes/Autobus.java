package fuentes;
public class Autobus extends Vehiculo {
    private int numeroPlazas;

    public int getNumeroPlazas() {
        return numeroPlazas;
    }

    public void setNumeroPlazas(int numeroPlazas) {
        this.numeroPlazas = numeroPlazas;
    }

    public Autobus(int numeroPlazas, String matricula, int modelo, int potenciaCV) {
        super(matricula, modelo, potenciaCV);
        this.numeroPlazas = numeroPlazas;
    }
    
}

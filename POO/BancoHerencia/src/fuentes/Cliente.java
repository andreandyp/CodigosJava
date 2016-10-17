package fuentes;
public class Cliente {
    private String nombre;
    private Cuenta cuentas[];
    private int numCuentas;
    
    public Cliente(String nom){
        nombre = nom;
        cuentas = new Cuenta[2];
        numCuentas = 0;
    }

    public String obtenerInfo() {
        return "Nombre: "+nombre+"\nNúmero de cuentas en uso: "+numCuentas;
    }
    
    public Cuenta obtenerCuenta(int index){
        return cuentas[index];
    }
    
    public void agregarCuenta(Cuenta cta){
        if(numCuentas == 2)
            System.out.println("Ya no se pueden tener mas cuentas");
        else
            cuentas[numCuentas++] = cta;
    }
    
    public int obtenerNumCtas(){
        return numCuentas;
    }
}

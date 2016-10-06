package fuentes;

import java.util.Random;

public class Cliente {
    private String nombre;
    private Cuenta cuenta; //Agregación
    private String numCuenta;
    
    public Cliente(String nom){
        nombre = nom;
        Random r = new Random();
        numCuenta = ""+r.nextInt();
    }
    
    public String obtenerNombre(){
        return nombre;
    }
    
    public Cuenta obtenerCuenta(){
        return cuenta;
    }
    
    public void establecerCuenta(Cuenta cta){
        cuenta = cta;
    }
    
    public String obtenerNumCta(){
        return numCuenta;
    }
}

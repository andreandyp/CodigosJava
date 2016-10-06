package fuentes;
public class Cuenta {
    private double saldo;
    
    public Cuenta(double saldoInicial){
        saldo = saldoInicial;
    }
    
    public double consultar(){
        return saldo;
    }
    
    public void depositar(double monto){
        if(monto > 0)
            saldo += monto;
        else
            System.out.println("No se puede depositar saldo negativo");
    }
    
    public void retirar(double monto){
        if(monto <= saldo && monto > 0)
            saldo -= monto;
        else
            System.out.println("Fondos insuficientes");
    }
}

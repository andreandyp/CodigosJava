package fuentes;
public class CuentaDeCheques extends Cuenta {
    protected double montoSobregiro;
    
    public CuentaDeCheques(double saldoIni,double sobregiro) {
        super(saldoIni);
        montoSobregiro = sobregiro;
    }
    
    public CuentaDeCheques(double saldoIni) {
        super(saldoIni);
    }
    
    
}

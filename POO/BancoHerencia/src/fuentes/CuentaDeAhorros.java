package fuentes;
public class CuentaDeAhorros extends Cuenta {
    private double tasaDeInteres;

    public CuentaDeAhorros(double saldoIni,double tasaInteres) {
        super(saldoIni);
        tasaDeInteres = tasaInteres;
    }
}
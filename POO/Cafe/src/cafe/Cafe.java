package cafe;
public class Cafe {
    public static void main(String[] args) {
        Orden o1 = new Orden(46,"09/30/2016");
        o1.enviarOrden();
        o1.calcularCosto();
        o1.entrega();
        System.out.println(o1);
    }    
}
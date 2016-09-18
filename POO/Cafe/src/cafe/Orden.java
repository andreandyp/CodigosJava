package cafe;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class Orden {
    private int bolsas;
    private int cajaG;
    private int cajaM;
    private int cajaC;
    private double total;
    private LocalDate fechaPedido;
    private LocalDate fechaEntrega;
    private final static double COSTO_BOLSA = 5.5;
    private final static double COSTO_GRANDE = 2.0;
    private final static double COSTO_MEDIANA = 1.0;
    private final static double COSTO_CHICA = 0.5;
    
    
    public Orden(int bolsas, String fechaPedido){
        this.bolsas = bolsas;
        this.fechaPedido = LocalDate.parse(fechaPedido, DateTimeFormatter.ofPattern("M/d/y"));
    }
    public void enviarOrden(){
        int temp = bolsas;
        cajaG = temp/20;
        temp = temp%20;
        cajaM = temp/10;
        temp = temp%10;
        cajaC = temp/5;
        if(temp%5 > 0)
            cajaC++;
    }
    public void calcularCosto(){
        total =  bolsas*COSTO_BOLSA;
        total = total+(cajaG*COSTO_GRANDE)+(cajaM*COSTO_MEDIANA)+(cajaC*COSTO_CHICA);
    }
    public void entrega(){
        fechaEntrega = fechaPedido.plus(14, ChronoUnit.DAYS);
    }
    @Override
    public String toString(){
        return "Bolsas ordenadas: "+bolsas+" - $"+bolsas*COSTO_BOLSA+"\n"+
                "Cajas grandes: "+cajaG+" - $"+cajaG*COSTO_GRANDE+"\n"+
                "Cajas medianas: "+cajaM+" - $"+cajaM*COSTO_MEDIANA+"\n"+
                "Cajas chicas: "+cajaC+" - $"+cajaC*COSTO_CHICA+"\n"+
                "Total: "+total+"\n"+
                "Fecha de entrega: "+fechaEntrega.format(DateTimeFormatter.ofPattern("M/d/y"));
    }
}

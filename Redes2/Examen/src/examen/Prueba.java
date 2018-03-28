package examen;
public class Prueba {
    public byte pequeñito;
    public short pequeño;
    public int numero;
    public long grande;
    public float flotante;
    public double decimal;
    public boolean vf;
    public char inicial;
    public String cadena;

    public Prueba(byte pequeñito, short pequeño, int numero, long grande, float flotante, double decimal, boolean vf, char inicial, String cadena) {
        this.pequeñito = pequeñito;
        this.pequeño = pequeño;
        this.numero = numero;
        this.grande = grande;
        this.flotante = flotante;
        this.decimal = decimal;
        this.vf = vf;
        this.inicial = inicial;
        this.cadena = cadena;
    }
    
    public Prueba(){
        
    }

    @Override
    public String toString() {
        return "pequeñito: " + pequeñito + " pequeño: " + pequeño + ", numero: " + numero + ", grande: " + grande + ", flotante: " + flotante + ", decimal: " + decimal + ", vf: " + vf + ", inicial: " + inicial + ", cadena: " + cadena;
    }
    
}

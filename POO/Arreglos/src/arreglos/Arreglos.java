package arreglos;
public class Arreglos {
    public static void main(String[] args) {
        int canales[] = new int[10];
        String cadena;
        for (int i = 0; i < canales.length; i++) {
            cadena = String.format("Canales[%d] = "+canales[i],i);
            //System.out.println(hue);
        }
        int canales2[] = {2,4,5,7,9,11,13,22,28,34,40};
        for (int i = 0; i < canales2.length; i++) {
            System.out.println(canales2);
        }
        int canales3[] = new int[]{2,4,5,7,9,11,13,22,28,34,40};
    }    
}
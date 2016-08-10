package primerjava;
public class PrimerJava {
    public static void main(String[] args) {
        int x = 15;
        System.out.println("x = "+x);
        short s = 043;
        System.out.println("s = "+s);
        long grandote = 0xF1;
        System.out.println("Número hexadecimal: "+grandote);
        byte b = 127;
        System.out.println("Número chiquito: "+b);
        int r = s+b;
        short r2 = (short) (s+b);
        long l2 = (long) 100e6;
        System.out.println(l2);
    }
}

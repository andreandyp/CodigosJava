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
        //Caracteres:
        char c = 64;
        System.out.println(c+"AndreAndyP");
        char c2 = '\u0040';
        System.out.println(c2+"AndreAndyP");
        char c3 = '@';
        System.out.println(c3+"AndreAndyP");
        
        Boolean b2,b3,b4,b5;
        b2 = true;
        b3 = false;
        b4 = b2 || b3;
        b5 = b4 != false;
        System.out.println(b4);
        System.out.println(b5);
        if(b5){
            System.out.println("b5 = "+b5);
        }
        int i1 = 17, i2 = 18;
        boolean b6 = i1 == i2;
        System.out.println(b6);
        float f = 1.2e6f;
        System.out.println(f);
        System.out.println(Float.MAX_VALUE);
        /*int i4 = Integer.MAX_VALUE+1;
        System.out.println(i4);*/
        double d1,d2;
        d1 = 5.3;
        d2 = 2.0;
        System.out.println("d1 % d2 = "+(d1%d2));
    }
}

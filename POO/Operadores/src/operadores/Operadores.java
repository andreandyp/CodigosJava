package operadores;
public class Operadores {
    public static void main(String[] args) {
        int x = -128;
        int y = 4;
        int resultado = x>>y;
        System.out.println("Resultado: "+resultado);
        //<< Duplicador >> Divisor
        
        short x2 = 5;
        short resultado2 = (short) ~x2;
        System.out.println("Resultado: "+resultado2);
        //Sumar 1 y cambiar de signo
        
        float f1 = 17.5F;
        float f2 = 5.0F;
        float resultado3 = f1%f2;
        System.out.println("Resultado: "+resultado3);
        //El modulo tambien trabaja con floats
        
        boolean a = true;
        boolean b = false;
        boolean resultado4 = (a != b) || (b = true);
        boolean resultado5 = (a == b) && (b = true);
        
        System.out.println("Resultado: "+resultado4);
        System.out.println("Resultado: "+resultado5);
        System.out.println("B: "+b);
        //&&, || de corto circuito, miwntras que | y & evalua toda la expresion
        
        int i = 14;
        int j = 15;
        int resultado6 = ++i/j++;
        System.out.println("Resultado: "+resultado6++);
        System.out.println("Resultado: "+resultado6);
        //++x pre, x++ post
        
        int resultado7 = (i!=j)?i:j;
        System.out.println("Resultado: "+resultado7);
        //Operador trinario
        
        int m = 7;
        int n = 16;
        System.out.println("AND: "+(m&n));
        System.out.println("OR: "+(m|n));
        System.out.println("XOR: "+(m^n));
        
        int p = 1;
        int q = 6;
        int r = 3;
        int s = (++p/q) | r;
        System.out.println(s);
        System.out.println(Math.pow(p,2));
    }    
}
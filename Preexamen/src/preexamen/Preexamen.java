package preexamen;

import java.util.Scanner;

public class Preexamen {
    public static void main(String[] args) {
        String cadena;
        
        String [] arreglo1;
        int [] arreglo2;
        int letra = 0;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingresa una frase: ");
        cadena = teclado.nextLine();
        arreglo1 = cadena.split(" ");
        arreglo2 = new int[arreglo1.length];
        
        for (int i = 0; i < arreglo1.length; i++) {
            letra = arreglo1[i].charAt(0);
        }        
        
    }
}

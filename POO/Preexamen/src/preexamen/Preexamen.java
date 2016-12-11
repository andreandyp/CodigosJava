package preexamen;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Preexamen {
    public static void main(String[] args) {
        String cadena;
        
        String [] arreglo1,ordenadas;
        int [] arreglo2;
        int letra = 0;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingresa una frase: ");
        cadena = teclado.nextLine();
        arreglo1 = cadena.split(" ");
        arreglo2 = new int[arreglo1.length];
        ordenadas = new String[arreglo1.length];
        
        for (int i = 0; i < arreglo1.length; i++)
            arreglo2[i] = arreglo1[i].charAt(0);
        Arrays.sort(arreglo2);
        for (int i = 0; i < arreglo2.length; i++)
            System.out.println(arreglo2[i]);
        for(int j = 0; j <arreglo2.length; j++){
            for (int i = 0; i < arreglo1.length; i++) {
                if(arreglo1[i].charAt(0) == arreglo2[j]){
                    ordenadas[j] = arreglo1[i];
                }
            }
        }
        for(String hue : ordenadas){
            System.out.println(hue);
        }
    }
}

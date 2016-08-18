package cadenas;

import java.util.Locale;
import java.util.Calendar;
   import java.util.GregorianCalendar;
   import static java.util.Calendar.*;

public class Cadenas {
    public static void main(String[] args) {
        /*String juegos = "Rio 2016";
        String juegosSig = new String("Tokio 2020");
        System.out.println("JJOO de "+juegos);
        System.out.println("Proximos JJOO de "+juegosSig);
        System.out.println("Juegos en mayuscula: "+juegos.toUpperCase());
        juegos = juegos.toUpperCase();
        System.out.println("Juegos en mayuscula: "+juegos);
        String cosas[] = juegos.split(" ");
        for (int i = 0; i < cosas.length; i++) {
            System.out.println(cosas[i]);
        }*/
        
        /*String nombre1 = "André Michel Pozos Nieto";
        String sub = nombre1.substring(6);
        System.out.println(sub);
        sub = nombre1.substring(6,12); //Le resta 1 al segundo indice
        System.out.println(sub);*/
        
        /*String mail = "andreandyp@outlook.com";
        mail = mail.replace('@', '#');
        System.out.println(mail);
        mail = mail.replace("outlook","gmail");
        System.out.println(mail);
        int i = mail.indexOf('a');
        System.out.println("Posicion de a: "+i);
        int j = mail.lastIndexOf('a');
        System.out.println("Posicion de a: "+j);*/
        
        //Obtener todas las posiciones del caracter '' usando indexOf
        /*char correo[] = "jgarciasa@ipn.mx".toCharArray();
        for (int i = 0; i < correo.length; i++) {
            if(correo[i] == 'a')
                System.out.println("Posicion de a: "+i);
        }*/
        
        /*String cad1 = "Michael";
        String cad2 = "Michael";
        String cads1 = new String("Michael");
        String cads2 = new String("Michael");
        if(cads1.equals(cads2))
            System.out.println("Iguales");
        else
            System.out.println("Distintas");*/
        
        

        Calendar c = new GregorianCalendar(1997, SEPTEMBER, 3);
        String nombre = "André";
        int edad = 18;
        
        String formateada  = String.format("Hola, soy %s, tengo %d años.", nombre,edad);
        System.out.println(formateada);
        String fecha = String.format("Nací el %1$te / %1$tm / %1$ty", c);
        System.out.println(fecha);
    }
}
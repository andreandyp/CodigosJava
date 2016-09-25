package fechanacimiento;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class FechaNacimiento {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int año,mes,dia;
        System.out.println("Ingresa tu año de nacimiento");
        año = teclado.nextInt();
        System.out.println("Ahora el mes (en número)");
        mes = teclado.nextInt();
        System.out.println("...y el día");
        dia = teclado.nextInt();
        Calendar fecha = GregorianCalendar.getInstance();
        //Obtener la diferencia de fechas
        fecha.add(Calendar.YEAR, -año);
        fecha.add(Calendar.MONTH, -mes);
        fecha.add(Calendar.DAY_OF_MONTH, -dia);
        //Pasar la fecha a dias (con un pequeño margen de error)
        int dias = (fecha.get(Calendar.YEAR)*365)+(fecha.get(Calendar.MONTH)*30)+fecha.get(Calendar.DAY_OF_MONTH)+4;
        if(dias >= 6574) //18 años *365 dias + 4 dias de años bisiestos
            System.out.println("Eres mayor de edad :D llevas "+dias+" en este mundo");
        else
            System.out.println("No eres mayor de edad :( llevas "+dias+" en este mundo");
    }    
}
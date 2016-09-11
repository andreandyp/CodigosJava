package pruebas;
import fuentes.Army;
import fuentes.Robot;

public class TestArmy {
    public static void main(String[] args) {    
        Army myArmy = new Army("Trigarante");
        Robot r1 = new Robot(0xA, "Vicente Guerrebot", 70.0, true);
        Robot r2 = new Robot(0xB, "Agustín 001", 72.0, false);
        Robot r3 = new Robot(0xC, "Juan O'Donojú", 68.0, false);
        Robot r4 = new Robot(0xD, "Miguel Hidalgo", 65.0, true);
        Robot r5 = new Robot(0xE, "Juan Aldama", 75.0, false);
        myArmy.agregarRobot(r1);
        myArmy.agregarRobot(r2);
        System.out.println("Army "+myArmy);
        myArmy.quitarRobot();
        System.out.println("Army "+myArmy);
        myArmy.quitarRobot();
        System.out.println("Army "+myArmy);
        myArmy.quitarRobot();
        System.out.println("myArmy" +myArmy);
        myArmy.quitarRobot();
        System.out.println("myArmy" +myArmy);
    }
}
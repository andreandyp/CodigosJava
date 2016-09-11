package pruebas;
import fuentes.Robot;
public class Prueba {
    public static void main(String[] args) {        
        
        Robot myRobot = new Robot(030, "RC-38", 14.4, true);    
        System.out.println("myRobot=" + myRobot.toString());
        System.out.println("id=" + myRobot.getId());
        System.out.println("name=" + myRobot.getName());
        System.out.println("weight="+myRobot.getWeigth());
        System.out.println("attack Mode = " + myRobot.isAttackMode());
        myRobot.setId(0xF);
        myRobot.setName("Mazinger Z");
        myRobot.setWeigth(2000);
        myRobot.setAttackMode(true);
        System.out.println("myRobot = " + myRobot.toString() );
    }    
}
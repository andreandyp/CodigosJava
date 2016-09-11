
package fuentes;

public class Army {
    
    private Robot[] army;
    private String name;
    private int index;

    public Army(String name) {
        //se inicializa con valores null (aparta el lugarsito)
        this.army = new Robot[5];
        this.name = name;
    }

    @Override
    public String toString() {
        String temp = "{ name=" + name+"\n";
        for (int i = 0; i < army.length; i++) {
            if(army[i] != null){
                temp = temp + army[i].toString() +"\n";
            }
        }
        return temp;
    }
    public boolean agregarRobot(Robot r){
        if (index < army.length) {
            this.army[index++] = r;
            return true;
        }else{
            System.out.println("Ya no caben");
            return false;
        }
    }
    public boolean quitarRobot(){
        if (index >= 0) {
            army[index--] = null;
            index = (index < 0)?++index:index;
        }
        else{
            System.out.println("Esta vacío");
        }
        return false;
    }
}

package fuentes;

public class Robot {
    private int id;
    private String name;
    private double weigth;
    private boolean attackMode;

    public Robot(int id, String name, double weigth, boolean attackMode) {
        this.id = id;
        this.name = name;
        this.weigth = weigth;
        this.attackMode = attackMode;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWeigth() {
        return weigth;
    }

    public boolean isAttackMode() {
        return attackMode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeigth(double weigth) {
        this.weigth = weigth;
    }

    public void setAttackMode(boolean attackMode) {
        this.attackMode = attackMode;
    }

    
    @Override
    public String toString() {
        return "Robot{" + "id=" + id + ", name=" + name + ", weigth=" + weigth + ", attackMode=" + attackMode + '}';
    }
    
    
}

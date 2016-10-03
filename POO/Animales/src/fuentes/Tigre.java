package fuentes;
public class Tigre extends Mamífero {

    public Tigre(String temperamento) {
        super(temperamento);
        System.out.println("Tigre");
    }

    public Tigre() {
        System.out.println("Tigre");
    }
    
    @Override
    public String toString() {
        return "Me dicen Tigre B| {" + '}';
    }
    public String toString(String s){
        return toString()+" "+s;
    }
}

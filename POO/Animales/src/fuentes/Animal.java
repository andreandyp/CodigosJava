package fuentes;
public class Animal {
    public String temperamento;

    public Animal(String temperamento) {
        System.out.println("Un argumento");
        this.temperamento = temperamento;
    }
    public Animal(){
        System.out.println("Sin argumentos");
    }
}

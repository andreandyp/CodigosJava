package fuentes;
public abstract class Animal {
    protected int piernas;

    public Animal(int piernas) {
        this.piernas = piernas;
    }
    
    public abstract void comer();
    
    public void caminar(){
        System.out.println("Estoy caminando usando mis "+piernas+" piernas");
    }
}

package fuentes;

public class OtroHilo implements Runnable{
    @Override
    public void run(){
        System.out.println("Hola "+Thread.currentThread().getName());
    }
}

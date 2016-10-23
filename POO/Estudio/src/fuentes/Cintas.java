package fuentes;
public class Cintas implements Grabador {
    @Override
    public void reproducir() {
        System.out.println("Reproduciendo DVD");
    }

    @Override
    public void detener() {
        System.out.println("Detenido");
    }

    @Override
    public void pausar() {
        System.out.println("Pausa");
    }

    @Override
    public void retroceder() {
        System.out.println("Retrocediendo DVD");
    }
    
    @Override
    public void grabar(){
        System.out.println("Grabando...");
    }
}

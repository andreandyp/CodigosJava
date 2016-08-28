package tv;
public class Television {
    private final int canales[] = {2,4,5,7,9,11,13,22,28,34,40};
    private int vol;
    private int canal;
    private boolean encendida;
    //Duda sobre private
    final int resX;
    final int resY;

    public Television(int vol, int canal, boolean encendida, int resX, int resY) {
        this.vol = vol;
        this.canal = canal;
        this.encendida = encendida;
        this.resX = resX;
        this.resY = resY;
    }

    public Television() {
        this.vol = 20;
        this.canal = 5;
        this.encendida = true;
        this.resX = 1920;
        this.resY = 1080;
    }
    
    public boolean encender(){
        this.encendida = !encendida;
        return encendida;
    }
    
    public void subirVolumen(int svol){
        vol = vol + svol;
    }
    public void subirVolumen(){
        vol++;
    }
    public void bajarVolumen(int bvol){
        vol = vol - bvol;
    }
    public void bajarVolumen(){
        vol--;
    }
    
    public void cambiarCanal(int canal){
        String mensaje;
        for(int c : canales){
            if(c == canal){
                this.canal = canal;
                System.out.println("Canal establecido");
                return;
            }
        }
        System.out.println("No se encuentra el canal");
    }
    
    public void cambiarCanal(boolean pos){
        for (int i = 0; i < canales.length; i++) {
            if(canal == canales[i]){
                if(pos){
                    if(i <= canales.length)
                        canal = canales[0];
                    else
                        canal = canales[++i];
                }
                else{
                    if(i == 0)
                        
                        canal = canales[canales.length - 1];
                    else
                        canal = canales[--i];
                }
                return;
            }
        }
            
    }
    @Override
    public String toString() {
        return "Television{" + "vol=" + vol + ", canal=" + canal + ", encendida=" + encendida + ", resX=" + resX + ", resY=" + resY + '}';
    }
}

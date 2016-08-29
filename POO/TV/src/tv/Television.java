package tv;
public class Television {
    private final int canales[] = {2,4,5,7,9,11,13,22,28,34,40};
    private int vol;
    private int canal;
    private boolean encendida;
    //Duda sobre private
    private final int resX;
    private final int resY;

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
        if (vol < 100) 
            vol = vol + svol;
    }
    public void subirVolumen(){
        if(vol < 100)
            vol++;
    }
    public void bajarVolumen(int bvol){
        if(vol >= 0)
            vol = vol - bvol;
    }
    public void bajarVolumen(){
        if(vol >= 0)
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
        String lista = "";
        for(int canal : canales){
            lista = lista+canal+",";
        }
        return "Television{" + "vol=" + vol + ", canal=" + canal + ", encendida=" + encendida + ", resX=" + resX + ", resY=" + resY + '}';
        
    }
    /*
        subirCanal()
        if(indice >= validChannels.length)
            indice = -1;
            channel = validChannels[++indice];
        bajarCanal()
        if(indice == 0)
            indice = validChannels.length;
        channel = validChannels[--indice];
    */
}

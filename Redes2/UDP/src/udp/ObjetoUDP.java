package udp;

import java.io.Serializable;
import java.util.Arrays;

class ObjetoUDP implements Serializable{
    int n, total;
    byte[] mensaje;
    
    public ObjetoUDP(int n, int t, byte[] b){
        this.n = n;
        this.total = t;
        this.mensaje = Arrays.copyOf(b, b.length);
    }
    
    public int getN(){
        return this.n;
    }
    
    public int getTotal(){
        return this.total;
    }
    
    public byte[] getB(){
        return this.mensaje;
    }
}

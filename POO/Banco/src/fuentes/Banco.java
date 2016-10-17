package fuentes;
public class Banco {
    private Cliente[] clientes;
    private int numClientes;
    
    public Banco(){
        numClientes = -1;
        clientes = new Cliente[5];
    }
    
    public void agregaCliente(String nom){
        Cliente c = new Cliente(nom);
        Cuenta cta = new Cuenta(50000.0);
        c.establecerCuenta(cta);
        if(numClientes == 4)
            System.out.println("Ya no se pueden agregar mas clientes");
        else
            clientes[++numClientes] = c;
    }
    
    public int obtenerNumClientes(){
        return numClientes;
    }
    public Cliente obtenerCliente(int indice){
        if(indice >= 0 && indice < clientes.length)
            return clientes[indice];
        return null;
    }
    public void imprimirClientes(){
        for (int i = 0; i <= numClientes; i++) {
            Cliente cliente = clientes[i];
            System.out.println("Nombre: "+cliente.obtenerNombre());
            System.out.println("Saldo: "+cliente.obtenerCuenta().consultar());
            System.out.println("NumCuenta: "+cliente.obtenerNumCta());
        }
    }
}
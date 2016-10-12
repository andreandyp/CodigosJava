package fuentes;
public class Banco {
    private Cliente[] clientes;
    private int numClientes;
    
    public Banco(){
        numClientes = -1;
        clientes = new Cliente[5];
    }
    
    public void agregaCliente(String nom, String numCta){
        Cliente c = new Cliente(nom);
        Cuenta cta = new Cuenta(50000.0);
        //clientes[++numClientes];x
        /*Falta método para agregar clientes*/
    }
    
    public int obtenerNumClientes(){
        return numClientes;
    }
    public Cliente obtenerCliente(int indice){
        if(indice >= 0 && indice < clientes.length)
            return clientes[indice];
        return null;
    }
    public void imprimirCliente(){
        for (int i = 0; i < numClientes; i++) {
            Cliente cliente = clientes[i];
            System.out.println("Nombre: "+cliente.obtenerNombre());
            System.out.println("Saldo: "+cliente.obtenerCuenta().consultar());
            System.out.println("NumCuenta: "+cliente.obtenerNumCta());
        }
    }
}
package zootopia;
public class Zootopia {
    public static void main(String[] args) {
        Conejo conejo1 = new Conejo();
        conejo1.nombre = "Hops";
        conejo1.velocidad = 30;
        System.out.println(conejo1);
        
        Perezoso perezoso1 = new Perezoso();
        perezoso1.setNombre("Flash");
        perezoso1.velocidad = 1;
        System.out.println(perezoso1);
        
        
    }
    /*
    Public para TODOS lados
    Sin public solo para el paquete
    
    {Campos en privado
    Metodos en publico}
    Y eso mis camaradas es encapsulamiento
    */
}
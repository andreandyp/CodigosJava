package fuentes;
public class CuartoHotel {
    private int num;
    private int capacidad;
    private boolean ocupada;
    private double tarifa;
    private String descripcion;

    //Inicializa los campos
    /*
    * @param num Numero de cuaro
    * @param capacidad Personas que caben
    * @param ocupada Saber si esta ocupada o no
    * @param tarifa Lo que cuesta el cuarto
    * @param descripcion Servicios que incluye
    */

    public CuartoHotel(int num, int capacidad, boolean ocupada, double tarifa, String descripcion) {
        this.num = num;
        this.capacidad = capacidad;
        this.ocupada = ocupada;
        this.tarifa = tarifa;
        this.descripcion = descripcion;
    }

    public CuartoHotel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "CuartoHotel{" + "num=" + num + ", capacidad=" + capacidad + ", ocupada=" + ocupada + ", tarifa=" + tarifa + ", descripcion=" + descripcion + '}';
    }
}
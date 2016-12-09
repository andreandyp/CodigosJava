package fuentes;
public class Auto {
    private String marca;
    private String modelo;
    private String placas;

    public Auto(String marca, String modelo, String placas) {
        this.marca = marca;
        this.modelo = modelo;
        this.placas = placas;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }
    
}

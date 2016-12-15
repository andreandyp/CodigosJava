package ine;
class CasillaINE {
    private BoletaElectoral [] boletas;
    private int numVoto;
    private String tipo;
    
    public CasillaINE() {
        boletas = new BoletaElectoral[3];
        numVoto = 0;
    }

    public BoletaElectoral[] getBoletas() {
        return boletas;
    }

    public void emitirVoto(BoletaElectoral be) {
        be.setClaveBoleta(numVoto);
        boletas[numVoto] = be;
        ++numVoto;
    }

    public int getNumVoto() {
        return numVoto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}

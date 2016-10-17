package pruebas;

import fuentes.Banco;

public class Prueba {
    public static void main(String[] args) {
        Banco banco = new Banco();
        banco.agregaCliente("Andy1");
        banco.agregaCliente("Andy2");
        banco.agregaCliente("Andy3");
        banco.agregaCliente("Andy4");
        banco.agregaCliente("Andy5");
        banco.imprimirClientes();
        banco.agregaCliente("Andy6");
    }
}

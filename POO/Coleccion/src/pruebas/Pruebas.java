package pruebas;

import fuentes.ColeccionCadenas;

public class Pruebas {
    public static void main(String[] args) {
        ColeccionCadenas cadenas;
        cadenas = new ColeccionCadenas();
        cadenas.agregarNombre("Niña que estudia 1");
        cadenas.agregarNombre("Niña que estudia 2");
        cadenas.agregarNombre("Tocaya de Gisela");
        for(String nombre : cadenas.verNombres()){
            System.out.println("Nombre: "+nombre);
        }
        
        System.out.println();
        for (int i = 0; i < cadenas.verNombres().size(); i++) {
            System.out.println("Nombre: "+cadenas.verNombre(i));
        }
        cadenas.quitarNombre("Tocaya de Gisela");
        cadenas.agregarNombre("Gizela");
        System.out.println(cadenas.verNombre(2));
        cadenas.cambiar(1, "Arheli");
        System.out.println(cadenas.verNombre(1));
        if(cadenas.verNombres().contains("Niña que estudia 1")){
            System.out.println(cadenas.verNombre(1));
        }
        System.out.println("Indice de Gizela: "+cadenas.verNombres().indexOf("Samuel"));
    }
}

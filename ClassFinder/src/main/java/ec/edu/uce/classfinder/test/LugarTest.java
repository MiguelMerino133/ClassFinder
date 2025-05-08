package ec.edu.uce.classfinder.test;

import ec.edu.uce.classfinder.modelo.Lugar;

public class LugarTest {

    public static void main(String[] args) {

        Lugar lugar = new Lugar("L001", "Edificio A", "Edificio principal", "U001");


        lugar.setIdLugar("L002");
        lugar.setNombre("Edificio B");
        lugar.setDescripcion("Edificio secundario");
        lugar.setIdUniversidad("U002");


        System.out.println("ID del lugar: " + lugar.getIdLugar());
        System.out.println("Nombre: " + lugar.getNombre());
        System.out.println("Descripción: " + lugar.getDescripcion());
        System.out.println("ID de la universidad: " + lugar.getIdUniversidad());


        lugar.registrarLugar();
        lugar.modificarLugar();
        lugar.eliminarLugar();
    }

}

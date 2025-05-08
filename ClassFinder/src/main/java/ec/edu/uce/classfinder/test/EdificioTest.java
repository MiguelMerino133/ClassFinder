package ec.edu.uce.classfinder.test;

import ec.edu.uce.classfinder.modelo.Edificio;

public class EdificioTest {

    public static void main(String[] args) {

        Edificio edificio = new Edificio("E001", "Edificio A", "Edificio principal", "U001", 5, "Campus Norte");


        edificio.setIdLugar("E002");
        edificio.setNombre("Edificio B");
        edificio.setDescripcion("Edificio secundario");
        edificio.setIdUniversidad("U002");
        edificio.setNumeroPisos(6);
        edificio.setUbicacion("Campus Sur");


        System.out.println("ID del edificio: " + edificio.getIdLugar());
        System.out.println("Nombre: " + edificio.getNombre());
        System.out.println("Descripción: " + edificio.getDescripcion());
        System.out.println("ID de la universidad: " + edificio.getIdUniversidad());
        System.out.println("Número de pisos: " + edificio.getNumeroPisos());
        System.out.println("Ubicación: " + edificio.getUbicacion());


        edificio.registrarLugar();
        edificio.modificarLugar();
        edificio.eliminarLugar();
        edificio.asignarUbicacion();
        edificio.agregarEdificio();
    }

}

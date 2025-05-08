package ec.edu.uce.classfinder.test;

import ec.edu.uce.classfinder.modelo.Universidad;

public class UniversidadTest {

    public static void main(String[] args) {

        Universidad universidad = new Universidad("U001", "Universidad Central", "Dr. Carlos López", "123-456-7890");


        universidad.setIdUniversidad("U002");
        universidad.setNombreUniversidad("Universidad del Norte");
        universidad.setNombreRector("Dra. María González");
        universidad.setTelefono("987-654-3210");


        System.out.println("ID de la universidad: " + universidad.getIdUniversidad());
        System.out.println("Nombre de la universidad: " + universidad.getNombreUniversidad());
        System.out.println("Nombre del rector: " + universidad.getNombreRector());
        System.out.println("Teléfono: " + universidad.getTelefono());


        universidad.gestionarUsuario();
        universidad.gestionarReserva();
        universidad.gestionarLugar();
        universidad.gestionarEspacio();
    }

}

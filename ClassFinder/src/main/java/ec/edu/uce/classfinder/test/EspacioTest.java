package ec.edu.uce.classfinder.test;

import ec.edu.uce.classfinder.modelo.Espacio;

public class EspacioTest {

    public static void main(String[] args) {

        Espacio espacio = new Espacio("E001", "L001", "Aula 101", 30, "mediano");


        espacio.setIdEspacio("E002");
        espacio.setIdLugar("L002");
        espacio.setNombre("Aula 102");
        espacio.setCapacidad(40);
        espacio.setTamano("grande");


        System.out.println("ID del espacio: " + espacio.getIdEspacio());
        System.out.println("ID del lugar: " + espacio.getIdLugar());
        System.out.println("Nombre: " + espacio.getNombre());
        System.out.println("Capacidad: " + espacio.getCapacidad());
        System.out.println("Tamaño: " + espacio.getTamano());


        espacio.registrarEspacio();
        espacio.actualizarCapacidad();
        espacio.consultarEspacio();
    }

}

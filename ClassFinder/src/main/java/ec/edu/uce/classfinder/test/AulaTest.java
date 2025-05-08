package ec.edu.uce.classfinder.test;

import ec.edu.uce.classfinder.modelo.Aula;

public class AulaTest {

    public static void main(String[] args) {

        Aula aula = new Aula("A001", "L001", "Aula 101", 30, "mediano", "101A");


        aula.setIdEspacio("A002");
        aula.setIdLugar("L002");
        aula.setNombre("Aula 102");
        aula.setCapacidad(40);
        aula.setTamano("grande");
        aula.setNumeroAula("102B");


        System.out.println("ID del aula: " + aula.getIdEspacio());
        System.out.println("ID del lugar: " + aula.getIdLugar());
        System.out.println("Nombre: " + aula.getNombre());
        System.out.println("Capacidad: " + aula.getCapacidad());
        System.out.println("Tamaño: " + aula.getTamano());
        System.out.println("Número de aula: " + aula.getNumeroAula());


        aula.registrarEspacio();
        aula.actualizarCapacidad();
        aula.consultarEspacio();
        aula.asignarNumero();
    }

}

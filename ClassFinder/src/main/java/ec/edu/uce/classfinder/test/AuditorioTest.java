package ec.edu.uce.classfinder.test;

import ec.edu.uce.classfinder.modelo.Auditorio;

public class AuditorioTest {

    public static void main(String[] args) {

        Auditorio auditorio = new Auditorio("A001", "L001", "Auditorio Central", 100, "grande", true);


        auditorio.setIdEspacio("A002");
        auditorio.setIdLugar("L002");
        auditorio.setNombre("Auditorio Norte");
        auditorio.setCapacidad(120);
        auditorio.setTamano("extra grande");
        auditorio.setEquipoSonido(false);


        System.out.println("ID del auditorio: " + auditorio.getIdEspacio());
        System.out.println("ID del lugar: " + auditorio.getIdLugar());
        System.out.println("Nombre: " + auditorio.getNombre());
        System.out.println("Capacidad: " + auditorio.getCapacidad());
        System.out.println("Tamaño: " + auditorio.getTamano());
        System.out.println("Equipo de sonido: " + auditorio.isEquipoSonido());


        auditorio.registrarEspacio();
        auditorio.actualizarCapacidad();
        auditorio.consultarEspacio();
        auditorio.configurarEquipo();
    }

}

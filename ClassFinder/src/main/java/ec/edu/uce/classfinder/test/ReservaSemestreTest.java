package ec.edu.uce.classfinder.test;

import ec.edu.uce.classfinder.modelo.ReservaSemestre;
import java.util.Date;

public class ReservaSemestreTest {

    public static void main(String[] args) {

        ReservaSemestre reservaSemestre = new ReservaSemestre("RS001", new Date(), new Date(), "pendiente", "U001", "E001", "2025-1");


        reservaSemestre.setIdReserva("RS002");
        reservaSemestre.setFechaInicio(new Date());
        reservaSemestre.setFechaFin(new Date());
        reservaSemestre.setEstado("aprobada");
        reservaSemestre.setIdUsuario("U002");
        reservaSemestre.setIdEspacio("E002");
        reservaSemestre.setSemestre("2025-2");


        System.out.println("ID de la reserva: " + reservaSemestre.getIdReserva());
        System.out.println("Fecha de inicio: " + reservaSemestre.getFechaInicio());
        System.out.println("Fecha de fin: " + reservaSemestre.getFechaFin());
        System.out.println("Estado: " + reservaSemestre.getEstado());
        System.out.println("ID del usuario: " + reservaSemestre.getIdUsuario());
        System.out.println("ID del espacio: " + reservaSemestre.getIdEspacio());
        System.out.println("Semestre: " + reservaSemestre.getSemestre());


        reservaSemestre.registrarReserva();
        reservaSemestre.cancelarReserva();
        reservaSemestre.aprobarReserva();
        reservaSemestre.rechazarReserva();
        reservaSemestre.asignarSemestre();
    }

}

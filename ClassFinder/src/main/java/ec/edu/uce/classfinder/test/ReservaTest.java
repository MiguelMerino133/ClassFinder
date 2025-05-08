package ec.edu.uce.classfinder.test;

import ec.edu.uce.classfinder.modelo.Reserva;
import java.util.Date;

public class ReservaTest {

    public static void main(String[] args) {

        Reserva reserva = new Reserva("R001", new Date(), new Date(), "pendiente", "U001", "E001");


        reserva.setIdReserva("R002");
        reserva.setFechaInicio(new Date());
        reserva.setFechaFin(new Date());
        reserva.setEstado("aprobada");
        reserva.setIdUsuario("U002");
        reserva.setIdEspacio("E002");


        System.out.println("ID de la reserva: " + reserva.getIdReserva());
        System.out.println("Fecha de inicio: " + reserva.getFechaInicio());
        System.out.println("Fecha de fin: " + reserva.getFechaFin());
        System.out.println("Estado: " + reserva.getEstado());
        System.out.println("ID del usuario: " + reserva.getIdUsuario());
        System.out.println("ID del espacio: " + reserva.getIdEspacio());


        reserva.registrarReserva();
        reserva.cancelarReserva();
        reserva.aprobarReserva();
        reserva.rechazarReserva();
    }

}

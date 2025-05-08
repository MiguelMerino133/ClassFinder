package ec.edu.uce.classfinder.test;

import ec.edu.uce.classfinder.modelo.ReservaTemporal;
import java.util.Date;

public class ReservaTemporalTest {

    public static void main(String[] args) {

        ReservaTemporal reservaTemporal = new ReservaTemporal("RT001", new Date(), new Date(), "pendiente", "U001", "E001", 2);


        reservaTemporal.setIdReserva("RT002");
        reservaTemporal.setFechaInicio(new Date());
        reservaTemporal.setFechaFin(new Date());
        reservaTemporal.setEstado("aprobada");
        reservaTemporal.setIdUsuario("U002");
        reservaTemporal.setIdEspacio("E002");
        reservaTemporal.setDuracionHoras(4);


        System.out.println("ID de la reserva: " + reservaTemporal.getIdReserva());
        System.out.println("Fecha de inicio: " + reservaTemporal.getFechaInicio());
        System.out.println("Fecha de fin: " + reservaTemporal.getFechaFin());
        System.out.println("Estado: " + reservaTemporal.getEstado());
        System.out.println("ID del usuario: " + reservaTemporal.getIdUsuario());
        System.out.println("ID del espacio: " + reservaTemporal.getIdEspacio());
        System.out.println("Duración en horas: " + reservaTemporal.getDuracionHoras());


        reservaTemporal.registrarReserva();
        reservaTemporal.cancelarReserva();
        reservaTemporal.aprobarReserva();
        reservaTemporal.rechazarReserva();
        reservaTemporal.establecerDuracion();
        reservaTemporal.notificarExpiracion();
    }

}

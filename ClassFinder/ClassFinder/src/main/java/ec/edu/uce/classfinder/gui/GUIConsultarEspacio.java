package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Espacio;
import ec.edu.uce.classfinder.dominio.Lugar;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Interfaz gráfica para consultar un espacio en el sistema ClassFinder.
 * Utiliza {@link Universidad} para buscar los espacios.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIConsultarEspacio {

    private Scanner entradaTeclado = new Scanner(System.in);
    private Universidad universidad;

    /**
     * Constructor que recibe una instancia de {@link Universidad}.
     * @param universidad instancia de la universidad para gestionar los datos
     */
    public GUIConsultarEspacio(Universidad universidad) {
        this.universidad = universidad;
    }

    /**
     * Solicita el ID del espacio a consultar.
     * @return el ID del espacio ingresado
     */
    public String consultar() {
        System.out.println("\n=== CONSULTAR ESPACIO ===");
        String idEspacio;

        do {
            System.out.print("Ingrese ID del espacio a consultar (formato ESP-001): ");
            idEspacio = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idEspacio)) {
                System.out.println("Error: El ID debe tener el formato ESP-001.");
            }
        } while (!Validadores.esIdValido(idEspacio));

        return idEspacio;
    }

    /**
     * Muestra los detalles de un espacio basado en su ID.
     * @param idEspacio ID del espacio a consultar
     */
    public void mostrarEspacio(String idEspacio) {
        boolean encontrado = false;
        for (int i = 0; i < universidad.getNumLugares(); i++) {
            Lugar lugar = universidad.getLugares()[i];
            for (int j = 0; j < lugar.getNumEspacios(); j++) {
                Espacio espacio = lugar.getEspacios()[j];
                if (espacio.getIdEspacio().equals(idEspacio)) {
                    System.out.println("Consulta de Espacio - ID: " + idEspacio);
                    System.out.println("Nombre del espacio: " + espacio.getNombre());
                    System.out.println("Capacidad: " + espacio.getCapacidad());
                    System.out.println("Tamaño: " + espacio.getTamano());
                    encontrado = true;
                    break;
                }
            }
            if (encontrado) break;
        }
        if (!encontrado) {
            System.out.println("Espacio con ID " + idEspacio + " no encontrado.");
        }
    }
}
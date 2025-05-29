package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Lugar;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Interfaz gráfica para consultar un lugar en el sistema ClassFinder.
 * Utiliza {@link Universidad} para buscar los lugares.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIConsultarLugar {

    private Scanner entradaTeclado = new Scanner(System.in);
    private Universidad universidad;

    /**
     * Constructor que recibe una instancia de {@link Universidad}.
     * @param universidad instancia de la universidad para gestionar los datos
     */
    public GUIConsultarLugar(Universidad universidad) {
        this.universidad = universidad;
    }

    /**
     * Solicita el ID del lugar a consultar.
     * @return el ID del lugar ingresado
     */
    public String consultar() {
        System.out.println("\n=== CONSULTAR LUGAR ===");
        String idLugar;

        do {
            System.out.print("Ingrese ID del lugar a consultar (formato LUG-001): ");
            idLugar = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idLugar)) {
                System.out.println("Error: El ID debe tener el formato LUG-001.");
            }
        } while (!Validadores.esIdValido(idLugar));

        return idLugar;
    }

    /**
     * Muestra los detalles de un lugar basado en su ID.
     * @param idLugar ID del lugar a consultar
     */
    public void mostrarLugar(String idLugar) {
        boolean encontrado = false;
        for (int i = 0; i < universidad.getNumLugares(); i++) {
            Lugar lugar = universidad.getLugares()[i];
            if (lugar.getIdLugar().equals(idLugar)) {
                System.out.println("Consulta de Lugar - ID: " + idLugar);
                System.out.println("Nombre del lugar: " + lugar.getNombre());
                System.out.println("Descripción: " + lugar.getDescripcion());
                System.out.println("Espacios: ");
                System.out.println(lugar.consultarEspacios());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Lugar con ID " + idLugar + " no encontrado.");
        }
    }
}
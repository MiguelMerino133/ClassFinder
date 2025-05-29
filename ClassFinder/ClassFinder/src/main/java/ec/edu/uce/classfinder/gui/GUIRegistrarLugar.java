package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Lugar;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Interfaz gráfica para registrar un lugar en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIRegistrarLugar {

    private Scanner entradaTeclado = new Scanner(System.in);

    /**
     * Permite registrar un nuevo lugar.
     * @return el lugar creado con los datos ingresados
     */
    public Lugar registrar() {
        System.out.println("\n=== REGISTRAR LUGAR ===");
        Lugar lugar = new Lugar();
        String idLugar, nombre, descripcion;

        do {
            System.out.print("Ingrese ID del lugar (formato LUG-001): ");
            idLugar = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idLugar)) {
                System.out.println("Error: El ID debe tener el formato LUG-001.");
            }
        } while (!Validadores.esIdValido(idLugar));
        lugar.setIdLugar(idLugar);

        do {
            System.out.print("Ingrese nombre del lugar: ");
            nombre = entradaTeclado.nextLine();
            if (!Validadores.esTextoValido(nombre)) {
                System.out.println("Error: El nombre no puede estar vacío.");
            }
        } while (!Validadores.esTextoValido(nombre));
        lugar.setNombre(nombre);

        do {
            System.out.print("Ingrese descripción del lugar: ");
            descripcion = entradaTeclado.nextLine();
            if (!Validadores.esTextoValido(descripcion)) {
                System.out.println("Error: La descripción no puede estar vacía.");
            }
        } while (!Validadores.esTextoValido(descripcion));
        lugar.setDescripcion(descripcion);

        return lugar;
    }
}
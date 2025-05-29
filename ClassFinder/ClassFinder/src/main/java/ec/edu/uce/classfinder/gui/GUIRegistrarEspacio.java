package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Espacio;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Interfaz gráfica para registrar un espacio en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIRegistrarEspacio {

    private Scanner entradaTeclado = new Scanner(System.in);

    /**
     * Permite registrar un nuevo espacio.
     * @return el espacio creado con los datos ingresados
     */
    public Espacio registrar() {
        System.out.println("\n=== REGISTRAR ESPACIO ===");
        Espacio espacio = new Espacio();
        String idEspacio, nombre, tamano;

        do {
            System.out.print("Ingrese ID del espacio (formato ESP-001): ");
            idEspacio = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idEspacio)) {
                System.out.println("Error: El ID debe tener el formato ESP-001.");
            }
        } while (!Validadores.esIdValido(idEspacio));
        espacio.setIdEspacio(idEspacio);

        do {
            System.out.print("Ingrese nombre del espacio: ");
            nombre = entradaTeclado.nextLine();
            if (!Validadores.esTextoValido(nombre)) {
                System.out.println("Error: El nombre no puede estar vacío.");
            }
        } while (!Validadores.esTextoValido(nombre));
        espacio.setNombre(nombre);

        int capacidad;
        do {
            System.out.print("Ingrese capacidad (mínimo 1): ");
            if (entradaTeclado.hasNextInt()) {
                capacidad = entradaTeclado.nextInt();
                entradaTeclado.nextLine();
                if (!Validadores.esCapacidadValido(String.valueOf(capacidad))) {
                    System.out.println("Error: La capacidad debe ser un número positivo.");
                }
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                capacidad = 0;
                entradaTeclado.nextLine();
            }
        } while (!Validadores.esCapacidadValido(String.valueOf(capacidad)));
        espacio.setCapacidad(capacidad);

        do {
            System.out.print("Ingrese tamaño (pequeño/mediano/grande): ");
            tamano = entradaTeclado.nextLine();
            if (!Validadores.esTamanoValido(tamano)) {
                System.out.println("Error: El tamaño debe ser 'pequeño', 'mediano' o 'grande'.");
            }
        } while (!Validadores.esTamanoValido(tamano));
        espacio.setTamano(tamano);

        return espacio;
    }
}
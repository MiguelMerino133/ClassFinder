package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.modelo.Espacio;
import java.util.Scanner;

public class GUIRegistrarEspacio {

    private Scanner entradaTeclado = new Scanner(System.in);

    public Espacio registrar() {
        System.out.println("\n=== REGISTRAR ESPACIO ===");
        Espacio espacio = new Espacio();
        System.out.print("Ingrese ID del espacio: ");
        espacio.setIdEspacio(entradaTeclado.nextLine());
        System.out.print("Ingrese ID del lugar: ");
        espacio.setIdLugar(entradaTeclado.nextLine());
        System.out.print("Ingrese nombre del espacio: ");
        espacio.setNombre(entradaTeclado.nextLine());
        System.out.print("Ingrese capacidad (número de personas): ");
        espacio.setCapacidad(Integer.parseInt(entradaTeclado.nextLine()));
        System.out.print("Ingrese tamaño (pequeño/mediano/grande): ");
        espacio.setTamano(entradaTeclado.nextLine());
        return espacio;
    }

}

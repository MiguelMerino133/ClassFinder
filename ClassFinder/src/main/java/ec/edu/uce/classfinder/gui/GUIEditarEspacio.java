package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.modelo.Espacio;
import java.util.Scanner;

public class GUIEditarEspacio {

    private Scanner entradaTeclado = new Scanner(System.in);

    public Espacio editar() {
        System.out.println("\n=== EDITAR ESPACIO ===");
        Espacio espacio = new Espacio();
        System.out.print("Ingrese ID del espacio a editar: ");
        espacio.setIdEspacio(entradaTeclado.nextLine());
        System.out.print("Ingrese nuevo ID del lugar: ");
        espacio.setIdLugar(entradaTeclado.nextLine());
        System.out.print("Ingrese nuevo nombre del espacio: ");
        espacio.setNombre(entradaTeclado.nextLine());
        System.out.print("Ingrese nueva capacidad (número de personas): ");
        espacio.setCapacidad(Integer.parseInt(entradaTeclado.nextLine()));
        System.out.print("Ingrese nuevo tamaño (pequeño/mediano/grande): ");
        espacio.setTamano(entradaTeclado.nextLine());
        return espacio;
    }

}

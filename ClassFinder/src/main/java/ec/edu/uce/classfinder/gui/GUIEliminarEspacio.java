package ec.edu.uce.classfinder.gui;

import java.util.Scanner;

public class GUIEliminarEspacio {

    private Scanner entradaTeclado = new Scanner(System.in);

    public String eliminar() {
        System.out.println("\n=== ELIMINAR ESPACIO ===");
        System.out.print("Ingrese ID del espacio a eliminar: ");
        return entradaTeclado.nextLine();
    }

}

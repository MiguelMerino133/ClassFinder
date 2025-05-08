package ec.edu.uce.classfinder.gui;

import java.util.Scanner;

public class GUIEliminarLugar {

    private Scanner entradaTeclado = new Scanner(System.in);

    public String eliminar() {
        System.out.println("\n=== ELIMINAR LUGAR ===");
        System.out.print("Ingrese ID del lugar a eliminar: ");
        return entradaTeclado.nextLine();
    }

}

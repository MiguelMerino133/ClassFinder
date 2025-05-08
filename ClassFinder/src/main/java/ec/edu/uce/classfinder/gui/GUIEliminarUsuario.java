package ec.edu.uce.classfinder.gui;

import java.util.Scanner;

public class GUIEliminarUsuario {

    private Scanner entradaTeclado = new Scanner(System.in);

    public String eliminar() {
        System.out.println("\n=== ELIMINAR USUARIO ===");
        System.out.print("Ingrese ID del usuario a eliminar: ");
        return entradaTeclado.nextLine();
    }
}

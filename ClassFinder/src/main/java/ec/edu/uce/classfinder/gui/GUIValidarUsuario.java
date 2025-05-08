package ec.edu.uce.classfinder.gui;

import java.util.Scanner;

public class GUIValidarUsuario {

    private Scanner entradaTeclado = new Scanner(System.in);

    public String[] validar() {
        System.out.println("\n=== VALIDAR CREDENCIALES ===");
        String[] credenciales = new String[2];
        System.out.print("Ingrese ID del usuario: ");
        credenciales[0] = entradaTeclado.nextLine();
        System.out.print("Ingrese contraseña: ");
        credenciales[1] = entradaTeclado.nextLine();
        return credenciales;
    }

}

package ec.edu.uce.classfinder.gui;

import java.util.Scanner;

public class GUIIngresarSistema {

    private Scanner entradaTeclado = new Scanner(System.in);

    public void ingresar(String idUsuario) {
        System.out.println("\n=== ACCEDER AL SISTEMA ===");
        System.out.print("Acceso concedido. Presione Enter para continuar...");
        entradaTeclado.nextLine();
        System.out.println("Bienvenido al sistema ClassFinder!");
    }


}

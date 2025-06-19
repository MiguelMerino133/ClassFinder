package ec.edu.uce.classfinder.gui;

import java.util.Scanner;

public class GUIIngresarSistema {
    private Scanner entradaTeclado = new Scanner(System.in);

    public void ingresar(String idUsuario) {
        System.out.println("\n=== INGRESO AL SISTEMA ===");
        System.out.println("Bienvenido al sistema, usuario con ID: " + idUsuario);
    }
}
package ec.edu.uce.classfinder.gui;

import java.util.Scanner;

/**
 * Interfaz gráfica para ingresar al sistema ClassFinder tras validar credenciales.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIIngresarSistema {

    private Scanner entradaTeclado = new Scanner(System.in);

    /**
     * Permite al usuario ingresar al sistema con su ID.
     * @param idUsuario ID del usuario validado
     */
    public void ingresar(String idUsuario) {
        System.out.println("\n=== INGRESO AL SISTEMA ===");
        System.out.println("Bienvenido al sistema, usuario con ID: " + idUsuario);
        System.out.println("Opciones disponibles según rol aparecerán en el menú principal.");
    }
}
package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.modelo.Lugar;
import java.util.Scanner;

public class GUIRegistrarLugar {

    private Scanner entradaTeclado = new Scanner(System.in);

    public Lugar registrar() {
        System.out.println("\n=== REGISTRAR LUGAR ===");
        Lugar lugar = new Lugar();
        System.out.print("Ingrese ID del lugar: ");
        lugar.setIdLugar(entradaTeclado.nextLine());
        System.out.print("Ingrese nombre del lugar: ");
        lugar.setNombre(entradaTeclado.nextLine());
        System.out.print("Ingrese descripción: ");
        lugar.setDescripcion(entradaTeclado.nextLine());
        System.out.print("Ingrese ID de la universidad: ");
        lugar.setIdUniversidad(entradaTeclado.nextLine());
        return lugar;
    }

}

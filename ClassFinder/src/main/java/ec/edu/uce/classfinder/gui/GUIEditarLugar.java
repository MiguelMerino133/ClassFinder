package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.modelo.Lugar;
import java.util.Scanner;

public class GUIEditarLugar {

    private Scanner entradaTeclado = new Scanner(System.in);

    public Lugar editar() {
        System.out.println("\n=== EDITAR LUGAR ===");
        Lugar lugar = new Lugar();
        System.out.print("Ingrese ID del lugar a editar: ");
        lugar.setIdLugar(entradaTeclado.nextLine());
        System.out.print("Ingrese nuevo nombre del lugar: ");
        lugar.setNombre(entradaTeclado.nextLine());
        System.out.print("Ingrese nueva descripción: ");
        lugar.setDescripcion(entradaTeclado.nextLine());
        System.out.print("Ingrese nuevo ID de la universidad: ");
        lugar.setIdUniversidad(entradaTeclado.nextLine());
        return lugar;
    }

}

package ec.edu.uce.classfinder.gui;

import java.util.Scanner;

public class GUIConsultarLugar {

    private Scanner entradaTeclado = new Scanner(System.in);

    public String consultar() {
        System.out.println("\n=== CONSULTAR LUGAR ===");
        System.out.print("Ingrese ID del lugar a consultar: ");
        return entradaTeclado.nextLine();
    }

    public void mostrarLugar(String idLugar) {
        System.out.println("Consulta de Lugar - ID: " + idLugar);
        System.out.println("Detalles simulados: Nombre: Edificio A, Descripción: Edificio principal");
    }

}

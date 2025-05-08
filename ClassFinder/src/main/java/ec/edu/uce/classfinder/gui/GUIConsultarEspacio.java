package ec.edu.uce.classfinder.gui;

import java.util.Scanner;

public class GUIConsultarEspacio {

    private Scanner entradaTeclado = new Scanner(System.in);

    public String consultar() {
        System.out.println("\n=== CONSULTAR ESPACIO ===");
        System.out.print("Ingrese ID del espacio a consultar: ");
        return entradaTeclado.nextLine();
    }

    public void mostrarEspacio(String idEspacio) {
        // Simulación: podrías reemplazar esto con datos reales si tienes una lista de espacios
        System.out.println("Consulta de Espacio - ID: " + idEspacio);
        System.out.println("Detalles simulados: Nombre: Aula 101, Capacidad: 30, Tamaño: mediano");
    }

}

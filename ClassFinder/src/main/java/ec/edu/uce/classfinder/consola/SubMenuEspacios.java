package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.gui.GUIRegistrarEspacio;
import ec.edu.uce.classfinder.gui.GUIConsultarEspacio;
import ec.edu.uce.classfinder.gui.GUIEditarEspacio;
import ec.edu.uce.classfinder.gui.GUIEliminarEspacio;
import ec.edu.uce.classfinder.modelo.Espacio;
import java.util.Scanner;

public class SubMenuEspacios {

    public void mostrar() {
        Scanner entradaTeclado = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== GESTIÓN ESPACIOS ===");
            System.out.println("1. Registrar Espacios");
            System.out.println("2. Consultar Espacio");
            System.out.println("3. Editar Espacio");
            System.out.println("4. Eliminar Espacio");
            System.out.println("5. Volver al Menu Principal");
            System.out.print("Seleccione una opción: ");
            opcion = entradaTeclado.nextInt();

            switch (opcion) {
                case 1:
                    GUIRegistrarEspacio guiRegistrar = new GUIRegistrarEspacio();
                    Espacio nuevoEspacio = guiRegistrar.registrar();
                    System.out.println("Espacio registrado: " + nuevoEspacio.getNombre());
                    break;
                case 2:
                    GUIConsultarEspacio guiConsultar = new GUIConsultarEspacio();
                    String idConsulta = guiConsultar.consultar();
                    guiConsultar.mostrarEspacio(idConsulta);
                    break;
                case 3:
                    GUIEditarEspacio guiEditar = new GUIEditarEspacio();
                    Espacio espacioEditado = guiEditar.editar();
                    System.out.println("Espacio editado: " + espacioEditado.getNombre());
                    break;
                case 4:
                    GUIEliminarEspacio guiEliminar = new GUIEliminarEspacio();
                    String idEspacio = guiEliminar.eliminar();
                    System.out.println("Espacio con ID " + idEspacio + " eliminado.");
                    break;
                case 5:
                    System.out.println("Regresando al menú principal...");
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 5);

    }

}

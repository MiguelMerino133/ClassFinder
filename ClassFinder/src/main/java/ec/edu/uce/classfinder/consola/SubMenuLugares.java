package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.gui.GUIRegistrarLugar;
import ec.edu.uce.classfinder.gui.GUIEditarLugar;
import ec.edu.uce.classfinder.gui.GUIEliminarLugar;
import ec.edu.uce.classfinder.gui.GUIConsultarLugar;
import ec.edu.uce.classfinder.modelo.Lugar;
import java.util.Scanner;

public class SubMenuLugares {

    public void mostrar(){
        Scanner entradaTeclado=new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== GESTIÓN DE LUGARES ===");
            System.out.println("1. Registrar Lugar");
            System.out.println("2. Consultar Lugar");
            System.out.println("3. Editar Lugar");
            System.out.println("4. Eliminar Lugar");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = entradaTeclado.nextInt();

            switch (opcion) {
                case 1:
                    GUIRegistrarLugar guiRegistrar = new GUIRegistrarLugar();
                    Lugar nuevoLugar = guiRegistrar.registrar();
                    System.out.println("Lugar registrado: " + nuevoLugar.getNombre());
                    break;
                case 2:
                    GUIConsultarLugar guiConsultar = new GUIConsultarLugar();
                    String idConsulta = guiConsultar.consultar();
                    guiConsultar.mostrarLugar(idConsulta);
                    break;
                case 3:
                    GUIEditarLugar guiEditar = new GUIEditarLugar();
                    Lugar lugarEditado = guiEditar.editar();
                    System.out.println("Lugar editado: " + lugarEditado.getNombre());
                    break;
                case 4:
                    GUIEliminarLugar guiEliminar = new GUIEliminarLugar();
                    String idLugar = guiEliminar.eliminar();
                    System.out.println("Lugar con ID " + idLugar + " eliminado.");
                    break;
                case 5:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 5);

    }

}

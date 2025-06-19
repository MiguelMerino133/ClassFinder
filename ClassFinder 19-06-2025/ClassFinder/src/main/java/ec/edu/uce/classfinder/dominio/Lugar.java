package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Clase que representa un lugar genérico en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Lugar {
    private static int contador = 1;
    private static final String PREFIJO = "LUG-";
    private final String idLugar;
    private String nombre;
    private String descripcion;
    private Espacio[] espacios;
    private int numEspacios;

    public Lugar() {
        idLugar = PREFIJO + String.format("%03d", contador++);
        nombre = "";
        descripcion = "";
        espacios = new Espacio[5];
        numEspacios = 0;
    }

    public Lugar(String nombre, String descripcion) {
        this.idLugar = PREFIJO + String.format("%03d", contador++);
        setNombre(nombre);
        setDescripcion(descripcion);
        espacios = new Espacio[5];
        numEspacios = 0;
    }

    public String getIdLugar() {
        return idLugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = (nombre != null && Validadores.esTextoValido(nombre)) ? nombre : "";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = (descripcion == null || descripcion.isBlank() || Validadores.esTextoValido(descripcion)) ? descripcion : "";
    }

    public Espacio[] getEspacios() {
        return espacios;
    }

    public int getNumEspacios() {
        return numEspacios;
    }

    public boolean crearEspacio(Espacio espacio) {
        if (espacio == null) return false;
        for (Espacio e : espacios) {
            if (e != null && e.equals(espacio)) {
                return false;
            }
        }
        if (numEspacios == espacios.length) {
            Espacio[] nuevo = new Espacio[espacios.length + 5];
            System.arraycopy(espacios, 0, nuevo, 0, numEspacios);
            espacios = nuevo;
        }
        espacios[numEspacios++] = espacio;
        return true;
    }

    public Object buscarPorId(Integer id) {
        if (id == null) return null;
        String idEspacio = "ESP-" + String.format("%03d", id);
        for (Espacio espacio : espacios) {
            if (espacio != null && espacio.getIdEspacio().equals(idEspacio)) {
                return espacio;
            }
        }
        return null;
    }

    public String listar() {
        StringBuilder sb = new StringBuilder();
        int index = 1;
        for (Espacio espacio : espacios) {
            if (espacio != null) {
                sb.append(index++).append(": ").append(espacio.toString()).append("\n");
            }
        }
        return sb.length() > 0 ? sb.toString() : "No hay espacios registrados.";
    }

    public String nuevo(Object obj) {
        if (!(obj instanceof Espacio) || !crearEspacio((Espacio) obj)) {
            return "Error: No se pudo crear el espacio, posible duplicado.";
        }
        return "Espacio creado exitosamente con ID: " + ((Espacio) obj).getIdEspacio();
    }

    public String editar(Object obj) {
        if (!(obj instanceof Espacio)) return "Error: Objeto inválido.";
        Espacio espacio = (Espacio) obj;
        for (int i = 0; i < numEspacios; i++) {
            if (espacios[i].getIdEspacio().equals(espacio.getIdEspacio())) {
                espacios[i] = espacio;
                return "Espacio editado exitosamente.";
            }
        }
        return "Error: Espacio no encontrado.";
    }

    public String borrar(Object obj) {
        if (!(obj instanceof Espacio)) return "Error: Objeto inválido.";
        Espacio espacio = (Espacio) obj;
        for (int i = 0; i < numEspacios; i++) {
            if (espacios[i].getIdEspacio().equals(espacio.getIdEspacio())) {
                System.arraycopy(espacios, i + 1, espacios, i, numEspacios - i - 1);
                espacios[--numEspacios] = null;
                return "Espacio eliminado exitosamente.";
            }
        }
        return "Error: Espacio no encontrado.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lugar)) return false;
        Lugar lugar = (Lugar) o;
        return nombre.equalsIgnoreCase(lugar.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lugar:\r\nID: ").append(idLugar).append("\r\nNombre: ").append(nombre);
        if (!descripcion.isEmpty()) {
            sb.append("\r\nDescripción: ").append(descripcion);
        }
        return sb.toString();
    }

    public static int getContador() {
        return contador;
    }
}
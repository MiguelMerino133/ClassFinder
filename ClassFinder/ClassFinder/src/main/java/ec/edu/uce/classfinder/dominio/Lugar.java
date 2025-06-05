package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Representa un lugar en el sistema ClassFinder.
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

    public Lugar(String idLugar, String nombre, String descripcion) {
        this.idLugar = idLugar != null && Validadores.esIdValido(idLugar) ? idLugar : PREFIJO + String.format("%03d", contador++);
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
        this.descripcion = descripcion != null ? descripcion : "";
    }

    public Espacio[] getEspacios() {
        return espacios;
    }

    public int getNumEspacios() {
        return numEspacios;
    }

    public boolean crearEspacio(Espacio espacio) {
        if (espacio == null || !validarDuplicado(espacio)) return false;
        if (numEspacios == espacios.length) {
            Espacio[] temp = new Espacio[espacios.length + 5];
            System.arraycopy(espacios, 0, temp, 0, numEspacios);
            espacios = temp;
        }
        espacios[numEspacios++] = espacio;
        return true;
    }

    public boolean crearEspacio(Aula aula) {
        return crearEspacio((Espacio) aula);
    }

    public boolean crearEspacio(Auditorio auditorio) {
        return crearEspacio((Espacio) auditorio);
    }

    public String consultarEspacios() {
        StringBuilder info = new StringBuilder();
        for (int i = 0; i < numEspacios; i++) {
            info.append((i + 1)).append(": ").append(espacios[i].toString()).append("\n");
        }
        return info.length() > 0 ? info.toString() : "No hay espacios registrados.";
    }

    public boolean editarEspacio(int pos, Espacio espacio) {
        if (pos >= 0 && pos < numEspacios && espacio != null && validarDuplicado(espacio, pos)) {
            espacios[pos] = espacio;
            return true;
        }
        return false;
    }

    public boolean eliminarEspacio(int pos) {
        if (pos < 0 || pos >= numEspacios) return false;
        System.arraycopy(espacios, pos + 1, espacios, pos, numEspacios - pos - 1);
        espacios[numEspacios--] = null;
        return true;
    }

    public boolean validarDuplicado(Espacio espacio) {
        for (Espacio e : espacios) {
            if (e != null && e.equals(espacio)) {
                return false;
            }
        }
        return true;
    }

    private boolean validarDuplicado(Espacio espacio, int skipPos) {
        for (int i = 0; i < numEspacios; i++) {
            if (i != skipPos && espacios[i] != null && espacios[i].equals(espacio)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lugar)) return false;
        Lugar lugar = (Lugar) o;
        return idLugar.equals(lugar.idLugar);
    }

    @Override
    public int hashCode() {
        return idLugar.hashCode();
    }

    @Override
    public String toString() {
        return "Lugar:" + "\r\nID: " + idLugar + "\r\nNombre: " + nombre +
                "\r\nDescripción: " + descripcion + "\r\nEspacios registrados: " + numEspacios;
    }
}
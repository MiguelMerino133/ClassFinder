package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Representa un lugar dentro de una universidad, que contiene espacios.
 * Es una clase base para tipos específicos como {@link Edificio}.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Lugar {

    private String idLugar;
    private String nombre;
    private String descripcion;
    private Espacio[] espacios;
    private int numEspacios;

    /**
     * Constructor por defecto.
     * Inicializa un lugar con valores predeterminados y un arreglo de 5 espacios.
     */
    public Lugar() {
        idLugar = "LUG-001";
        nombre = "Lugar General";
        descripcion = "Descripción general";
        espacios = new Espacio[5];
        numEspacios = 0;
    }

    /**
     * Constructor con parámetros.
     * @param idLugar identificador del lugar
     * @param nombre nombre del lugar
     * @param descripcion descripción del lugar
     */
    public Lugar(String idLugar, String nombre, String descripcion) {
        this.idLugar = idLugar;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.espacios = new Espacio[5];
        this.numEspacios = 0;
    }

    /**
     * Obtiene el identificador del lugar.
     * @return el identificador del lugar
     */
    public String getIdLugar() {
        return idLugar;
    }

    /**
     * Establece el identificador del lugar.
     * Valida que el valor no sea nulo ni inválido, usando un valor por defecto si falla.
     * @param idLugar nuevo identificador
     */
    public void setIdLugar(String idLugar) {
        if (idLugar == null || !Validadores.esIdValido(idLugar)) {
            this.idLugar = "LUG-001";
        } else {
            this.idLugar = idLugar;
        }
    }

    /**
     * Obtiene el nombre del lugar.
     * @return el nombre del lugar
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del lugar.
     * Valida que el valor no sea nulo ni inválido, usando un valor por defecto si falla.
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        if (nombre == null || !Validadores.esTextoValido(nombre)) {
            this.nombre = "Lugar General";
        } else {
            this.nombre = nombre;
        }
    }

    /**
     * Obtiene la descripción del lugar.
     * @return la descripción del lugar
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del lugar.
     * Valida que el valor no sea nulo ni inválido, usando un valor por defecto si falla.
     * @param descripcion nueva descripción
     */
    public void setDescripcion(String descripcion) {
        if (descripcion == null || !Validadores.esTextoValido(descripcion)) {
            this.descripcion = "Descripción general";
        } else {
            this.descripcion = descripcion;
        }
    }

    /**
     * Obtiene el arreglo de espacios.
     * @return el arreglo de espacios
     */
    public Espacio[] getEspacios() {
        return espacios;
    }

    /**
     * Establece el arreglo de espacios.
     * Valida que el arreglo no sea nulo ni exceda un tamaño máximo de 100, usando un valor por defecto si falla.
     * @param espacios nuevo arreglo de espacios
     */
    public void setEspacios(Espacio[] espacios) {
        if (espacios == null || espacios.length > 100) {
            this.espacios = new Espacio[5];
        } else {
            this.espacios = new Espacio[espacios.length];
            System.arraycopy(espacios, 0, this.espacios, 0, espacios.length);
            this.numEspacios = Math.min(espacios.length, this.espacios.length);
        }
    }

    /**
     * Obtiene el número de espacios registrados.
     * @return el número de espacios
     */
    public int getNumEspacios() {
        return numEspacios;
    }

    /**
     * Establece el número de espacios.
     * Valida que el valor no sea negativo ni exceda el tamaño del arreglo, usando un valor por defecto si falla.
     * @param numEspacios nuevo número de espacios
     */
    public void setNumEspacios(int numEspacios) {
        if (numEspacios < 0 || numEspacios > this.espacios.length) {
            this.numEspacios = 0;
        } else {
            this.numEspacios = numEspacios;
        }
    }

    /**
     * Crea un nuevo espacio y lo agrega al lugar.
     * @param espacio Espacio a agregar
     * @return true si el espacio fue agregado exitosamente, false de lo contrario
     */
    public boolean crearEspacio(Espacio espacio) {
        if (espacio == null) return false;
        if (numEspacios == espacios.length) {
            Espacio[] temp = new Espacio[espacios.length + 5];
            System.arraycopy(espacios, 0, temp, 0, numEspacios);
            espacios = temp;
        }
        espacios[numEspacios++] = espacio;
        return true;
    }

    /**
     * Consulta todos los espacios del lugar.
     * @return Cadena con la información de todos los espacios
     */
    public String consultarEspacios() {
        StringBuilder info = new StringBuilder();
        for (int i = 0; i < numEspacios; i++) {
            info.append(espacios[i].toString()).append("\n");
        }
        return info.length() > 0 ? info.toString() : "No hay espacios registrados.";
    }



    /**
     * Actualiza los datos de un espacio existente.
     * @param pos Posición del espacio a actualizar
     * @param espacio Nuevo espacio con los datos actualizados
     * @return true si la actualización fue exitosa, false de lo contrario
     */
    public boolean editarEspacio(int pos, Espacio espacio) {
        if (pos >= 0 && pos < numEspacios && espacio != null) {
            espacios[pos] = espacio;
            return true;
        }
        return false;
    }

    /**
     * Elimina un espacio del lugar.
     * @param pos Posición del espacio a eliminar
     * @return true si la eliminación fue exitosa, false de lo contrario
     */
    public boolean eliminarEspacio(int pos) {
        if (pos >= 0 && pos < numEspacios) {
            for (int i = pos; i < numEspacios - 1; i++) {
                espacios[i] = espacios[i + 1];
            }
            espacios[--numEspacios] = null;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Lugar" + "\r\nID: " + idLugar + "\r\nNombre: " + nombre + "\r\nDescripción: " + descripcion;
    }
}
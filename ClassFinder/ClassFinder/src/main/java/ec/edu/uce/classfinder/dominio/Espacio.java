package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Representa un espacio dentro de un lugar en la universidad.
 * Es una clase base para tipos específicos como {@link Auditorio} y {@link Aula}.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Espacio {

    private String idEspacio;
    private String nombre;
    private int capacidad;
    private String tamano;

    /**
     * Constructor por defecto.
     * Inicializa un espacio con valores predeterminados.
     */
    public Espacio() {
        idEspacio = "ESP-001";
        nombre = "Espacio General";
        capacidad = 10;
        tamano = "mediano";
    }

    /**
     * Constructor con parámetros.
     * @param idEspacio identificador del espacio
     * @param nombre nombre del espacio
     * @param capacidad capacidad del espacio
     * @param tamano tamaño del espacio
     */
    public Espacio(String idEspacio, String nombre, int capacidad, String tamano) {
        this.idEspacio = idEspacio;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tamano = tamano;
    }

    /**
     * Obtiene el identificador del espacio.
     * @return el identificador del espacio
     */
    public String getIdEspacio() {
        return idEspacio;
    }

    /**
     * Establece el identificador del espacio.
     * Valida que el valor no sea nulo ni inválido, usando un valor por defecto si falla.
     * @param idEspacio nuevo identificador
     */
    public void setIdEspacio(String idEspacio) {
        if (idEspacio == null || !Validadores.esIdValido(idEspacio)) {
            this.idEspacio = "ESP-001";
        } else {
            this.idEspacio = idEspacio;
        }
    }

    /**
     * Obtiene el nombre del espacio.
     * @return el nombre del espacio
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del espacio.
     * Valida que el valor no sea nulo ni inválido, usando un valor por defecto si falla.
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        if (nombre == null || !Validadores.esTextoValido(nombre)) {
            this.nombre = "Espacio General";
        } else {
            this.nombre = nombre;
        }
    }

    /**
     * Obtiene la capacidad del espacio.
     * @return la capacidad del espacio
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Establece la capacidad del espacio.
     * Valida que el valor sea positivo y válido, usando un valor por defecto si falla.
     * @param capacidad nueva capacidad
     */
    public void setCapacidad(int capacidad) {
        if (capacidad <= 0 || !Validadores.esCapacidadValido(String.valueOf(capacidad))) {
            this.capacidad = 10;
        } else {
            this.capacidad = capacidad;
        }
    }

    /**
     * Obtiene el tamaño del espacio.
     * @return el tamaño del espacio
     */
    public String getTamano() {
        return tamano;
    }

    /**
     * Establece el tamaño del espacio.
     * Valida que el valor no sea nulo ni inválido, usando un valor por defecto si falla.
     * @param tamano nuevo tamaño
     */
    public void setTamano(String tamano) {
        if (tamano == null || !Validadores.esTamanoValido(tamano)) {
            this.tamano = "mediano";
        } else {
            this.tamano = tamano;
        }
    }


    @Override
    public String toString() {
        return "Espacio"+"\r\nID: "+idEspacio+"\r\nNombre: "+nombre+"\r\nCapacidad: " +capacidad
                +"\r\nTamaño: "+tamano;
    }
}
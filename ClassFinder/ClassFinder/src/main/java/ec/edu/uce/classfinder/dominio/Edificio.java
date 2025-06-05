package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Representa un edificio, que es un tipo de lugar con número de pisos y ubicación.
 * Extiende la clase {@link Lugar} para heredar sus atributos básicos.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Edificio extends Lugar {

    private int numeroPisos;
    private String ubicacion;
    private static String[] ubicacionesPredeterminadas = {"Campus Central", "Campus Norte", "Campus Sur"};
    private static int indiceUbicacion = 0; // Índice para rotar entre ubicaciones

    /**
     * Constructor por defecto.
     * Inicializa un edificio con un piso y ubicación predeterminada "Campus Central".
     */
    public Edificio() {
        super();
        numeroPisos = 1;
        ubicacion = "Campus Central";
    }

    /**
     * Constructor con parámetros.
     * @param idLugar identificador del lugar
     * @param nombre nombre del lugar
     * @param descripcion descripción del lugar
     * @param numeroPisos número de pisos del edificio
     * @param ubicacion ubicación del edificio
     */
    public Edificio(String idLugar, String nombre, String descripcion, int numeroPisos, String ubicacion) {
        super(idLugar, nombre, descripcion);
        this.numeroPisos = numeroPisos;
        this.ubicacion = ubicacion;
    }

    /**
     * Obtiene el número de pisos.
     * @return el número de pisos
     */
    public int getNumeroPisos() {
        return numeroPisos;
    }

    /**
     * Establece el número de pisos.
     * Valida que el valor sea positivo y válido, usando un valor por defecto si falla.
     * @param numeroPisos nuevo número de pisos
     */
    public void setNumeroPisos(int numeroPisos) {
        if (numeroPisos <= 0 || !Validadores.esNumeroValido(String.valueOf(numeroPisos))) {
            this.numeroPisos = 1;
        } else {
            this.numeroPisos = numeroPisos;
        }
    }

    /**
     * Obtiene la ubicación.
     * @return la ubicación del edificio
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Establece la ubicación.
     * Valida que el valor no sea nulo ni inválido, usando un valor por defecto si falla.
     * @param ubicacion nueva ubicación
     */
    public void setUbicacion(String ubicacion) {
        if (ubicacion == null || !Validadores.esTextoValido(ubicacion)) {
            this.ubicacion = "Campus Central";
        } else {
            this.ubicacion = ubicacion;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\r\nNúmero de pisos: " + numeroPisos + "\r\nUbicación: " + ubicacion;
    }
}
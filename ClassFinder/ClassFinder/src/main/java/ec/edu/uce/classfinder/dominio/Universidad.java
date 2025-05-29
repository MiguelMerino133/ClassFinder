package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Representa una universidad.
 * Gestiona lugares, reservas, usuarios y sus operaciones CRUD.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Universidad {

    private String idUniversidad;
    private String nombreUniversidad;
    private String nombreRector;
    private String telefono;
    private Lugar[] lugares;
    private int numLugares;
    private Reserva[] reservas;
    private int numReservas;
    private Usuario[] usuarios;
    private int numUsuarios;

    /**
     * Constructor por defecto.
     * Inicializa una universidad con valores predeterminados y arreglos de 5 elementos para lugares, reservas y usuarios.
     */
    public Universidad() {
        idUniversidad = "UNI-001";
        nombreUniversidad = "Universidad Central";
        nombreRector = "Juan Pérez";
        telefono = "0991234567";
        lugares = new Lugar[5];
        numLugares = 0;
        reservas = new Reserva[5];
        numReservas = 0;
        usuarios = new Usuario[5];
        numUsuarios = 0;
    }

    /**
     * Constructor con parámetros.
     * @param idUniversidad identificador de la universidad
     * @param nombreUniversidad nombre de la universidad
     * @param nombreRector nombre del rector
     * @param telefono teléfono de contacto
     */
    public Universidad(String idUniversidad, String nombreUniversidad, String nombreRector, String telefono) {
        setIdUniversidad(idUniversidad);
        setNombreUniversidad(nombreUniversidad);
        setNombreRector(nombreRector);
        setTelefono(telefono);
        lugares = new Lugar[5];
        numLugares = 0;
        reservas = new Reserva[5];
        numReservas = 0;
        usuarios = new Usuario[5];
        numUsuarios = 0;
    }

    /**
     * Obtiene el identificador de la universidad.
     * @return el identificador de la universidad
     */
    public String getIdUniversidad() {
        return idUniversidad;
    }

    /**
     * Establece el identificador de la universidad.
     * Valida que el valor no sea nulo ni inválido, usando un valor por defecto si falla.
     * @param idUniversidad nuevo identificador
     */
    public void setIdUniversidad(String idUniversidad) {
        if (idUniversidad == null || !Validadores.esIdValido(idUniversidad)) {
            this.idUniversidad = "UNI-001";
        } else {
            this.idUniversidad = idUniversidad;
        }
    }

    /**
     * Obtiene el nombre de la universidad.
     * @return el nombre de la universidad
     */
    public String getNombreUniversidad() {
        return nombreUniversidad;
    }

    /**
     * Establece el nombre de la universidad.
     * Valida que el valor no sea nulo ni inválido, usando un valor por defecto si falla.
     * @param nombreUniversidad nuevo nombre
     */
    public void setNombreUniversidad(String nombreUniversidad) {
        if (nombreUniversidad == null || !Validadores.esTextoValido(nombreUniversidad)) {
            this.nombreUniversidad = "Universidad Central";
        } else {
            this.nombreUniversidad = nombreUniversidad;
        }
    }

    /**
     * Obtiene el nombre del rector.
     * @return el nombre del rector
     */
    public String getNombreRector() {
        return nombreRector;
    }

    /**
     * Establece el nombre del rector.
     * Valida que el valor no sea nulo ni inválido, usando un valor por defecto si falla.
     * @param nombreRector nuevo nombre del rector
     */
    public void setNombreRector(String nombreRector) {
        if (nombreRector == null || !Validadores.esTextoValido(nombreRector)) {
            this.nombreRector = "Juan Pérez";
        } else {
            this.nombreRector = nombreRector;
        }
    }

    /**
     * Obtiene el teléfono de la universidad.
     * @return el teléfono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono de la universidad.
     * Valida que el valor sea un número de 10 dígitos, usando un valor por defecto si falla.
     * @param telefono nuevo teléfono
     */
    public void setTelefono(String telefono) {
        if (telefono == null || !telefono.matches("\\d{10}")) {
            this.telefono = "0991234567";
        } else {
            this.telefono = telefono;
        }
    }

    /**
     * Obtiene el número de lugares registrados.
     * @return el número de lugares
     */
    public int getNumLugares() {
        return numLugares;
    }

    /**
     * Obtiene el arreglo de lugares.
     * @return el arreglo de lugares
     */
    public Lugar[] getLugares() {
        return lugares;
    }

    /**
     * Obtiene el número de reservas registradas.
     * @return el número de reservas
     */
    public int getNumReservas() {
        return numReservas;
    }

    /**
     * Obtiene el arreglo de reservas.
     * @return el arreglo de reservas
     */
    public Reserva[] getReservas() {
        return reservas;
    }

    /**
     * Obtiene el número de usuarios registrados.
     * @return el número de usuarios
     */
    public int getNumUsuarios() {
        return numUsuarios;
    }

    /**
     * Obtiene el arreglo de usuarios.
     * @return el arreglo de usuarios
     */
    public Usuario[] getUsuarios() {
        return usuarios;
    }

    /**
     * Crea un nuevo lugar y lo agrega a la universidad.
     * @param lugar Lugar a agregar
     * @return true si el lugar fue agregado exitosamente, false de lo contrario
     */
    public boolean crearLugar(Lugar lugar) {
        if (lugar == null) return false;
        if (numLugares == lugares.length) {
            Lugar[] temp = new Lugar[lugares.length + 5];
            System.arraycopy(lugares, 0, temp, 0, numLugares);
            lugares = temp;
        }
        lugares[numLugares++] = lugar;
        return true;
    }

    /**
     * Consulta todos los lugares de la universidad.
     * @return Cadena con la información de todos los lugares, incluyendo índices
     */
    public String consultarLugares() {
        StringBuilder info = new StringBuilder();
        int index = 1; // Mantener el índice para mostrar al usuario (basado en 1)
        for (Lugar lugar : lugares) {
            if (index <= numLugares) {
                info.append(index).append(": ").append(lugar.toString()).append("\n");
                index++;
            }
        }
        return info.length() > 0 ? info.toString() : "No hay lugares registrados.";
    }

    /**
     * Actualiza los datos de un lugar existente.
     * @param pos Posición del lugar a actualizar
     * @param lugar Nuevo lugar con los datos actualizados
     * @return true si la actualización fue exitosa, false de lo contrario
     */
    public boolean editarLugar(int pos, Lugar lugar) {
        if (pos >= 0 && pos < numLugares && lugar != null) {
            lugares[pos] = lugar;
            return true;
        }
        return false;
    }

    /**
     * Elimina un lugar de la universidad.
     * @param pos Posición del lugar a eliminar
     * @return true si la eliminación fue exitosa, false de lo contrario
     */
    public boolean eliminarLugar(int pos) {
        if (pos >= 0 && pos < numLugares) {
            for (int i = pos; i < numLugares - 1; i++) {
                lugares[i] = lugares[i + 1];
            }
            lugares[numLugares - 1] = null;
            numLugares--;
            return true;
        }
        return false;
    }

    public boolean agregarEdificio(Edificio edificio) {
        if (numLugares < lugares.length) {
            lugares[numLugares] = edificio;
            numLugares++;
            return true;
        }
        return false;
    }

    /**
     * Crea un nuevo usuario y lo agrega a la universidad.
     * @param usuario Usuario a agregar
     * @return true si el usuario fue agregado exitosamente, false de lo contrario
     */
    public boolean crearUsuario(Usuario usuario) {
        if (usuario == null) return false;
        if (numUsuarios == usuarios.length) {
            Usuario[] temp = new Usuario[usuarios.length + 5];
            System.arraycopy(usuarios, 0, temp, 0, numUsuarios);
            usuarios = temp;
        }
        usuarios[numUsuarios++] = usuario;
        return true;
    }

    /**
     * Consulta todos los usuarios de la universidad.
     * @return Cadena con la información de todos los usuarios, incluyendo índices
     */
    public String consultarUsuarios() {
        StringBuilder info = new StringBuilder();
        int index = 1;
        for (Usuario usuario : usuarios) {
            if (index <= numUsuarios) {
                info.append(index).append(": ").append(usuario.toString()).append("\n");
                index++;
            }
        }
        return info.length() > 0 ? info.toString() : "No hay usuarios registrados.";
    }

    /**
     * Actualiza los datos de un usuario existente.
     * @param pos Posición del usuario a actualizar
     * @param usuario Nuevo usuario con los datos actualizados
     * @return true si la actualización fue exitosa, false de lo contrario
     */
    public boolean editarUsuario(int pos, Usuario usuario) {
        if (pos >= 0 && pos < numUsuarios && usuario != null) {
            usuarios[pos] = usuario;
            return true;
        }
        return false;
    }

    /**
     * Elimina un usuario de la universidad.
     * @param pos Posición del usuario a eliminar
     * @return true si la eliminación fue exitosa, false de lo contrario
     */
    public boolean eliminarUsuario(int pos) {
        if (pos >= 0 && pos < numUsuarios) {
            for (int i = pos; i < numUsuarios - 1; i++) {
                usuarios[i] = usuarios[i + 1];
            }
            usuarios[--numUsuarios] = null;
            return true;
        }
        return false;
    }

    /**
     * Crea una nueva reserva y la agrega a la universidad.
     * @param reserva Reserva a agregar
     * @return true si la reserva fue agregada exitosamente, false de lo contrario
     */
    public boolean crearReserva(Reserva reserva) {
        if (reserva == null) return false;
        if (numReservas == reservas.length) {
            Reserva[] temp = new Reserva[reservas.length + 5];
            System.arraycopy(reservas, 0, temp, 0, numReservas);
            reservas = temp;
        }
        reservas[numReservas++] = reserva;
        return true;
    }

    /**
     * Consulta todas las reservas de la universidad.
     * @return Cadena con la información de todas las reservas, incluyendo índices
     */
    public String consultarReservas() {
        StringBuilder info = new StringBuilder();
        int index = 1;
        for (Reserva reserva : reservas) {
            if (index <= numReservas) {
                info.append(index).append(": ").append(reserva.toString()).append("\n");
                index++;
            }
        }
        return info.length() > 0 ? info.toString() : "No hay reservas registradas.";
    }

    /**
     * Busca un usuario por su ID.
     * @param idUsuario El ID del usuario a buscar (formato USR-001).
     * @return El objeto Usuario si se encuentra, null de lo contrario.
     */
    public Usuario buscarUsuario(String idUsuario) {
        if (idUsuario == null || !Validadores.esIdValido(idUsuario)) {
            return null;
        }
        for (Usuario usuario : usuarios) {
            if (usuario != null && usuario.getIdUsuario().equals(idUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Busca un espacio por su ID en todos los lugares.
     * @param idEspacio El ID del espacio a buscar (formato ESP-001).
     * @return El espacio encontrado, o null si no existe.
     */
    public Espacio buscarEspacio(String idEspacio) {
        if (!Validadores.esIdValido(idEspacio)) {
            return null;
        }
        for (int i = 0; i < numLugares; i++) {
            if (lugares[i] != null) {
                Espacio[] espacios = lugares[i].getEspacios();
                int numEspacios = lugares[i].getNumEspacios();
                for (int j = 0; j < numEspacios; j++) {
                    if (espacios[j] != null && espacios[j].getIdEspacio().equals(idEspacio)) {
                        return espacios[j];
                    }
                }
            }
        }
        return null;
    }


    public Reserva buscarReservaId(String idReserva) {
        if (idReserva == null || !Validadores.esIdValido(idReserva)) {
            return null;
        }
        for (Reserva reserva : reservas) {
            if (reserva != null && reserva.getIdReserva().equals(idReserva)) {
                return reserva;
            }
        }
        return null;
    }




    /**
     * Actualiza los datos de una reserva existente.
     * @param pos Posición de la reserva a actualizar
     * @param reserva Nueva reserva con los datos actualizados
     * @return true si la actualización fue exitosa, false de lo contrario
     */
    public boolean editarReserva(int pos, Reserva reserva) {
        if (pos >= 0 && pos < numReservas && reserva != null) {
            reservas[pos] = reserva;
            return true;
        }
        return false;
    }

    /**
     * Elimina una reserva de la universidad.
     * @param pos Posición de la reserva a eliminar
     * @return true si la eliminación fue exitosa, false de lo contrario
     */
    public boolean eliminarReserva(int pos) {
        if (pos >= 0 && pos < numReservas) {
            for (int i = pos; i < numReservas - 1; i++) {
                reservas[i] = reservas[i + 1];
            }
            reservas[numReservas - 1] = null;
            numReservas--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Universidad" + "\r\nID: " + idUniversidad + "\r\nNombre: " + nombreUniversidad +
                "\r\nRector: " + nombreRector + "\r\nTeléfono: " + telefono;
    }
}
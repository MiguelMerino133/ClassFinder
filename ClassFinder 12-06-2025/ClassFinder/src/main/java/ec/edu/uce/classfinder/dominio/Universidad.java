package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Representa una universidad en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Universidad {
    private static final Universidad INSTANCE = new Universidad();
    private String nombreUniversidad;
    private String nombreRector;
    private String telefono;
    private Lugar[] lugares;
    private int numLugares;
    private Reserva[] reservas;
    private int numReservas;
    private Usuario[] usuarios;
    private int numUsuarios;

    private Universidad() {
        nombreUniversidad = "Universidad Central";
        nombreRector = "Juan Pérez";
        telefono = "0991234567";
        lugares = new Lugar[5];
        numLugares = 0;
        reservas = new Reserva[5];
        numReservas = 0;
        usuarios = new Usuario[5];
        numUsuarios = 0;
        inicializar();
    }

    public static Universidad getInstance() {
        return INSTANCE;
    }

    public String getNombreUniversidad() {
        return nombreUniversidad;
    }

    public void setNombreUniversidad(String nombreUniversidad) {
        this.nombreUniversidad = (nombreUniversidad != null && Validadores.esTextoValido(nombreUniversidad)) ? nombreUniversidad : "Universidad Central";
    }

    public String getNombreRector() {
        return nombreRector;
    }

    public void setNombreRector(String nombreRector) {
        this.nombreRector = (nombreRector != null && Validadores.esTextoValido(nombreRector)) ? nombreRector : "Juan Pérez";
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = (telefono != null && telefono.matches("\\d{10}")) ? telefono : "0991234567";
    }

    public int getNumLugares() {
        return numLugares;
    }

    public Lugar[] getLugares() {
        return lugares;
    }

    public int getNumReservas() {
        return numReservas;
    }

    public Reserva[] getReservas() {
        return reservas;
    }

    public int getNumUsuarios() {
        return numUsuarios;
    }

    public Usuario[] getUsuarios() {
        return usuarios;
    }


    // Métodos CRUD para Lugares
    public boolean crearLugar(Lugar lugar) {
        if (lugar == null || !validarDuplicado(lugar)) return false;
        if (numLugares == lugares.length) {
            Lugar[] temp = new Lugar[lugares.length + 5];
            System.arraycopy(lugares, 0, temp, 0, numLugares);
            lugares = temp;
        }
        lugares[numLugares++] = lugar;
        return true;
    }

    public boolean crearLugar(Edificio edificio) {
        return crearLugar((Lugar) edificio);
    }

    public String consultarLugares() {
        StringBuilder info = new StringBuilder();
        for (int i = 0; i < numLugares; i++) {
            info.append((i + 1)).append(": ").append(lugares[i].toString()).append("\n");
        }
        return info.length() > 0 ? info.toString() : "No hay lugares registrados.";
    }

    public boolean editarLugar(int pos, Lugar lugar) {
        if (pos >= 0 && pos < numLugares && lugar != null && validarDuplicado(lugar, pos)) {
            lugares[pos] = lugar;
            return true;
        }
        return false;
    }

    public boolean eliminarLugar(int pos) {
        if (pos < 0 || pos >= numLugares) return false;
        System.arraycopy(lugares, pos + 1, lugares, pos, numLugares - pos - 1);
        lugares[--numLugares] = null;
        return true;
    }

    // Métodos CRUD para Reservas
    public boolean crearReserva(Reserva reserva) {
        if (reserva == null || !validarDuplicado(reserva)) return false;
        if (numReservas == reservas.length) {
            Reserva[] nuevo = new Reserva[reservas.length + 5];
            System.arraycopy(reservas, 0, nuevo, 0, numReservas);
            reservas = nuevo;
        }
        reservas[numReservas++] = reserva;
        return true;
    }

    public boolean crearReserva(ReservaTemporal reservaTemporal) {
        return crearReserva((Reserva) reservaTemporal);
    }

    public boolean crearReserva(ReservaSemestre reservaSemestre) {
        return crearReserva((Reserva) reservaSemestre);
    }

    public String consultarReservas() {
        StringBuilder info = new StringBuilder();
        for (int i = 0; i < numReservas; i++) {
            info.append((i + 1)).append(": ").append(reservas[i].toString()).append("\n");
        }
        return info.length() > 0 ? info.toString() : "No hay reservas registradas.";
    }

    public boolean editarReserva(int pos, Reserva reserva) {
        if (pos >= 0 && pos < numReservas && reserva != null && validarDuplicado(reserva, pos)) {
            reservas[pos] = reserva;
            return true;
        }
        return false;
    }

    public boolean eliminarReserva(int pos) {
        if (pos < 0 || pos >= numReservas) return false;
        System.arraycopy(reservas, pos + 1, reservas, pos, numReservas - pos - 1);
        reservas[--numReservas] = null;
        return true;
    }

    // Métodos CRUD para Usuarios
    public boolean crearUsuario(Usuario usuario) {
        if (usuario == null || !validarDuplicado(usuario)) return false;
        if (numUsuarios == usuarios.length) {
            Usuario[] temp = new Usuario[usuarios.length + 5];
            System.arraycopy(usuarios, 0, temp, 0, numUsuarios);
            usuarios = temp;
        }
        usuarios[numUsuarios++] = usuario;
        return true;
    }

    public String consultarUsuarios() {
        StringBuilder info = new StringBuilder();
        for (int i = 0; i < numUsuarios; i++) {
            info.append((i + 1)).append(": ").append(usuarios[i].toString()).append("\n");
        }
        return info.length() > 0 ? info.toString() : "No hay usuarios registrados.";
    }

    public boolean editarUsuario(int pos, Usuario usuario) {
        if (pos >= 0 && pos < numUsuarios && usuario != null && validarDuplicado(usuario, pos)) {
            if (!usuarios[pos].getCedulaIdentidad().equals(usuario.getCedulaIdentidad())) {
                return false;
            }
            usuarios[pos] = usuario;
            return true;
        }
        return false;
    }

    public boolean eliminarUsuario(int pos) {
        if (pos < 0 || pos >= numUsuarios) return false;
        System.arraycopy(usuarios, pos + 1, usuarios, pos, numUsuarios - pos - 1);
        usuarios[--numUsuarios] = null;
        return true;
    }

    // Métodos de búsqueda
    public Usuario buscarUsuario(String idUsuario) {
        if (idUsuario == null) return null;
        for (Usuario usuario : usuarios) {
            if (usuario != null && usuario.getIdUsuario().equals(idUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscarUsuarioPorCedula(String cedula) {
        if (cedula == null) return null;
        for (Usuario usuario : usuarios) {
            if (usuario != null && usuario.getCedulaIdentidad().equals(cedula)) {
                return usuario;
            }
        }
        return null;
    }

    public Espacio buscarEspacio(String idEspacio) {
        if (idEspacio == null) return null;
        for (int i = 0; i < numLugares; i++) {
            if (lugares[i] != null) {
                for (int j = 0; j < lugares[i].getNumEspacios(); j++) {
                    Espacio espacio = lugares[i].getEspacios()[j];
                    if (espacio != null && espacio.getIdEspacio().equals(idEspacio)) {
                        return espacio;
                    }
                }
            }
        }
        return null;
    }

    public Reserva buscarReservaId(String idReserva) {
        if (idReserva == null) return null;
        for (Reserva reserva : reservas) {
            if (reserva != null && reserva.getIdReserva().equals(idReserva)) {
                return reserva;
            }
        }
        return null;
    }

    // Validación de duplicados
    public boolean validarDuplicado(Lugar lugar) {
        for (Lugar l : lugares) {
            if (l != null && l.equals(lugar)) {
                return false;
            }
        }
        return true;
    }

    private boolean validarDuplicado(Lugar lugar, int skipPos) {
        for (int i = 0; i < numLugares; i++) {
            if (i != skipPos && lugares[i] != null && lugares[i].equals(lugar)) {
                return false;
            }
        }
        return true;
    }

    public boolean validarDuplicado(Reserva reserva) {
        for (Reserva r : reservas) {
            if (r != null && r.equals(reserva)) {
                return false;
            }
        }
        return true;
    }

    private boolean validarDuplicado(Reserva reserva, int skipPos) {
        for (int i = 0; i < numReservas; i++) {
            if (i != skipPos && reservas[i] != null && reservas[i].equals(reserva)) {
                return false;
            }
        }
        return true;
    }

    public boolean validarDuplicado(Usuario usuario) {
        for (Usuario u : usuarios) {
            if (u != null && u.equals(usuario)) {
                return false;
            }
        }
        return true;
    }

    private boolean validarDuplicado(Usuario usuario, int skipPos) {
        for (int i = 0; i < numUsuarios; i++) {
            if (i != skipPos && usuarios[i] != null && usuarios[i].equals(usuario)) {
                return false;
            }
        }
        return true;
    }
    // Inicialización
    private void inicializar() {
        usuarios[0] = new Usuario("Miguel Merino", "pass123456", "1728803246", "miguel@gmail.com", Rol.INVITADO);
        usuarios[1] = new Usuario("Mishell Quinatoa", "pass456789", "1728803247", "mishell@gmail.com", Rol.DOCENTE);
        usuarios[2] = new Usuario("Juan Pérez", "pass789123", "1728803248", "juan@gmail.com", Rol.ESTUDIANTE);
        numUsuarios = 3;

        lugares[0] = new Lugar("Edificio A", "Edificio principal");
        lugares[1] = new Edificio("Edificio Civil", "Edificio de color azul de 4 pisos", 5, "Diagonal a la entrada de la gasca");
        numLugares = 2;

        Espacio espacio1 = new Espacio("Aula 101", 30, Tamano.MEDIANO);
        Espacio espacio2 = new Espacio("Lab 102", 20, Tamano.PEQUENO);
        lugares[0].crearEspacio(espacio1);
        lugares[0].crearEspacio(espacio2);

        reservas[0] = new Reserva("2025/06/01 08:00", "2025/06/01 10:00", EstadoReserva.PENDIENTE, usuarios[0], espacio1);
        numReservas = 1;
    }

    @Override
    public String toString() {
        return "Universidad:" + "\r\nNombre: " + nombreUniversidad +
                "\r\nRector: " + nombreRector + "\r\nTeléfono: " + telefono +
                "\r\nLugares registrados: " + numLugares +
                "\r\nReservas registradas: " + numReservas +
                "\r\nUsuarios registrados: " + numUsuarios;
    }
}
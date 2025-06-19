package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Representa una universidad en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Universidad implements IAdministrador {
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

    // Getters y setters
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

    // Métodos de gestión de lugares
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
        int index = 1;
        for (Lugar lugar : lugares) {
            if (lugar != null) {
                info.append(index++).append(": ").append(lugar.toString()).append("\n");
            }
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

    // Métodos de gestión de reservas
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
        int index = 1;
        for (Reserva reserva : reservas) {
            if (reserva != null) {
                info.append(index++).append(": ").append(reserva.toString()).append("\n");
            }
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

    // Métodos de gestión de usuarios
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
        int index = 1;
        for (Usuario usuario : usuarios) {
            if (usuario != null) {
                info.append(index++).append(": ").append(usuario.toString()).append("\n");
            }
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
        for (Lugar lugar : lugares) {
            if (lugar != null) {
                for (Espacio espacio : lugar.getEspacios()) {
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

    // Métodos de validación de duplicados
    public boolean validarDuplicado(Lugar lugar) {
        for (Lugar l : lugares) {
            if (l != null && l.equals(lugar)) {
                return false;
            }
        }
        return true;
    }

    private boolean validarDuplicado(Lugar lugar, int skipPos) {
        int index = 0;
        for (Lugar l : lugares) {
            if (l != null && index++ != skipPos && l.equals(lugar)) {
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
        int index = 0;
        for (Reserva r : reservas) {
            if (r != null && index++ != skipPos && r.equals(reserva)) {
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
        int index = 0;
        for (Usuario u : usuarios) {
            if (u != null && index++ != skipPos && u.equals(usuario)) {
                return false;
            }
        }
        return true;
    }

    private void inicializar() {
        usuarios[0] = new Usuario("Miguel Merino", "pass123456", "1728803246", "miguel@gmail.com", Rol.INVITADO);
        usuarios[1] = new Usuario("Mishell Quinatoa", "pass456789", "1728803247", "mishell@gmail.com", Rol.DOCENTE);
        usuarios[2] = new Usuario("Juan Pérez", "pass789123", "1728803248", "juan@gmail.com", Rol.ESTUDIANTE);
        usuarios[3] = new Usuario("Ana Gómez", "pass101112", "1728803249", "ana@gmail.com", Rol.ESTUDIANTE);
        usuarios[4] = new Usuario("Carlos López", "pass131415", "1728803250", "carlos@gmail.com", Rol.DOCENTE);
        numUsuarios = 5;

        lugares[0] = new Lugar("Edificio A", "Edificio principal");
        lugares[1] = new Edificio("Edificio Civil", "Edificio de color azul de 4 pisos", 5, "Diagonal a la entrada de la gasca");
        lugares[2] = new Edificio("Edificio B", "Edificio de ciencias", 3, "Cerca del campus central");
        lugares[3] = new Lugar("Biblioteca", "Biblioteca central");
        lugares[4] = new Edificio("Edificio C", "Edificio de laboratorios", 4, "Frente al parqueadero");
        numLugares = 5;

        Espacio espacio1 = new Aula(30, Tamano.MEDIANO, "AUL-001");
        Espacio espacio2 = new Aula(20, Tamano.PEQUENO, "AUL-002");
        Espacio espacio3 = new Aula(40, Tamano.GRANDE, "AUL-003");
        Espacio espacio4 = new Auditorio("Auditorio 1", 100, Tamano.GRANDE, true);
        Espacio espacio5 = new Aula(25, Tamano.MEDIANO, "AUL-004");
        lugares[0].crearEspacio(espacio1);
        lugares[0].crearEspacio(espacio2);
        lugares[1].crearEspacio(espacio3);
        lugares[2].crearEspacio(espacio4);
        lugares[3].crearEspacio(espacio5);

        reservas[0] = new ReservaTemporal("2025/07/01 08:00", "2025/07/01 10:00", EstadoReserva.PENDIENTE, usuarios[0], espacio1, 2);
        reservas[1] = new ReservaSemestre("2025/07/01 11:00", "2025/12/01 11:00", EstadoReserva.PENDIENTE, usuarios[1], espacio3, "2025-1");
        reservas[2] = new ReservaTemporal("2025/07/02 10:00", "2025/07/02 12:00", EstadoReserva.APROBADO, usuarios[2], espacio2, 2);
        reservas[3] = new ReservaTemporal("2025/07/03 14:00", "2025/07/03 16:00", EstadoReserva.PENDIENTE, usuarios[3], espacio4, 2);
        reservas[4] = new ReservaSemestre("2025/07/01 13:00", "2025/12/01 13:00", EstadoReserva.APROBADO, usuarios[4], espacio5, "2025-1");
        numReservas = 5;
    }

    @Override
    public String nuevo(Object obj) {
        if (obj instanceof Usuario) {
            return getUsuarioCRUD().nuevo(obj);
        } else if (obj instanceof Lugar) {
            return getLugarCRUD().nuevo(obj);
        } else if (obj instanceof Reserva) {
            return getReservaCRUD().nuevo(obj);
        }
        return "Error: Tipo de objeto no soportado.";
    }

    @Override
    public String editar(Object obj) {
        if (obj instanceof Usuario) {
            return getUsuarioCRUD().editar(obj);
        } else if (obj instanceof Lugar) {
            return getLugarCRUD().editar(obj);
        } else if (obj instanceof Reserva) {
            return getReservaCRUD().editar(obj);
        }
        return "Error: Tipo de objeto no soportado.";
    }

    @Override
    public String borrar(Object obj) {
        if (obj instanceof Usuario) {
            return getUsuarioCRUD().borrar(obj);
        } else if (obj instanceof Lugar) {
            return getLugarCRUD().borrar(obj);
        } else if (obj instanceof Reserva) {
            return getReservaCRUD().borrar(obj);
        }
        return "Error: Tipo de objeto no soportado.";
    }

    @Override
    public Object buscarPorId(Integer id) {
        return null;
    }

    @Override
    public String listar() {
        return "Use listar() de las clases internas específicas.";
    }

    public class UsuarioCRUD implements IAdministrador {
        @Override
        public String nuevo(Object obj) {
            if (!(obj instanceof Usuario) || !crearUsuario((Usuario) obj)) {
                return "Error: No se pudo crear el usuario, posible duplicado.";
            }
            return "Usuario creado exitosamente con ID: " + ((Usuario) obj).getIdUsuario();
        }

        @Override
        public String editar(Object obj) {
            if (!(obj instanceof Usuario)) {
                return "Error: Objeto inválido.";
            }
            Usuario usuario = (Usuario) obj;
            for (int i = 0; i < numUsuarios; i++) {
                if (usuarios[i].getIdUsuario().equals(usuario.getIdUsuario())) {
                    if (!usuarios[i].getCedulaIdentidad().equals(usuario.getCedulaIdentidad())) {
                        return "Error: No se puede cambiar la cédula.";
                    }
                    if (editarUsuario(i, usuario)) {
                        return "Usuario editado exitosamente.";
                    }
                    return "Error: No se pudo editar el usuario.";
                }
            }
            return "Error: Usuario no encontrado.";
        }

        @Override
        public String borrar(Object obj) {
            if (!(obj instanceof Usuario)) {
                return "Error: Objeto inválido.";
            }
            Usuario usuario = (Usuario) obj;
            for (int i = 0; i < numUsuarios; i++) {
                if (usuarios[i].getIdUsuario().equals(usuario.getIdUsuario())) {
                    if (eliminarUsuario(i)) {
                        return "Usuario eliminado exitosamente.";
                    }
                    return "Error: No se pudo eliminar el usuario.";
                }
            }
            return "Error: Usuario no encontrado.";
        }

        @Override
        public Object buscarPorId(Integer id) {
            if (id == null) return null;
            return buscarUsuario("USR-" + String.format("%03d", id));
        }

        @Override
        public String listar() {
            return consultarUsuarios();
        }
    }

    public class LugarCRUD implements IAdministrador {
        @Override
        public String nuevo(Object obj) {
            if (!(obj instanceof Lugar) || !crearLugar((Lugar) obj)) {
                return "Error: No se pudo crear el lugar, posible duplicado.";
            }
            return "Lugar creado exitosamente con ID: " + ((Lugar) obj).getIdLugar();
        }

        @Override
        public String editar(Object obj) {
            if (!(obj instanceof Lugar)) {
                return "Error: Objeto inválido.";
            }
            Lugar lugar = (Lugar) obj;
            for (int i = 0; i < numLugares; i++) {
                if (lugares[i].getIdLugar().equals(lugar.getIdLugar())) {
                    if (editarLugar(i, lugar)) {
                        return "Lugar editado exitosamente.";
                    }
                    return "Error: No se pudo editar el lugar.";
                }
            }
            return "Error: Lugar no encontrado.";
        }

        @Override
        public String borrar(Object obj) {
            if (!(obj instanceof Lugar)) {
                return "Error: Objeto inválido.";
            }
            Lugar lugar = (Lugar) obj;
            for (int i = 0; i < numLugares; i++) {
                if (lugares[i].getIdLugar().equals(lugar.getIdLugar())) {
                    if (eliminarLugar(i)) {
                        return "Lugar eliminado exitosamente.";
                    }
                    return "Error: No se pudo eliminar el lugar.";
                }
            }
            return "Error: Lugar no encontrado.";
        }

        @Override
        public Object buscarPorId(Integer id) {
            if (id == null) return null;
            for (Lugar lugar : lugares) {
                if (lugar != null && lugar.getIdLugar().equals("LUG-" + String.format("%03d", id))) {
                    return lugar;
                }
            }
            return null;
        }

        @Override
        public String listar() {
            return consultarLugares();
        }
    }

    public class ReservaCRUD implements IAdministrador {
        @Override
        public String nuevo(Object obj) {
            if (!(obj instanceof Reserva) || !crearReserva((Reserva) obj)) {
                return "Error: No se pudo crear la reserva, posible conflicto de horario.";
            }
            return "Reserva creada exitosamente con ID: " + ((Reserva) obj).getIdReserva();
        }

        @Override
        public String editar(Object obj) {
            if (!(obj instanceof Reserva)) {
                return "Error: Objeto inválido.";
            }
            Reserva reserva = (Reserva) obj;
            for (int i = 0; i < numReservas; i++) {
                if (reservas[i].getIdReserva().equals(reserva.getIdReserva())) {
                    if (editarReserva(i, reserva)) {
                        return "Reserva editada exitosamente.";
                    }
                    return "Error: No se pudo editar la reserva.";
                }
            }
            return "Error: Reserva no encontrada.";
        }

        @Override
        public String borrar(Object obj) {
            if (!(obj instanceof Reserva)) {
                return "Error: Objeto inválido.";
            }
            Reserva reserva = (Reserva) obj;
            for (int i = 0; i < numReservas; i++) {
                if (reservas[i].getIdReserva().equals(reserva.getIdReserva())) {
                    if (eliminarReserva(i)) {
                        return "Reserva eliminada exitosamente.";
                    }
                    return "Error: No se pudo eliminar la reserva.";
                }
            }
            return "Error: Reserva no encontrada.";
        }

        @Override
        public Object buscarPorId(Integer id) {
            if (id == null) return null;
            return buscarReservaId("RES-" + String.format("%03d", id));
        }

        @Override
        public String listar() {
            return consultarReservas();
        }
    }

    public UsuarioCRUD getUsuarioCRUD() {
        return new UsuarioCRUD();
    }

    public LugarCRUD getLugarCRUD() {
        return new LugarCRUD();
    }

    public ReservaCRUD getReservaCRUD() {
        return new ReservaCRUD();
    }
}
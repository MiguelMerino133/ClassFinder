package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Representa un usuario en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Usuario {
    private static int contadorUsuarios = 1;
    private static final String PREFIJO = "USR-";
    private final String idUsuario;
    private String nombre;
    private String contrasena;
    private String cedulaIdentidad;
    private String correo;
    private Rol rol;

    public Usuario() {
        idUsuario = PREFIJO + String.format("%03d", contadorUsuarios++);
        nombre = "Usuario";
        contrasena = "123456";
        cedulaIdentidad = "1728803246";
        correo = "usuario@example.com";
        rol = Rol.ESTUDIANTE;
    }

    public Usuario(String idUsuario, String nombre, String contrasena, String cedulaIdentidad, String correo, Rol rol) {
        this.idUsuario = idUsuario != null && Validadores.esIdValido(idUsuario) ? idUsuario : PREFIJO + String.format("%03d", contadorUsuarios++);
        setNombre(nombre);
        setContrasena(contrasena);
        setCedulaIdentidad(cedulaIdentidad);
        setCorreo(correo);
        setRol(rol);
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = (nombre != null && Validadores.esTextoValido(nombre)) ? nombre : "Usuario";
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = (contrasena != null && contrasena.length() >= 6) ? contrasena : "123456";
    }

    public String getCedulaIdentidad() {
        return cedulaIdentidad;
    }

    public void setCedulaIdentidad(String cedulaIdentidad) {
        this.cedulaIdentidad = (cedulaIdentidad != null && Validadores.esCedulaValida(cedulaIdentidad)) ? cedulaIdentidad : "1728803246";
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = (correo != null && Validadores.esCorreoValido(correo)) ? correo : "usuario@example.com";
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = (rol != null) ? rol : Rol.ESTUDIANTE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return idUsuario.equals(usuario.idUsuario);
    }

    @Override
    public int hashCode() {
        return idUsuario.hashCode();
    }

    @Override
    public String toString() {
        return "Usuario:" + "\r\nID: " + idUsuario + "\r\nNombre: " + nombre +
                "\r\nCédula: " + cedulaIdentidad + "\r\nCorreo: " + correo +
                "\r\nRol: " + rol.getDescripcion();
    }
}
package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Representa un usuario del sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Usuario {

    private String idUsuario;
    private String nombre;
    private String contrasena;
    private String cedulaIdentidad;
    private String correo;
    private Rol rol;

    /**
     * Constructor por defecto.
     * Inicializa un usuario con valores predeterminados.
     */
    public Usuario() {
        idUsuario = "USR-001";
        nombre = "Miguel";
        contrasena = "123456";
        cedulaIdentidad = "1728803246";
        correo = "miguel@gmail.com";
        rol = Rol.ESTUDIANTE;
    }

    /**
     * Constructor con parámetros.
     * @param idUsuario identificador del usuario
     * @param nombre nombre del usuario
     * @param contrasena contraseña del usuario
     * @param cedulaIdentidad cédula de identidad del usuario
     * @param correo correo electrónico del usuario
     * @param rol rol del usuario
     */
    public Usuario(String idUsuario, String nombre, String contrasena, String cedulaIdentidad, String correo, Rol rol) {
        setIdUsuario(idUsuario);
        setNombre(nombre);
        setContrasena(contrasena);
        setCedulaIdentidad(cedulaIdentidad);
        setCorreo(correo);
        setRol(rol);
    }

    /**
     * Obtiene el identificador del usuario.
     * @return el identificador del usuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el identificador del usuario.
     * Valida que el valor no sea nulo ni inválido, usando un valor por defecto si falla.
     * @param idUsuario nuevo identificador
     */
    public void setIdUsuario(String idUsuario) {
        if (idUsuario == null || !Validadores.esIdValido(idUsuario)) {
            this.idUsuario = "USR-001";
        } else {
            this.idUsuario = idUsuario;
        }
    }

    /**
     * Obtiene el nombre del usuario.
     * @return el nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * Valida que el valor no sea nulo ni inválido, usando un valor por defecto si falla.
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        if (nombre == null || !Validadores.esTextoValido(nombre)) {
            this.nombre = "Miguel";
        } else {
            this.nombre = nombre;
        }
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return la contraseña del usuario
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del usuario.
     * Valida que el valor no sea nulo ni inválido (mínimo 6 caracteres), usando un valor por defecto si falla.
     * @param contrasena nueva contraseña
     */
    public void setContrasena(String contrasena) {
        if (contrasena == null || !Validadores.esContrasenaValida(contrasena)) {
            this.contrasena = "123456";
        } else {
            this.contrasena = contrasena;
        }
    }

    /**
     * Obtiene la cédula de identidad del usuario.
     * @return la cédula de identidad
     */
    public String getCedulaIdentidad() {
        return cedulaIdentidad;
    }

    /**
     * Establece la cédula de identidad del usuario.
     * Valida que el valor no sea nulo ni inválido, usando un valor por defecto si falla.
     * @param cedulaIdentidad nueva cédula
     */
    public void setCedulaIdentidad(String cedulaIdentidad) {
        if (cedulaIdentidad == null || !Validadores.esCedulaValida(cedulaIdentidad)) {
            this.cedulaIdentidad = "1728803246";
        } else {
            this.cedulaIdentidad = cedulaIdentidad;
        }
    }

    /**
     * Obtiene el correo del usuario.
     * @return el correo del usuario
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo del usuario.
     * Valida que el valor no sea nulo ni inválido, usando un valor por defecto si falla.
     * @param correo nuevo correo
     */
    public void setCorreo(String correo) {
        if (correo == null || !Validadores.esCorreoValido(correo)) {
            this.correo = "miguel@gmail.com";
        } else {
            this.correo = correo;
        }
    }

    /**
     * Obtiene el rol del usuario.
     * @return el rol del usuario
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     * Valida que el valor no sea nulo ni inválido, usando un valor por defecto si falla.
     * @param rol nuevo rol
     */
    public void setRol(Rol rol) {
        if (rol == null || !Validadores.esRolValido(rol)) {
            this.rol = Rol.ESTUDIANTE;
        } else {
            this.rol = rol;
        }
    }


    public String toString(){
        return "Usuario :"+"\r\nID: "+idUsuario+"\r\nNombre: "+nombre+"\r\nCedula: "+cedulaIdentidad
                +"\r\nCorreo: "+correo+"\r\nRol: "+rol;
    }
}
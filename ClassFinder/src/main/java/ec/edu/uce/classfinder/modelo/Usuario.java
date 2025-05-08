package ec.edu.uce.classfinder.modelo;

public class Usuario {

   private String idUsuario;
   private String nombre;
   private String contrasena;
   private String cedulaIdentidad;
   private String correo;
   private String rol;

    public Usuario(){}

    public Usuario(String idUsuario, String nombre, String cedulaIdentidad, String correo, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.cedulaIdentidad = cedulaIdentidad;
        this.correo = correo;
        this.rol = rol;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedulaIdentidad() {
        return cedulaIdentidad;
    }

    public void setCedulaIdentidad(String cedulaIdentidad) {
        this.cedulaIdentidad = cedulaIdentidad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void registrarUsuario() {
        System.out.println("Usuario registrado: " + nombre);
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


    public void registrarReserva() {
        System.out.println("Reserva registrada por: " + nombre);
    }

    public void consultarEspacio() {
        System.out.println("Consultando espacio...");
    }

    public void consultarLugar() {
        System.out.println("Consultando lugar...");
    }

    public void cancelarReserva() {
        System.out.println("Reserva cancelada por: " + nombre);
    }



}

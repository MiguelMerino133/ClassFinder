package ec.edu.uce.classfinder.modelo;

public class Lugar {

    private String idLugar;
    private String nombre;
    private String descripcion;
    private String idUniversidad;


    public Lugar() {}

    public Lugar(String idLugar, String nombre, String descripcion, String idUniversidad) {
        this.idLugar = idLugar;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idUniversidad = idUniversidad;

    }


    public String getIdLugar() {
        return idLugar;
    }
    public void setIdLugar(String idLugar) {
        this.idLugar = idLugar;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdUniversidad() {
        return idUniversidad;
    }
    public void setIdUniversidad(String idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public void registrarLugar() {}
    public void modificarLugar() {}
    public void eliminarLugar() {}

}

package ec.edu.uce.classfinder.modelo;

public class Universidad {

    private String idUniversidad;
    private String nombreUniversidad;
    private String nombreRector;
    private String telefono;


    //Constructores

    public Universidad() {}

    public Universidad(String idUniversidad, String nombreUniversidad, String nombreRector, String telefono) {
        this.idUniversidad = idUniversidad;
        this.nombreUniversidad = nombreUniversidad;
        this.nombreRector = nombreRector;
        this.telefono = telefono;
    }

    //Get y Set
    public String getIdUniversidad() {
        return idUniversidad;
    }
    public void setIdUniversidad(String idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public String getNombreUniversidad() {
        return nombreUniversidad;
    }
    public void setNombreUniversidad(String nombreUniversidad) {
        this.nombreUniversidad = nombreUniversidad;
    }

    public String getNombreRector() {
        return nombreRector;
    }
    public void setNombreRector(String nombreRector) {
        this.nombreRector = nombreRector;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public void gestionarUsuario() {}
    public void gestionarReserva() {}
    public void gestionarLugar() {}
    public void gestionarEspacio() {}

}

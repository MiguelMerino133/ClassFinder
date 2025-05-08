package ec.edu.uce.classfinder.modelo;

public class Edificio extends Lugar {

    private int numeroPisos;
    private String ubicacion;


    public Edificio(String idLugar, String nombre, String descripcion, String idUniversidad,
                    int numeroPisos, String ubicacion) {
        super(idLugar, nombre, descripcion, idUniversidad);
        this.numeroPisos = numeroPisos;
        this.ubicacion = ubicacion;
    }

    public int getNumeroPisos() {
        return numeroPisos;
    }
    public void setNumeroPisos(int numeroPisos) {
        this.numeroPisos = numeroPisos;
    }

    public String getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void asignarUbicacion() {}
    public void agregarEdificio() {}
}

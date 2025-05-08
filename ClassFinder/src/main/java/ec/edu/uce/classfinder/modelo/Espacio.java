package ec.edu.uce.classfinder.modelo;

public class Espacio {

    private String idEspacio;
    private String idLugar;
    private String nombre;
    private int capacidad;
    private String tamano;


    public Espacio() {}

    public Espacio(String idEspacio, String idLugar, String nombre, int capacidad, String tamaño) {
        this.idEspacio = idEspacio;
        this.idLugar = idLugar;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tamano = tamano;
    }

    public String getIdEspacio() {
        return idEspacio;
    }
    public void setIdEspacio(String idEspacio) {
        this.idEspacio = idEspacio;
    }

    public String getIdLugar() {
        return idLugar;
    }
    public void setIdLugar(String idLugar) {
        this.idLugar = idLugar;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getTamano() {
        return tamano;
    }
    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public void registrarEspacio() {}
    public void actualizarCapacidad() {}
    public void consultarEspacio(){}


}

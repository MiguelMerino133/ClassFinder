package ec.edu.uce.classfinder.modelo;

public class Auditorio extends Espacio {

    private boolean equipoSonido;

    public Auditorio() {}

    public Auditorio(String idEspacio, String idLugar, String nombre, int capacidad, String tamaño,
                     boolean equipoSonido) {
        super(idEspacio, idLugar, nombre, capacidad, tamaño);
        this.equipoSonido = equipoSonido;
    }

    public boolean isEquipoSonido() {
        return equipoSonido;
    }
    public void setEquipoSonido(boolean equipoSonido) {
        this.equipoSonido = equipoSonido;
    }

    public void configurarEquipo() {}

}

package ec.edu.uce.classfinder.modelo;

public class Aula extends Espacio {

    private String numeroAula;

    public Aula(String idEspacio, String idLugar, String nombre, int capacidad, String tamaño,
                String numeroAula) {
        super(idEspacio, idLugar, nombre, capacidad, tamaño);
        this.numeroAula = numeroAula;
    }

    public String getNumeroAula() {
        return numeroAula;
    }
    public void setNumeroAula(String numeroAula) {
        this.numeroAula = numeroAula;
    }

    public void asignarNumero() {}
}

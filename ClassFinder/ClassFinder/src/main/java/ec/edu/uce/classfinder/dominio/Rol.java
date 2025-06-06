package ec.edu.uce.classfinder.dominio;

public enum Rol {
    ESTUDIANTE(1, "Estudiante"),
    DOCENTE(2, "Docente"),
    INVITADO(3, "Invitado");

    private final int numero;
    private final String descripcion;

    Rol(int numero, String descripcion) {
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public int getNumero() {
        return numero;
    }

    public String getDescripcion() {
        return descripcion;
    }



}

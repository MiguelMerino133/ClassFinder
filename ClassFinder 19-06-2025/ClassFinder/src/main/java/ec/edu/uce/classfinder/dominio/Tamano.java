package ec.edu.uce.classfinder.dominio;

public enum Tamano {
    PEQUENO(1, "Pequeño"),
    MEDIANO(2, "Mediano"),
    GRANDE(3, "Grande");

    private final int numero;
    private final String descripcion;

    Tamano(int numero, String descripcion) {
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
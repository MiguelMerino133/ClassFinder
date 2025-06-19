package ec.edu.uce.classfinder.dominio;

public enum EstadoReserva {
    PENDIENTE(1,"Pendiente"),
    APROBADO(2,"Aprobado"),
    RECHAZADO(3,"Rechazado"),
    EXPIRADA(4, "Expirada");

    private final String descripcion;
    private final int numero;

    EstadoReserva(int numero, String descripcion) {
        this.numero=numero;
        this.descripcion = descripcion;
    }

    public int getNumero(){
        return numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

}

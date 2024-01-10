package org.iesalandalus.programacion.reservashotel.dominio;

public enum TipoHabitacion {

    SIMPLE("Habitaci�n Simple", 1),
    DOBLE("Habitaci�n Doble", 2),
    TRIPLE("Habitaci�n Triple", 3),
    SUITE("Suite de Lujo", 4);

    private String cadenaAMostrar;

    // Constructor
    private TipoHabitacion(String descripcion, int capacidadMaxima) {
        this.cadenaAMostrar = descripcion + " (Max. " + capacidadMaxima + " personas)";
    }

    // M�todo getter
    public String getCadenaAMostrar() {
        return cadenaAMostrar;
    }

    // M�todo toString
    @Override
    public String toString() {
        return cadenaAMostrar;
    }
}

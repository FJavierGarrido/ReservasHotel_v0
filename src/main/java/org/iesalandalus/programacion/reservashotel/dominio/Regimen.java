package org.iesalandalus.programacion.reservashotel.dominio;

public enum Regimen {

    SOLO_ALOJAMIENTO("Solo alojamiento", 10),
    ALOJAMIENTO_DESAYUNO("Alojamiento y desayuno", 15),
    MEDIA_PENSION("Media pensi�n", 20),
    PENSION_COMPLETA("Pensi�n completa", 25);


    private final String descripcion;
    private final double incrementoPrecio;

    // Constructor
    private Regimen(String descripcion, int incrementoPrecio) {
        this.descripcion = descripcion;
        this.incrementoPrecio = incrementoPrecio;
    }

    // M�todo getter para la descripci�n
    public double getIncrementoPrecio() {
        return incrementoPrecio;
    }

    // M�todo toString
    @Override
    public String toString() {
        return "R�gimen{" +
                "descripcion='" + descripcion + '\'' +
                ", incrementoPrecioPorPersona=" + incrementoPrecio +
                '}';
    }

}

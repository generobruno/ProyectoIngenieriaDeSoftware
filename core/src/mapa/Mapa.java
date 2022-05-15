package mapa;

import graficos.Pantalla;

/**
 * Clase Mapa.
 * Es el entorno por donde se mueve el personaje. Está formado por
 * cuadros (o tiles).
 */
public abstract class Mapa {
    // Ancho del mapa
    private int ancho;
    // Alto del mapa
    private int alto;
    // Array de cuadros que conforman el mapa
    private int[] cuadros;

    /**
     * Constructor de la clase. Crea un mapa aleatorio.
     * @param ancho ancho del mapa.
     * @param alto alto del mapa.
     */
    public Mapa(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        cuadros = new int[ancho*alto];
        generarMapa();
    }

    /**
     * Constructor de la clase. Carga un mapa predefinido.
     * @param ruta ruta donde esta el mapa.
     */
    public Mapa(String ruta) {
        cargarMapa(ruta);
    }

    private void generarMapa() { }

    private void cargarMapa(String ruta) { }

    public void actualizar() { }

    public void mostrar(int compensacionX, int compensacionY, Pantalla pantalla) { }

}

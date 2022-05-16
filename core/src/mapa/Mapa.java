package mapa;

import graficos.Pantalla;

/**
 * Clase Mapa.
 * Es el entorno por donde se mueve el personaje. Está formado por
 * cuadros (o tiles).
 */
public abstract class Mapa {
    // Ancho del mapa
    protected int ancho;
    // Alto del mapa
    protected int alto;
    // Array de cuadros que conforman el mapa
    protected int[] cuadros;

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

    protected void generarMapa() { }

    private void cargarMapa(String ruta) { }

    public void actualizar() { }

    /**
     * Clase mostrar.
     * Traduce el movimiento de los cuadros a pixeles, haciendo Bit Shifting.
     * @param compensacionX Movimiento del jugador en el eje x
     * @param compensacionY Movimiento del jugador en el eje y
     * @param pantalla Pantalla donde se muestra el mapa
     */
    public void mostrar(int compensacionX, int compensacionY, Pantalla pantalla) {
        // Oeste
        int o = compensacionX >> 5;
        // Este
        int e = (compensacionX + pantalla.getAncho()) >> 5;
        // Norte
        int n = compensacionY >> 5;
        // Sur
        int s = (compensacionY + pantalla.getAlto()) >> 5;
    }

}

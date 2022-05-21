package mapa;

import graficos.Pantalla;
import mapa.cuadro.Cuadro;

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
    public void mostrar(final int compensacionX, final int compensacionY, final Pantalla pantalla) {

        pantalla.setDiferencia(compensacionX,compensacionY);

        // Oeste
        int o = compensacionX >> 5; // Equivalente a dividir entre 32
        // Este
        int e = (compensacionX + pantalla.getAncho() + Cuadro.LADO) >> 5;
        // Norte
        int n = compensacionY >> 5;
        // Sur
        int s = (compensacionY + pantalla.getAlto() + Cuadro.LADO) >> 5;

        for(int y = n; y < s; y++) {
            for(int x = o; x < e; x++) {
                getCuadro(x,y).mostrar(x,y,pantalla);
            }
        }

    }

    public Cuadro getCuadro(final int x, final int y) {
        if(x < 0 || y < 0 || x >= ancho || y >= alto) {
            return Cuadro.VACIO;
        }
        switch(cuadros[x + y * ancho]) {
            case 0:
                return Cuadro.PASTO;
            case 1:
                return Cuadro.PASTO1;
            case 2:
                return Cuadro.PASTO2;
            default:
                return Cuadro.VACIO;
        }
    }

}

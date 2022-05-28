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
    // Array de cuadros que conforman el mapa
    protected Cuadro[] cuadrosCatalogo;


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
        generarMapa();
    }

    protected void generarMapa() { }

    protected void cargarMapa(String ruta) { }

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
                if(x < 0 || y < 0 || x >= ancho || y >= alto){
                    Cuadro.VACIO.mostrar( x, y, pantalla);
                }else{
                    cuadrosCatalogo[x + y * ancho].mostrar( x, y, pantalla);
                }
            }
        }

    }

    public Cuadro getCuadro(final int x, final int y) {
        if(x < 0 || y < 0 || x >= ancho || y >= alto) {
            return Cuadro.VACIO;
        }
        return switch (cuadros[x + y * ancho]) {
            case 0 -> Cuadro.PISO;
            case 1 -> Cuadro.UNION1;
            case 2 -> Cuadro.UNION2;
            case 3 -> Cuadro.UNION3;
            case 4 -> Cuadro.UNION4;
            case 5 -> Cuadro.UNION5;
            case 6 -> Cuadro.UNION6;
            case 7 -> Cuadro.ESQ_INF_DER;
            case 8 -> Cuadro.ESQ_SUP_DER;
            case 9 -> Cuadro.ESQ_INF_IZQ;
            case 10 -> Cuadro.ESQ_SUP_IZQ;
            case 11 -> Cuadro.LADO_DER;
            case 12 -> Cuadro.LADO_IZQ;
            case 13 -> Cuadro.LADO_HORIZ;
            default -> Cuadro.VACIO;
        };
    }

}

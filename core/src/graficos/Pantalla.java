package graficos;

import mapa.cuadro.Cuadro;

/**
 * Clase Pantalla. Esta clase se encarga de ensamblar todas las
 * interfaces gráficas que hacen al juego.
 */
public final class Pantalla {
    // Ancho de la pantalla
    private final int ancho;
    // Alto de la pantalla
    private final int alto;
    // Array de los pixeles
    public final int[] pixeles;

    //TODO Esto es temporal
    private final static int LADO_SPRITE = 32;
    private final static int MASCARA_SPRITE = LADO_SPRITE-1;
    //TODO Fin de lo temporal

    /**
     * Constructor de la clase Pantalla
     * @param ancho ancho de la pantalla
     * @param alto alto de la pantalla
     */
    public Pantalla(final int ancho, final int alto) {
        this.ancho = ancho;
        this.alto = alto;
        pixeles = new int[ancho*alto];
    }

    /**
     * Método que limpia la pantalla, en cada actualización.
     */
    public void limpiar() {
        for(int i = 0; i < pixeles.length; i++) {
            // Se ponen en 0 hex, es decir, en negro.
            pixeles[i] = 0;
        }
    }

    /**
     * Método que dibuja la pantalla, de izquierda a derecha y de arriba
     * hacia abajo.
     * @param compensacionX movimiento realizado en el eje X por el pj
     * @param compensacionY movimiento realizado en el eje Y por el pj
     */
    public void mostrar(final int compensacionX, final int compensacionY) {

        for(int y = 0; y < alto; y++) {
            int posicionY = y + compensacionY;
            // Si se llega a los limites de la pantalla (o mapa):
            if(posicionY < 0 || posicionY >= alto) {
                // Dejamos de dibujar en este eje
                continue;
            }

            for(int x = 0; x < ancho; x++) {
                int posicionX = x + compensacionX;
                // Si se llega a los limites de la pantalla (o mapa):
                if(posicionX < 0 || posicionX >= ancho) {
                    // Dejamos de dibujar en este eje
                    continue;
                }
                // Se llena el array de pixeles con lo que se mostrará por pantalla
                // TODO Esto es temporal
                pixeles[posicionX + posicionY * ancho] =
                        Sprite.ejPj1.pixeles[(x & MASCARA_SPRITE) + (y & MASCARA_SPRITE) * LADO_SPRITE];
                // TODO Fin de lo temporal

            } // Fin del for para X
        } // Fin del for para Y
    } // Fin de mostrar()

    /**
     * Método mostrarCuadro. Se encarga de dibujar un cuadro en la pantalla.
     * @param compensacionX movimiento realizado en el eje X por el pj
     * @param compensacionY movimiento realizado en el eje Y por el pj
     * @param cuadro Cuadro que se dibujará
     */
    public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro) {
        for(int y = 0; y < cuadro.sprite.getLado(); y++) {
            int posicionY = y + compensacionY;
            for(int x = 0; x < cuadro.sprite.getLado(); x++) {
                int posicionX = x + compensacionX;
                // Condicional para que no se dibujen los cuadros fuera de la pantalla
                if(posicionX < 0 || posicionX > ancho || posicionY < 0 || posicionY > alto) {
                    break;
                }
                pixeles[posicionX + posicionY * ancho] =
                        cuadro.sprite.pixeles[x + y * cuadro.sprite.getLado()];
            } // Fin del for x
        } // Fin del for y

    }

    /**
     * Getter del ancho
     * @return Ancho de la pantalla
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Getter del alto
     * @return Alto de la pantalla
     */
    public int getAlto() {
        return alto;
    }
}

package graficos;

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

}

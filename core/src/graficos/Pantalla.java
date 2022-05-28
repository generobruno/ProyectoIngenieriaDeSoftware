package graficos;

import entes.criaturas.Jugador;
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

    private int diferenciaX;
    private int diferenciaY;


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
     * Método mostrarCuadro. Se encarga de dibujar un cuadro en la pantalla.
     * @param compensacionX movimiento realizado en el eje X por el pj
     * @param compensacionY movimiento realizado en el eje Y por el pj
     * @param cuadro Cuadro que se dibujará
     */
    public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro) {
        compensacionX -= diferenciaX;
        compensacionY -= diferenciaY;

        for(int y = 0; y < cuadro.sprite.getLado(); y++) {
            int posicionY = y + compensacionY;
            for(int x = 0; x < cuadro.sprite.getLado(); x++) {
                int posicionX = x + compensacionX;
                // Condicional para que no se dibujen los cuadros fuera de la pantalla
                if(posicionX < -cuadro.sprite.getLado() || posicionX >= ancho || posicionY < 0 || posicionY >= alto) {
                    break;
                }
                if(posicionX < 0) {
                    posicionX = 0;
                }
                pixeles[posicionX + posicionY * ancho] =
                        cuadro.sprite.pixeles[x + y * cuadro.sprite.getLado()];
            } // Fin del for x
        } // Fin del for y
    }

    // TODO VER COMO ELIMINAR COLOR DE FONDO
    public void mostrarJugador(int compensacionX, int compensacionY, Jugador jugador) {
        compensacionX -= diferenciaX;
        compensacionY -= diferenciaY;

        for(int y = 0; y < jugador.getSprite().getLado(); y++) {
            int posicionY = y + compensacionY;
            for(int x = 0; x < jugador.getSprite().getLado(); x++) {
                int posicionX = x + compensacionX;
                // Condicional para que no se dibujen los cuadros fuera de la pantalla
                if(posicionX < -jugador.getSprite().getLado() || posicionX >= ancho || posicionY < 0 || posicionY >= alto) {
                    break;
                }
                if(posicionX < 0) {
                    posicionX = 0;
                }
                if(jugador.getSprite().pixeles[x + y * jugador.getSprite().getLado()] < 0xffffffff)
                pixeles[posicionX + posicionY * ancho] =
                        jugador.getSprite().pixeles[x + y * jugador.getSprite().getLado()];
            } // Fin del for x
        } // Fin del for y
    }

    public void setDiferencia(final int diferenciaX,final int diferenciaY) {
        this.diferenciaX = diferenciaX;
        this.diferenciaY = diferenciaY;
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

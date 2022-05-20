package entes.criaturas;

import control.Teclado;
import graficos.Pantalla;
import graficos.Sprite;

public class Jugador extends Criatura {
    // Teclado con el que se mueve al jugador
    private Teclado teclado;
    // Sprite del jugador

    /**
     * Constructor de la clase
     * Crea al jugador
     * @param teclado Teclado con el que se mueve al jugador
     */
    public Jugador(Teclado teclado, Sprite sprite) {
        this.teclado = teclado;
        this.sprite = sprite;
    }

    /**
     * Constructor de la clase
     * @param teclado Teclado con el que se mueve al jugador
     * @param posicionX Posición X del mapa donde aparece el jugador
     * @param posicionY Posición Y del mapa donde aparece el jugador
     */
    public Jugador(Teclado teclado, int posicionX, int posicionY, Sprite sprite) {
        this.teclado = teclado;
        this.sprite = sprite;
        this.x = posicionX;
        this.y = posicionY;
    }

    public void actualizar() {

        int desplazamientoX = 0;
        int desplazamientoY = 0;

        if(teclado.arriba) {
            desplazamientoY--;
        }
        if(teclado.abajo) {
            desplazamientoY++;
        }
        if(teclado.derecha) {
            desplazamientoX++;
        }
        if(teclado.izquierda) {
            desplazamientoX--;
        }

        // El jugador solo se mueve si se presionó una tecla de movimiento
        if(desplazamientoX != 0 || desplazamientoY != 0) {
            mover(desplazamientoX, desplazamientoY);
        }

    }

    @Override
    public void mostrar(Pantalla pantalla) {
        pantalla.mostrarJugador(x,y,this);
    }
}

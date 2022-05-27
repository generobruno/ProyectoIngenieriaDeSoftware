package entes.criaturas;

import entes.Ente;
import graficos.Pantalla;
import graficos.Sprite;

public abstract class Criatura extends Ente {
    // Sprite de la criatura
    protected Sprite sprite;
    // Dirección a la que apunta la criatura
    protected char direccion = 's';
    // Movimiento de la criatura
    protected boolean enMovimiento = false;

    @Override
    public void actualizar() {
    }

    @Override
    public void mostrar() {
    }

    public void mover(int desplazamientoX, int desplazamientoY) {
        // La criatura se mueve hacia el este
        if(desplazamientoX > 0) {
            direccion = 'e';
        }
        // La criatura se mueve hacia el oeste
        if(desplazamientoX < 0) {
            direccion = 'o';
        }
        // La criatura se mueve hacia el sur
        if(desplazamientoY > 0) {
            direccion = 's';
        }
        // La criatura se mueve hacia el norte
        if(desplazamientoY < 0) {
            direccion = 'n';
        }
        // Si la criatura esta viva, se mueve
        if(!isEliminado()) {
            if(!enColision(desplazamientoX,0)) {
                modificarPosicionX(desplazamientoX);
            }
            if(!enColision(0,desplazamientoY)) {
                modificarPosicionY(desplazamientoY);
            }
        }
    }

    /**
     * Método enColision
     * Comprueba si la criatura se está colisionando con un cuadro sólido
     * @return
     */
    private boolean enColision(int desplazamientoX, int desplazamientoY) {
        // Asumimos que por defecto no estamos colisionando con nada
        boolean colision = false;

        // Posición siguiente del Jugador
        int posX = x + desplazamientoX;
        int posY = y + desplazamientoY;

        // Margen del Tile de Jugador que colisionaría
        int margenIzquierdo = -6;
        int margenDerecho = 18;
        int margenSuperior = -4;
        int margenInferior = 31;

        // Bordes del Tile de Jugador
        int bordeIzq = (posX + margenDerecho)/sprite.getLado();
        int bordeDer = (posX + margenDerecho + margenIzquierdo)/ sprite.getLado();
        int bordeSup = (posY + margenInferior)/sprite.getLado();
        int bordeInf = (posY + margenInferior + margenSuperior)/sprite.getLado();

        // Comprobamos las esquinas del Tile para ver si hay colisión
        //TODO

        return colision;
    }

    public Sprite getSprite() {
        return sprite;
    }


    public abstract void mostrar(Pantalla pantalla);
}

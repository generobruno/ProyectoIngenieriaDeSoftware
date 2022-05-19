package entes.criaturas;

import entes.Ente;
import graficos.Sprite;

public abstract class Criatura extends Ente {
    // Sprite de la criatura
    protected Sprite sprite;
    // Dirección a la que apunta la criatura
    protected char direccion = 'n';
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
            modificarPosicionX(desplazamientoX);
            modificarPosicionY(desplazamientoY);
        }
    }

    /**
     * Método enColision
     * Comprueba si la criatura se esta colisionando con un cuadro solido
     * @return
     */
    private boolean enColision() {
        return false;
    }


}

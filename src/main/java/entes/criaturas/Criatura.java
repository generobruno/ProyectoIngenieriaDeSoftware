package entes.criaturas;

import entes.Ente;
import graficos.Pantalla;
import graficos.Sprite;

public abstract class Criatura extends Ente {
    // Sprite de la criatura
    protected Sprite sprite;
    // Dirección a la que apunta la criatura
    protected char direccion = 'n';
    // Movimiento de la criatura
    protected boolean enMovimiento = false;

    // Estadísticas de personaje
    protected int vidaMax;
    protected int salud;

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
        // Si la criatura esta viva...
        if(!isEliminado()) {
            // ... Y si no está en colisión, se mueve
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
     * @return Si la criatura esta en colisión
     */
    private boolean enColision(int desplazamientoX, int desplazamientoY) {
        // Asumimos que por defecto no estamos colisionando con nada
        boolean colision = false;
        // TODO SOLVE
        /*
        // Posición siguiente del Jugador
        int posX = x + desplazamientoX;
        int posY = y + desplazamientoY;

        // Margen del Tile de Jugador que colisionaría
        int margenIzquierdo = -8;
        int margenDerecho = 32;
        int margenSuperior = -32;
        int margenInferior = 0;

        // Bordes del Tile de Jugador
        int bordeIzq = (posX + margenDerecho)/sprite.getLado();
        int bordeDer = (posX + margenDerecho + margenIzquierdo)/sprite.getLado();
        int bordeSup = (posY + margenInferior)/sprite.getLado();
        int bordeInf = (posY + margenInferior + margenSuperior)/sprite.getLado();

        // Comprobamos las esquinas del Tile para ver si hay colisión
        if(mapa.getCuadroCatalogo(bordeIzq+bordeSup * mapa.getAncho()).isSolido()) {
            colision = true;
        }
        if(mapa.getCuadroCatalogo(bordeIzq+bordeInf * mapa.getAncho()).isSolido()) {
            colision = true;
        }
        if(mapa.getCuadroCatalogo(bordeDer+bordeSup * mapa.getAncho()).isSolido()) {
            colision = true;
        }
        if(mapa.getCuadroCatalogo(bordeDer+bordeInf * mapa.getAncho()).isSolido()) {
            colision = true;
        }
        */

        return colision;
    }

    public Sprite getSprite() {
        return sprite;
    }


    public abstract void mostrar(Pantalla pantalla);
}

package entes.criaturas;

import graficos.Pantalla;
import graficos.observer.Observer;

public class Enemigo extends Criatura {
    // ID única para cada enemigo
    private int idEnemigo;
    // Posición del enemigo
    protected int posicionX;
    protected int posicionY;
    // Nombre del enemigo
    private String nombre;

    public Enemigo(final int idEnemigo, final String nombre, final int vidaMax) {
        this.idEnemigo = idEnemigo;

        this.posicionX = 0;
        this.posicionY = 0;

        this.nombre = nombre;
        this.vidaMax = vidaMax;
        this.salud = vidaMax;
    }

    @Override
    public void actualizar() {
    }

    @Override
    public void dibujar(Pantalla pantalla) {

    }

    /**
     * Método setPosición.
     * Establece la posición del enemigo
     * @param posicionX Posición en el eje x
     * @param posicionY Posición en el eje y
     */
    public void setPosicion(final int posicionX, final int posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    /**
     * Getter de la posición en x
     * @return Posición en x
     */
    public int getPosicionX() {
        return posicionX;
    }

    /**
     * Getter de la posición en y
     * @return Posición en y
     */
    public int getPosicionY() {
        return posicionY;
    }

    /**
     * Getter de la ID del enemigo
     * @return ID del enemigo
     */
    public int getIdEnemigo() {
        return idEnemigo;
    }

    /**
     * Getter de la salud del enemigo
     * @return Salud del enemigo
     */
    public int getSalud() {
        return salud;
    }

}

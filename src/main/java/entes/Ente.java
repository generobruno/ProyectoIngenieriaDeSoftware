package entes;

import mapa.Mapa;

public abstract class Ente {
    // Posición x del ente en el mapa
    protected int x;
    // Posición y del ente en el mapa
    protected int y;
    // Estado del ente en el mapa
    private boolean eliminado = false;
    // Mapa donde se muestra el ente
    protected Mapa mapa;

    public void actualizar() {
    }

    public void mostrar() {
    }

    public void eliminar() {
        eliminado = true;
    }

    public int getPosicionX() {
        return x;
    }

    public int getPosicionY() {
        return y;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void modificarPosicionX(int x) {
        this.x += x;
    }

    public void modificarPosicionY(int y) {
        this.y += y;
    }
}

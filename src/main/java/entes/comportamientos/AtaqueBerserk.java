package entes.comportamientos;

import control.Teclado;
import entes.criaturas.Jugador;

public class AtaqueBerserk implements AttackBehavior {
    // Teclado
    private Teclado teclado;
    // Jugador
    private Jugador jugador;

    public AtaqueBerserk(Teclado teclado, Jugador jugador) {
        this.teclado = teclado;
        this.jugador = jugador;
    }

    /**
     * Método atacar de AtaqueBerserk
     * Un ataque en modo berserk no consume estamina del
     * jugador y tiene un cool-down menor
     */
    @Override
    public void atacar() {

    }

}

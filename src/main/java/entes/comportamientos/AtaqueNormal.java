package entes.comportamientos;

import control.Teclado;
import entes.criaturas.Jugador;

public class AtaqueNormal implements AttackBehavior {
    // Teclado
    private Teclado teclado;
    // Jugador
    private Jugador jugador;

    public AtaqueNormal(Teclado teclado, Jugador jugador) {
        this.teclado = teclado;
        this.jugador = jugador;
    }

    /**
     * Método atacar de AtaqueNormal
     * Un ataque normal consume estamina y tiene un cooldown normal
     */
    @Override
    public void atacar() {
        if(teclado.ataque && (jugador.getResistencia() > 0)) {
            jugador.disminuirResistencia();
            jugador.notificar();
        }
    }

}

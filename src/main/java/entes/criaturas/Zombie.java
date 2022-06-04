package entes.criaturas;

import graficos.HojaSprites;
import graficos.Pantalla;
import graficos.Sprite;

public class Zombie extends Enemigo {
    // Hoja de sprites de Zombies
    private static HojaSprites hojaZombie;

    /**
     * Constructor de la clase Zombie
     * @param idEnemigo ID del enemigo
     * @param nombre Nombre del enemigo
     * @param vidaMax Vida máxima del enemigo
     */
    public Zombie(int idEnemigo, String nombre, int vidaMax) {
        super(idEnemigo, nombre, vidaMax);

        // Sprite inicial del enemigo
        this.sprite = Sprite.ABAJO_E0;

        // Posición inicial del enemigo
        posicionX = 992;
        posicionY = 140;
    }

    @Override
    public void mostrar(Pantalla pantalla) {
        pantalla.mostrarEnemigo(x,y);
    }
}

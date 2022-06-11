package entes.criaturas;

import graficos.HojaSprites;
import graficos.Pantalla;
import graficos.Sprite;

public class Viale extends Enemigo {
    // Hoja de sprites de Zombies
    private static HojaSprites hojaViale;

    /**
     * Constructor de la clase Zombie
     * @param idEnemigo ID del enemigo
     * @param nombre Nombre del enemigo
     * @param vidaMax Vida máxima del enemigo
     */
    public Viale(int idEnemigo, String nombre, int vidaMax) {
        super(idEnemigo, nombre, vidaMax);

        // Sprite inicial del enemigo
        this.sprite = Sprite.ABAJO_E0;

        // Posición inicial del enemigo
        this.posicionX = 992;
        this.posicionY = 120;
    }

    @Override
    public void dibujar(Pantalla pantalla) {
        pantalla.mostrarEnemigo(this.posicionX,this.posicionY, this);
    }

    public void actualizar(){

    }
}

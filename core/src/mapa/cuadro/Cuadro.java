package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

/**
 * Clase Cuadro. Representa los cuadros (o tiles) que conforman al mapa.
 */
public abstract class Cuadro {
    // Posición x del cuadro
    private int x;
    // Posición y del cuadro
    private int y;
    // Sprite del cuadro
    public Sprite sprite;

    // Colección de Cuadros
    //TODO CAMBIAR
    public static final Cuadro ASFALTO = new CuadroAsfalto(Sprite.ejPj1);
    // Fin de la colección

    /**
     * Constructor del Cuadro
     * @param sprite Sprite del cuadro.
     */
    public Cuadro(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Método mostrar. Un cuadro se dibuja a sí mismo.
     * @param x posición x del cuadro
     * @param y posición y del cuadro
     * @param pantalla pantalla donde se mostrará el cuadro
     */
    public void mostrar(int x, int y, Pantalla pantalla) { }

    /**
     * Método sólido. Un cuadro sólido no puede atravesarse.
     * Por defecto, un cuadro no es sólido.
     * @return True si es sólido, false en caso contrario.
     */
    public boolean solido() {
        return false;
    }

}

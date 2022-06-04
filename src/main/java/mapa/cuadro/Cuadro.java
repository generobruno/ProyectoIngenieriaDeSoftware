package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

/**
 * Clase Cuadro. Representa los cuadros (o tiles) que conforman al mapa.
 */
public class Cuadro {
    // Posición x del cuadro
    private int x;
    // Posición y del cuadro
    private int y;
    // Sprite del cuadro
    public Sprite sprite;
    // Boolean para ver si el cuadro es sólido
    private boolean solido;

    // Lado de cada cuadro
    public static final int LADO = 64;

    // Colección de Cuadros
    public static final Cuadro VACIO = new Cuadro(Sprite.VACIO,false);
    public static final Cuadro PISO = new Cuadro(Sprite.PISO,false);
    public static final Cuadro ESQUINA1A = new Cuadro(Sprite.ESQUINA1A);
    public static final Cuadro ESQUINA1B = new Cuadro(Sprite.ESQUINA1B);
    public static final Cuadro ESQUINA1ARX = new Cuadro(Sprite.ESQUINA1ARX);
    public static final Cuadro ESQUINA1BRX = new Cuadro(Sprite.ESQUINA1BRX);
    public static final Cuadro PARED1 = new Cuadro(Sprite.PARED1);
    public static final Cuadro PARED2 = new Cuadro(Sprite.PARED2);
    public static final Cuadro ESQ_INF_IZQ = new Cuadro(Sprite.ESQ_INF_IZQ);
    public static final Cuadro ESQ_SUP_IZQ = new Cuadro(Sprite.ESQ_SUP_IZQ);
    public static final Cuadro ESQ_INF_DER  = new Cuadro(Sprite.ESQ_INF_DER);
    public static final Cuadro ESQ_SUP_DER = new Cuadro(Sprite.ESQ_SUP_DER);
    public static final Cuadro LADO_DER = new Cuadro(Sprite.LADO_DER);
    public static final Cuadro LADO_IZQ = new Cuadro(Sprite.LADO_IZQ);
    public static final Cuadro LADO_HORIZ = new Cuadro(Sprite.LADO_HORIZ);

    public static final Cuadro ENEMIGO = new Cuadro(Sprite.ABAJO_E0);

    // Fin de la colección

    /**
     * Constructor del Cuadro
     * Por defecto, el cuadro no es sólido
     * @param sprite Sprite del cuadro.
     */
    public Cuadro(Sprite sprite) {
        this.sprite = sprite;
        solido = true;
    }

    /**
     * Constructor del Cuadro (Sobrecargado)
     * En este caso se le puede definir si es sólido o no.
     * @param sprite Sprite del cuadro.
     * @param solido Si es sólido o no
     */
    public Cuadro(Sprite sprite, boolean solido) {
        this.sprite = sprite;
        this.solido = solido;
    }

    /**
     * Método mostrar. Un cuadro se dibuja a sí mismo.
     * @param x posición x del cuadro
     * @param y posición y del cuadro
     * @param pantalla pantalla donde se mostrará el cuadro
     */
    public void mostrar(int x, int y, Pantalla pantalla) {
        pantalla.mostrarCuadro(x << 5,y << 5,this);
    }

    /**
     * Método sólido. Un cuadro sólido no puede atravesarse.
     * @return True si es sólido, false en caso contrario.
     */
    public boolean isSolido() {
        return this.solido;
    }


}

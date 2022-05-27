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
    // Lado de cada cuadro TODO REVISAR
    public static final int LADO = 32;

    // Colección de Cuadros
    public static final Cuadro VACIO = new Cuadro(Sprite.VACIO);

    public static final Cuadro PISO = new Cuadro(Sprite.PISO);
    public static final Cuadro UNION1 = new Cuadro(Sprite.UNION1);
    public static final Cuadro UNION2 = new Cuadro(Sprite.UNION2);
    public static final Cuadro UNION3 = new Cuadro(Sprite.UNION3);
    public static final Cuadro UNION4= new Cuadro(Sprite.UNION4);
    public static final Cuadro UNION5 = new Cuadro(Sprite.UNION5);
    public static final Cuadro UNION6 = new Cuadro(Sprite.UNION6);
    public static final Cuadro ESQ_INF_IZQ = new Cuadro(Sprite.ESQ_INF_IZQ);
    public static final Cuadro ESQ_SUP_IZQ = new Cuadro(Sprite.ESQ_SUP_IZQ);
    public static final Cuadro LADO_DER = new Cuadro(Sprite.LADO_DER);
    public static final Cuadro LADO_HORIZ = new Cuadro(Sprite.LADO_HORIZ);
    public static final Cuadro ESQ_SUP_DER = new Cuadro(Sprite.ESQ_SUP_DER);
    public static final Cuadro LADO_IZQ = new Cuadro(Sprite.LADO_IZQ);
    public static final Cuadro ESQ_INF_DER  = new Cuadro(Sprite.ESQ_INF_DER);
    public static final Cuadro NEGRO  = new Cuadro(Sprite.NEGRO);
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
    public void mostrar(int x, int y, Pantalla pantalla) {
        pantalla.mostrarCuadro(x << 5,y << 5,this);
    }

    /**
     * Método sólido. Un cuadro sólido no puede atravesarse.
     * Por defecto, un cuadro no es sólido.
     * @return True si es sólido, false en caso contrario.
     */
    public boolean solido() {
        return false;
    }

}

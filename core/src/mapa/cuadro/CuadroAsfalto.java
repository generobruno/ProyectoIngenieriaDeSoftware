package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

/**
 * Clase de prueba para un tile
 */
public class CuadroAsfalto extends Cuadro{

    public CuadroAsfalto(Sprite sprite) {
        super(sprite);
    }

    public void mostrar(int x, int y, Pantalla pantalla) {
        pantalla.mostrarCuadro(x,y,this);
    }
}

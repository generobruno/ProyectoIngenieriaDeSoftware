package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Clase Teclado:
 * Se encarga de configurar las teclas que se utilizarán para jugar
 * y de actualizar el estado del juego cuando se presiona una de
 * estas teclas.
 * NOTA: Los atributos de movimiento son public para agilizar la respuesta al
 * pulsar una tecla.
 */
public final class Teclado implements KeyListener {

    private final static int numeroTeclas = 120;
    private final boolean[] teclas = new boolean[numeroTeclas];

    // Colección de controles
    public boolean arriba;
    public boolean abajo;
    public boolean izquierda;
    public boolean derecha;

    public boolean correr;
    public boolean ataque;

    public boolean salir;

    /**
     * Método actualizar:
     * Actualiza el estado de una tecla en el array
     * de teclas.
     */
    public void actualizar(){
        arriba = teclas[KeyEvent.VK_W];
        abajo = teclas[KeyEvent.VK_S];
        izquierda = teclas[KeyEvent.VK_A];
        derecha = teclas[KeyEvent.VK_D];

        correr = teclas[KeyEvent.VK_SHIFT];
        ataque = teclas[KeyEvent.VK_L];

        salir = teclas[KeyEvent.VK_ESCAPE];
    }

    // Reconoce el pulsado constante de una tecla
    @Override
    public void keyPressed(KeyEvent e){
        teclas[e.getKeyCode()] = true;
    }

    // Reconoce que se dejó de pulsar una tecla
    @Override
    public void keyReleased(KeyEvent e) {
        teclas[e.getKeyCode()] = false;
    }

    // Reconoce el pulsado de una tecla
    @Override
    public void keyTyped(KeyEvent e) {  }

    /**
     * Método para probar el teclado
     * @param n tecla a testear.
     */
    public void testTeclado (int n){
        switch (n){
            case 1:
                arriba = true;
                break;
            case 2:
                abajo = true;
                break;
            case 3:
                izquierda = true;
                break;
            case 4:
                derecha = true;
                break;
            case 5:
                correr = true;
                break;
            case 6:
                ataque = true;
                break;
            case 7:
                arriba = false;
                break;
            case 8:
                abajo = false;
                break;
            case 9:
                izquierda = false;
                break;
            case 10:
                derecha = false;
                break;
            case 11:
                correr = false;
                break;
            case 12:
                ataque = false;
                break;
        }
    }

}

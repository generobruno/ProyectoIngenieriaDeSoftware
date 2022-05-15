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

    public boolean arriba;
    public boolean abajo;
    public boolean izquierda;
    public boolean derecha;

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
}

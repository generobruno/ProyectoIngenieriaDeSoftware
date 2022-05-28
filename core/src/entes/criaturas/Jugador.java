package entes.criaturas;

import control.Teclado;
import graficos.Pantalla;
import graficos.Sprite;

public class Jugador extends Criatura {
    // Teclado con el que se mueve al jugador
    private Teclado teclado;
    // Animación del jugador
    private int animacion;

    /**
     * Constructor de la clase
     * Crea al jugador
     * @param teclado Teclado con el que se mueve al jugador
     */
    public Jugador(Teclado teclado, Sprite sprite) {
        this.teclado = teclado;
        this.sprite = sprite;
    }

    /**
     * Constructor de la clase
     * @param teclado Teclado con el que se mueve al jugador
     * @param posicionX Posición X del mapa donde aparece el jugador
     * @param posicionY Posición Y del mapa donde aparece el jugador
     */
    public Jugador(Teclado teclado, int posicionX, int posicionY, Sprite sprite) {
        this.teclado = teclado;
        this.sprite = sprite;
        this.x = posicionX;
        this.y = posicionY;
    }

    /**
     * Método actualizar
     * Se encarga de actualizar los dibujos del jugador en la pantalla,
     * dependiendo de lo que este esté haciendo.
     */
    public void actualizar() {

        int desplazamientoX = 0;
        int desplazamientoY = 0;

        int velocidadMovimiento = 4;

        // Aumenta hasta el número máximo que puede tener un int
        if(animacion < 32767) {
            animacion++;
        } else {
            animacion = 0;
        }

        // Correr
        if(teclado.correr) {
            velocidadMovimiento = 8;
        }

        if(teclado.arriba) {
            desplazamientoY -= velocidadMovimiento;
        }
        if(teclado.abajo) {
            desplazamientoY += velocidadMovimiento;
        }
        if(teclado.derecha) {
            desplazamientoX += velocidadMovimiento;
        }
        if(teclado.izquierda) {
            desplazamientoX -= velocidadMovimiento;
        }

        // El jugador solo se mueve si se presionó una tecla de movimiento
        if(desplazamientoX != 0 || desplazamientoY != 0) {
            // Se mueve el jugador
            mover(desplazamientoX, desplazamientoY);
            enMovimiento = true;
        } else {
            enMovimiento = false;
        }

        // Animaciones
        if(direccion == 'n') {
            sprite = Sprite.ARRIBA0;
            if(enMovimiento) {
                if(animacion % 50 > 25) {
                    sprite = Sprite.ARRIBA1;
                } else {
                    sprite = Sprite.ARRIBA2;
                }
            }
        }
        if(direccion == 's') {
            sprite = Sprite.ABAJO0;
            if(enMovimiento) {
                if(animacion % 50 > 25) {
                    sprite = Sprite.ABAJO1;
                } else {
                    sprite = Sprite.ABAJO2;
                }
            }
        }
        if(direccion == 'o') {
            sprite = Sprite.IZQUIERDA0;
            if(enMovimiento) {
                if(animacion % 50 > 25) {
                    sprite = Sprite.IZQUIERDA1;
                } else {
                    sprite = Sprite.IZQUIERDA2;
                }
            }
        }
        if(direccion == 'e') {
            sprite = Sprite.DERECHA0;
            if(enMovimiento) {
                if(animacion % 50 > 25) {
                    sprite = Sprite.DERECHA1;
                } else {
                    sprite = Sprite.DERECHA2;
                }
            }
        }

    }

    @Override
    public void mostrar(Pantalla pantalla) {
        pantalla.mostrarJugador(x,y,this);
    }
}

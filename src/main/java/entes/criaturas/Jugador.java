package entes.criaturas;

import control.Teclado;
import entes.comportamientos.AtaqueBerserk;
import entes.comportamientos.AtaqueNormal;
import graficos.Pantalla;
import graficos.Sprite;
import graficos.observer.Observer;
import mapa.Mapa;

import java.util.ArrayList;
import java.util.List;

public class Jugador extends Criatura implements Subject {
    // Teclado con el que se mueve al jugador
    private Teclado teclado;
    // Animación del jugador
    private int animacion;
    private int SALUD_CRITICA = 50;

    // Estamina de Jugador
    private int resistencia = 600;
    private int recuperacion = 0;
    private int RECUPERACION_MAX = 200; // Cool-down de la recuperación
    private boolean recuperado = true;

    // Experiencia del Jugador
    private int nivel = 1;
    private int exp = 0;



    /**
     * Constructor de la clase
     * Crea al jugador
     * @param teclado Teclado con el que se mueve al jugador
     */
    public Jugador(Mapa mapa, Teclado teclado, Sprite sprite) {
        this.mapa = mapa;
        this.teclado = teclado;
        this.sprite = sprite;

        // Salud del Jugador
        this.vidaMax = 1000;
        this.salud = 1000;

        // Comportamientos
        attackBehavior = new AtaqueNormal();
    }

    /**
     * Constructor de la clase
     * @param teclado Teclado con el que se mueve al jugador
     * @param posicionX Posición X del mapa donde aparece el jugador
     * @param posicionY Posición Y del mapa donde aparece el jugador
     */
    public Jugador(Mapa mapa, Teclado teclado, int posicionX, int posicionY, Sprite sprite) {
        this(mapa, teclado, sprite);
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
        // El jugador tiene estamina
        if(teclado.correr && (resistencia > 0)) {
            velocidadMovimiento = 8;
            recuperado = false;
            recuperacion = 0;
        } else { // El jugador no tiene estamina
            velocidadMovimiento = 4;
            if(!recuperado && (recuperacion < RECUPERACION_MAX)) {
                recuperacion++;
                notificar();
            }
            if((recuperacion == RECUPERACION_MAX) && (resistencia < 600)) {
                resistencia++;
                notificar();
            }
        }

        // Comportamiento de ataque
        if(salud <= SALUD_CRITICA) {
            // Si la salud actual es menor a la crítica
            // el jugador entra en modo Berserk
            this.setAttackBehavior(new AtaqueBerserk());
        } else {
            this.setAttackBehavior(new AtaqueNormal());
        }

        // Ataque
        if(teclado.ataque && (resistencia > 0)) {
            attackBehavior.atacar();
            recuperado = false;
            recuperacion = 0;
        } else {
            if(!recuperado && (recuperacion < RECUPERACION_MAX)) {
                recuperacion++;
                notificar();
            }
            if((recuperacion == RECUPERACION_MAX) && (resistencia < 600)) {
                resistencia++;
                notificar();
            }
        }

        if(teclado.arriba) {
            desplazamientoY -= velocidadMovimiento;
            if(teclado.correr && (resistencia > 0)) {
                resistencia--;
                notificar();
            }
        }
        if(teclado.abajo) {
            desplazamientoY += velocidadMovimiento;
            if(teclado.correr && (resistencia > 0)) {
                resistencia--;
                notificar();
            }
        }
        if(teclado.derecha) {
            desplazamientoX += velocidadMovimiento;
            if(teclado.correr && (resistencia > 0)) {
                resistencia--;
                notificar();
            }
        }
        if(teclado.izquierda) {
            desplazamientoX -= velocidadMovimiento;
            if(teclado.correr && (resistencia > 0)) {
                resistencia--;
                notificar();
            }
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

    /**
     * Método que muestra el jugador por pantalla
     * @param pantalla Pantalla donde se muestra el jugador
     */
    @Override
    public void dibujar(Pantalla pantalla) {
        pantalla.mostrarJugador(x,y,this);
    }

    /**
     * Getter de la resistencia
     * @return Resistencia del jugador
     */
    public int getResistencia(){
        return resistencia;
    }

    /**
     * Getter de la vida máxima
     * @return Vida máxima del jugador
     */
    public int getVidaMax() {
        return this.vidaMax;
    }

    /**
     * Getter de la salud
     * @return Salud del jugador
     */
    public int getSalud() {
        return this.salud;
    }

    /**
     * Getter de la experiencia
     * @return Experiencia del jugador
     */
    public int getExp() {
        return this.exp;
    }

    /**
     * Getter del nivel
     * @return Nivel del jugador
     */
    public int getNivel() {
        return this.nivel;
    }
    // Implementación del Patron Observer - Sujeto Concreto
    @Override
    public void agregarObs(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void quitarObs(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notificar(){
        for (Observer observer:observers) {
            observer.update();
        }
    }
}

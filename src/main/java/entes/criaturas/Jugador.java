package entes.criaturas;

import control.Teclado;
import entes.comportamientos.AtaqueBerserk;
import entes.comportamientos.AtaqueNormal;
import graficos.Pantalla;
import graficos.Sprite;
import graficos.observer.Observer;
import mapa.Mapa;

public class Jugador extends Criatura implements Subject {
    // Teclado con el que se mueve al jugador
    private Teclado teclado;
    // Animación del jugador
    private int animacion;
    private int SALUD_CRITICA = 100;

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

        // Comportamiento inicial
        attackBehavior = new AtaqueNormal(teclado,this);
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

        // Código de muestra para disminuir salud
        if(salud > SALUD_CRITICA){
            salud--;
            notificar();
        }
        // Fin del código de muestra

        // Movimiento del jugador
        gestionarMovimiento(desplazamientoX,desplazamientoY);
        // Comportamiento de Ataque
        gestionarComportamiento();
        // Ataque
        gestionarAtaque();
        // Animaciones
        gestionarAnimacion();
    }

    /**
     * Método gestionarAtaque.
     * Utilizado para que el jugador realice un ataque si se presiona la tecla
     * correspondiente y tiene estamina disponible.
     */
    public void gestionarAtaque() {
        if(teclado.ataque && (resistencia > 0) && !moving()) {
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
    }

    /**
     * Método gestionarComportamiento.
     * Se encarga de cambiar el comportamiento de ataque del jugador
     * dependiendo de la salud que este tenga.
     */
    public void gestionarComportamiento() {
        if(salud <= SALUD_CRITICA) {
            // Si la salud actual es menor a la crítica
            // el jugador entra en modo Berserk
            this.setAttackBehavior(new AtaqueBerserk(teclado,this));
        } else {
            this.setAttackBehavior(new AtaqueNormal(teclado,this));
        }
    }

    /**
     * Método gestionarMovimiento.
     * Hace que el jugador se mueve por el mapa dependiendo de las teclas que se estén
     * presionando. El jugador también puede correr si se presiona la tecla determinada
     * y tiene la estamina suficiente.
     * @param desplazamientoX Desplazamiento del jugador por el mapa en el eje x
     * @param desplazamientoY Desplazamiento del jugador por el mapa en el eje y
     */
    public void gestionarMovimiento(int desplazamientoX, int desplazamientoY) {
        // Velocidad de movimiento inicial
        int velocidadMovimiento = 4;

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
            if((recuperacion == RECUPERACION_MAX) && (resistencia < 600)) { // Cool-down de recuperación
                resistencia++;
                notificar();
            }
        }

        // Movimiento dependiendo de la tecla
        if(teclado.arriba) {
            desplazamientoY -= velocidadMovimiento;
            gestionarEstamina();
        }
        if(teclado.abajo) {
            desplazamientoY += velocidadMovimiento;
            gestionarEstamina();
        }
        if(teclado.derecha) {
            desplazamientoX += velocidadMovimiento;
            gestionarEstamina();
        }
        if(teclado.izquierda) {
            desplazamientoX -= velocidadMovimiento;
            gestionarEstamina();
        }

        // El jugador solo se mueve si se presionó una tecla de movimiento
        if(desplazamientoX != 0 || desplazamientoY != 0) {
            // Se mueve el jugador
            mover(desplazamientoX, desplazamientoY);
            enMovimiento = true;
        } else {
            enMovimiento = false;
        }
    }

    /**
     * Método gestionarEstamina.
     * Se encarga de disminuir la estamina del jugador, si es que
     * este tiene estamina disponible.
     */
    public void gestionarEstamina() {
        if(teclado.correr && (resistencia > 0)) {
            resistencia--;
            notificar();
        }
    }

    /**
     * Método gestionarAnimacion.
     * Cambia el sprite del jugador dependiendo de cómo se está
     * moviendo.
     */
    public void gestionarAnimacion() {
        // Aumenta hasta el número máximo que puede tener un int
        if(animacion < 32767) {
            animacion++;
        } else {
            animacion = 0;
        }

        // Movimiento hacia arriba
        if(direccion == 'n') {
            if(salud <= SALUD_CRITICA){
                sprite = Sprite.ARRIBA0_B;
            } else {
                sprite = Sprite.ARRIBA0;
            }
            if(teclado.ataque){
                if(salud <= SALUD_CRITICA) {
                    sprite = Sprite.ARRIBAGOLPE_B;
                }else{
                    sprite = Sprite.ARRIBAGOLPE;
                }
            }
            if(enMovimiento) {
                if(animacion % 50 > 25) {
                    if(salud <= SALUD_CRITICA) {
                        sprite = Sprite.ARRIBA1_B;
                    } else {
                        sprite = Sprite.ARRIBA1;
                    }
                } else {
                    if(salud <= SALUD_CRITICA) {
                        sprite = Sprite.ARRIBA2_B;
                    } else {
                        sprite = Sprite.ARRIBA2;
                    }
                }
            }
        }

        // Movimiento hacia abajo
        if(direccion == 's') {
            if(salud <= SALUD_CRITICA){
                sprite = Sprite.ABAJO0_B;
            } else {
                sprite = Sprite.ABAJO0;
            }
            if(teclado.ataque){
                if(salud <= SALUD_CRITICA) {
                    sprite = Sprite.ABAJOGOLPE_B;
                }else{
                    sprite = Sprite.ABAJOGOLPE;
                }
            }
            if(enMovimiento) {
                if(animacion % 50 > 25) {
                    if(salud <= SALUD_CRITICA) {
                        sprite = Sprite.ABAJO1_B;
                    } else {
                        sprite = Sprite.ABAJO1;
                    }
                } else {
                    if(salud <= SALUD_CRITICA) {
                        sprite = Sprite.ABAJO2_B;
                    } else {
                        sprite = Sprite.ABAJO2;
                    }
                }
            }
        }

        // Movimiento hacia la izquierda
        if(direccion == 'o') {
            if(salud <= SALUD_CRITICA){
                sprite = Sprite.IZQUIERDA0_B;
            } else {
                sprite = Sprite.IZQUIERDA0;
            }
            if(teclado.ataque){
                if(salud <= SALUD_CRITICA) {
                    sprite = Sprite.IZQUIERDAGOLPE_B;
                }else{
                    sprite = Sprite.IZQUIERDAGOLPE;
                }
            }
            if(enMovimiento) {
                if(animacion % 50 > 25) {
                    if(salud <= SALUD_CRITICA) {
                        sprite = Sprite.IZQUIERDA1_B;
                    } else {
                        sprite = Sprite.IZQUIERDA1;
                    }
                } else {
                    if(salud <= SALUD_CRITICA) {
                        sprite = Sprite.IZQUIERDA2_B;
                    } else {
                        sprite = Sprite.IZQUIERDA2;
                    }
                }
            }
        }

        // Movimiento hacia la derecha
        if(direccion == 'e') {
            if(salud <= SALUD_CRITICA){
                sprite = Sprite.DERECHA0_B;
            } else {
                sprite = Sprite.DERECHA0;
            }
            if(teclado.ataque){
                if(salud <= SALUD_CRITICA) {
                    sprite = Sprite.DERECHAGOLPE_B;
                }else{
                    sprite = Sprite.DERECHAGOLPE;
                }
            }
            if(enMovimiento) {
                if(animacion % 50 > 25) {
                    if(salud <= SALUD_CRITICA) {
                        //if(teclado.ataque) sprite = Sprite.DERECHAGOLPE_B;
                        sprite = Sprite.DERECHA1_B;
                    } else {
                        sprite = Sprite.DERECHA1;
                    }
                } else {
                    if(salud <= SALUD_CRITICA) {
                        //if(teclado.ataque) sprite = Sprite.DERECHAGOLPE;
                        sprite = Sprite.DERECHA2_B;
                    } else {
                        sprite = Sprite.DERECHA2;
                    }
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

    /**
     * Método disminuirResistencia.
     * Disminuye la resistencia del jugador.
     */
    public void disminuirResistencia() {
        resistencia -= 5;
    }

    public int getRecuperacion(){
        return this.recuperacion;
    }

    public boolean getRecuperado() {
        return this.recuperado;
    }
    public boolean moving() {
        if(teclado.arriba || teclado.abajo || teclado.derecha || teclado.izquierda){
            return true;
        }
        return false;
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

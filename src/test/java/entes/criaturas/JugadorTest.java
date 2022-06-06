package entes.criaturas;

import control.Teclado;
import graficos.Sprite;
import junit.framework.TestCase;
import mapa.Mapa;
import mapa.MapaGenerado;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JugadorTest {
    Jugador jugador;
    Teclado teclado;
    MapaGenerado mapa;

    @BeforeClass
    void escenario() {
        teclado = new Teclado();
        mapa = new MapaGenerado(320, 320);
        jugador = new Jugador(mapa, teclado, 0, 0, Sprite.VACIO);
    }

    @Test
    void TestMovArriba() {
        escenario();
        teclado.testTeclado(1);
        jugador.actualizar();
        assertTrue(jugador.getPosicionY() == -4);
    }

    @Test
    void TestMovAbajo() {
        escenario();
        teclado.testTeclado(2);
        jugador.actualizar();
        assertTrue(jugador.getPosicionY() == 4);
    }

    @Test
    void TestMovIzquierda() {
        escenario();
        teclado.testTeclado(3);
        jugador.actualizar();
        assertTrue(jugador.getPosicionX() == -4);
    }

    @Test
    void TestMovDerecha() {
        escenario();
        teclado.testTeclado(4);
        jugador.actualizar();
        assertTrue(jugador.getPosicionX() == 4);
    }

    @Test
    void TestMovCorrer() {
        escenario();
        teclado.testTeclado(1);
        teclado.testTeclado(5);
        jugador.actualizar();
        assertTrue(jugador.getPosicionY() == -8);
    }

//    @Test
//    void TestMovAtaque() {
//        escenario();
//        teclado.testTeclado(1);
//        jugador.actualizar();
//        assertTrue(jugador.getPosicionY() == -4);
//    }
}
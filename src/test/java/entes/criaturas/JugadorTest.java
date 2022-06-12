package entes.criaturas;

import control.Teclado;
import graficos.Sprite;
import graficos.observer.Hud;
import mapa.MapaGenerado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JugadorTest {
    Jugador jugador;
    Teclado teclado;
    MapaGenerado mapa;

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

    @Test
    void TestEstamina() {
        escenario();
        teclado.testTeclado(1);
        teclado.testTeclado(5);
        jugador.actualizar();
        assertTrue(jugador.getResistencia() == 599);

    }

    @Test
    void TestSinEstamina() {
        escenario();
        teclado.testTeclado(1);
        teclado.testTeclado(5);
        for(int i = 1; i <= 600; i++){
            jugador.actualizar();
        }
        assertTrue(jugador.getResistencia() == 0);
    }

    @Test
    void TestAgregarObs(){
        escenario();
        Hud h = new Hud(jugador);
        jugador.agregarObs(h);
        assertEquals(jugador.observers.contains(h),true);

    }

    @Test
    void TestQuitarObs(){
        escenario();
        Hud h = new Hud(jugador);
        jugador.agregarObs(h);
        jugador.quitarObs(h);
        assertEquals(jugador.observers.contains(h),false);

    }

    @Test
    void TestAtaque() {
        escenario();
        teclado.testTeclado(6);
        jugador.actualizar();
        assertTrue(jugador.getResistencia() == 595);
    }

    @Test
    void TestNoAtacar() {
        escenario();
        teclado.testTeclado(1);
        teclado.testTeclado(6);
        jugador.actualizar();
        assertTrue(jugador.getResistencia() == 600);
    }

    @Test
    void TestResistenciaMax() {
        escenario();
        assertTrue(jugador.getResistencia() == 600);
    }

    @Test
    void TestRecuperacionResistencia() {
        escenario();
        teclado.testTeclado(1);
        teclado.testTeclado(5);
        for(int i = 1; i <= 600; i++){
            jugador.actualizar();
        }
        teclado.testTeclado(6);
        teclado.testTeclado(11);
        for(int i = 1; i <= 60; i++){
            jugador.actualizar();
        }
        assertTrue(jugador.getRecuperacion() == 121);
    }

    @Test
    void TestLimiteDeResistencia() {
        escenario();
        teclado.testTeclado(1);
        teclado.testTeclado(5);
        for(int i = 1; i <= 600; i++){
            jugador.actualizar();
        }
        teclado.testTeclado(7);
        teclado.testTeclado(11);
        for(int i = 1; i <= 600; i++){
            jugador.actualizar();
        }
        assertTrue(jugador.getResistencia() == 600);
    }

    @Test
    void TestSinResistenciaAtaque(){
        escenario();
        teclado.testTeclado(1);
        teclado.testTeclado(5);
        for(int i = 1; i <= 600; i++){
            jugador.actualizar();
        }
        teclado.testTeclado(7);
        teclado.testTeclado(11);
        teclado.testTeclado(6);
        jugador.actualizar();
        assertTrue(jugador.getRecuperado() == false);

    }

//    @Test
//    void TestNotificar(){
//        escenario();
//        Hud h = new Hud(jugador);
//        jugador.agregarObs(h);
//        assertEquals(jugador.getExp(),h.getExp());
//        assertEquals(jugador.getNivel(),h.getNivel());
//        assertEquals(jugador.getResistencia(),h.getEstamina());
//        assertEquals(jugador.getSalud(),h.getSalud());
//
//        teclado.arriba = true;
//        teclado.correr = true;
//        try{
//            TimeUnit.MILLISECONDS.sleep(600);
//        }catch(InterruptedException e){
//            e.printStackTrace();
//        }
//        teclado.correr = false;
//        teclado.arriba = false;
//
//        assertEquals(jugador.getResistencia(),h.getEstamina());
//
//        jugador.quitarObs(h);
//        try{
//            TimeUnit.MILLISECONDS.sleep(600);
//        }catch(InterruptedException e){
//            e.printStackTrace();
//        }
//
//        assertNotEquals(jugador.getResistencia(),h.getEstamina());
//
//
//    }
}
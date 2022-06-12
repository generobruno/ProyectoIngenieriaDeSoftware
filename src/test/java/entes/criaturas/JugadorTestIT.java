package entes.criaturas;

import control.Teclado;
import graficos.Sprite;
import graficos.observer.Hud;
import mapa.MapaGenerado;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JugadorTestIT {
    JugadorTest jugadorTest;
    Jugador jugador;
    Teclado teclado;
    MapaGenerado mapa;

    void escenario() {
         teclado = new Teclado();
         mapa = new MapaGenerado(320, 320);
         jugador = new Jugador(mapa, teclado, 0, 0, Sprite.VACIO);
    }
    @Test
    void IntegrationTestJugador (){
        escenario();
        teclado.testTeclado(1);
        jugador.actualizar();
        assertTrue(jugador.getPosicionY() == -4);

        escenario();
        teclado.testTeclado(2);
        jugador.actualizar();
        assertTrue(jugador.getPosicionY() == 4);

        escenario();
        teclado.testTeclado(3);
        jugador.actualizar();
        assertTrue(jugador.getPosicionX() == -4);

        escenario();
        teclado.testTeclado(4);
        jugador.actualizar();
        assertTrue(jugador.getPosicionX() == 4);

        escenario();
        teclado.testTeclado(1);
        teclado.testTeclado(5);
        jugador.actualizar();
        assertTrue(jugador.getPosicionY() == -8);

        escenario();
        teclado.testTeclado(1);
        teclado.testTeclado(5);
        jugador.actualizar();
        assertTrue(jugador.getResistencia() == 599);

        escenario();
        teclado.testTeclado(1);
        teclado.testTeclado(5);
        for(int i = 0; i <= 599; i++){
            jugador.actualizar();
        }
        assertTrue(jugador.getResistencia() == 0);

        escenario();
        Hud h = new Hud(jugador);
        jugador.agregarObs(h);
        assertEquals(jugador.observers.contains(h),true);

        escenario();
        Hud h1 = new Hud(jugador);
        jugador.agregarObs(h1);
        jugador.quitarObs(h1);
        assertEquals(jugador.observers.contains(h1),false);

        escenario();
        teclado.testTeclado(6);
        jugador.actualizar();
        assertTrue(jugador.getResistencia() == 595);

        escenario();
        teclado.testTeclado(1);
        teclado.testTeclado(6);
        jugador.actualizar();
        assertTrue(jugador.getResistencia() == 600);

        escenario();
        assertTrue(jugador.getResistencia() == 600);

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
}

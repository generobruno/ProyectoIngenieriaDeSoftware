package graficos.observer;

import control.Teclado;
import entes.criaturas.Jugador;
import graficos.Sprite;
import juego.Juego;
import mapa.Mapa;
import mapa.MapaCargado;
import mapa.MapaGenerado;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class HudTest {
    Jugador j;
    Teclado teclado;
    MapaGenerado mapa;
    void escenario() {
        teclado = new Teclado();
        mapa = new MapaGenerado(320, 320);
        j = new Jugador(mapa, teclado, 0, 0, Sprite.VACIO);
    }
    @Test
    void update() {
        // Creamos los Objetos necesarios para instanciar a un jugador
        escenario();
        // Creamos el Hud
        Hud hud = new Hud(j);

        // Verificamos que el Hud se haya inicializado correctamente
        assertEquals(j.getExp(),hud.getExp());
        assertEquals(j.getNivel(),hud.getNivel());
        assertEquals(j.getResistencia(),hud.getEstamina());
        assertEquals(j.getSalud(),hud.getSalud());

        // Agregamos al Hud como Observer de Jugador
        j.agregarObs(hud);

        // Movemos al jugador corriendo, reduciendo asi la estamina y haciendo que Jugador
        // notifique a Hud sobre los cambios en sus variables (llama a su funcion update)
        teclado.arriba = true;
        teclado.correr = true;
        try{
            TimeUnit.MILLISECONDS.sleep(83);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        teclado.correr = false;
        teclado.arriba = false;

        // Controlamos que la información de Hud haya sido actualizada
        assertEquals(j.getResistencia(),hud.getEstamina());

        // TODO agregar futuras pruebas para cuando el jugador interacciona con objetos / enemigos
    }
}
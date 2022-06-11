package entes.criaturas;

import control.Teclado;
import graficos.Sprite;
import graficos.observer.Hud;
import mapa.MapaGenerado;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JugadorTestIT {
    JugadorTest jugadorTest;
    @Test
    void IntegrationTestJugador (){
        jugadorTest = new JugadorTest();
        jugadorTest.TestNoAtacar();
        jugadorTest.TestMovAbajo();

    }
}

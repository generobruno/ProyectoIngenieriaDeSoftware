package control;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TecladoTest {

    //TODO Arreglar o ver esto
    @Test
    void actualizar() {
        try {
            Teclado teclado = new Teclado();
            Robot robot = new Robot();

            //robot.keyPress(KeyEvent.VK_W);
            teclado.actualizar();
            assertEquals(false,teclado.arriba);
            //robot.keyRelease(KeyEvent.VK_W);

        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

}
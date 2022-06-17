package graficos.observer;

import entes.criaturas.Jugador;

import javax.swing.*;
import java.awt.*;

public class Inventario implements Observer{

    // Variable que guarda la estamina actual del sujeto
    private int estamina;
    // Variable que guarda la vida actual del sujeto
    private int salud;
    // Variable que guarda la experiencia actual del sujeto
    private int  exp;
    // Variable que guarda el nivel actual del sujeto
    private int nivel;
    // Variable que guarda la posiciónX del jugador
    private int posX;
    // Variable que guarda la posiciónY del jugador
    private int posY;
    // Referencia al sujeto
    private Jugador j;

    /** Constructor de la clase Inventario
     *
     * @param j jugador a observar
     */
    public Inventario(Jugador j){
        this.j = j;
    }
    @Override
    public void update() {
        estamina = j.getResistencia();
        salud = j.getSalud();
        exp = j.getExp();
        nivel = j.getNivel();
        posX = j.getPosicionX();
        posY = j.getPosicionY();

    }

    /**
     * Método show
     * Muestra por pantalla estadísticas y el inventario
     * @param g recurso gráfico para mostrar el inventario
     * @param fps contador de "frames per second"
     * @param xventana posición x de la ventana JFrame
     * @param yventana  posición y de la ventana JFrame
     */
    public void show(Graphics g, String fps, int xventana, int yventana) {
        Font f = new Font("txt",2,2*yventana/100);
        Font title = new Font("title",3,3*yventana/100);
        int height = 30*yventana/100;
        int width = 50*xventana/100;
        int x = width/2;
        int y = height/2;
        g.setColor(new Color (0xB070A0));
        g.drawRect(x, y,width, height);
        g.drawRect(4*x, y,width, height);
        g.setColor(new Color (0xE8B0D8));
        g.fillRect(x+1,y+1,width-1,height-1);
        g.fillRect(4*x + 1,y+1,width-1,height-1);
        g.setColor(new Color(0xE2E07C));
        g.fillRect(x+5,y+5,width-10,height-10);
        g.fillRect(4*x + 5,y+5,width-10,height-10);
        g.setColor(new Color (0xFFB070A0));
        g.setFont(title);
        g.drawString("Player Stats",x+9*x/100,y+(height/10)+ 20);
        g.drawString( "Game Stats",x+10*x/100,y+(7*height/10)-10);
        g.drawString( "Inventario",4*x+10*x/100,y+(height/10)+20);
        g.setFont(f);
        g.setColor(new Color (0x669999));
        if(estamina <=200)
        {
            g.setColor(new Color(0x505068));
        }
        g.drawString("Resistencia: " + estamina,x+10*x/100,y+(3*height/10)-15);
        g.setColor(new Color (0x669999));
        if(salud<=300){
            g.setColor(Color.red);
        }
        g.drawString("Salud: " + salud,x+10*x/100,y+(4*height/10)-15);
        g.setColor(new Color (0x669999));
        g.drawString("Exp: " + exp,x+10*x/100,y+(5*height/10)-15);
        g.drawString("Nivel: " + nivel,x+10*x/100,y+(6*height/10)-15);
        g.drawString(fps,x+10*x/100,y+(8*height/10)-15);
        g.drawString("posX: " + posX,x+10*x/100,y+(9*height/10)-15);
        g.drawString("posY: " + posY,x+10*x/100,y+(10*height/10)-15);

    }


}

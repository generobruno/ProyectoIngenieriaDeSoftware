package graficos.observer;

import entes.criaturas.Jugador;

import java.awt.*;

public class Hud implements Observer, Display{
    // Variable que guarda la estamina actual del sujeto
    private int estamina;
    // Variable que guarda la vida actual del sujeto
    private int salud;
    // Variable que guarda la experiencia actual del sujeto
    private int  exp;
    // Variable que guarda el nivel actual del sujeto
    private int nivel;
    // Referencia al sujeto
    private Jugador player;

    /**
     * Constructor de la clase Hud
     * Referencia al sujeto a observar y guarda sus atributos
     * @param player jugador a referenciar
     */
    public Hud(Jugador player){
        this.player = player;
        update();
    }

    @Override
    public void update(){
        estamina = player.getResistencia();
        salud = player.getSalud();
        exp = player.getExp();
        nivel = player.getNivel();
    }

    @Override
    public void displayInfo(Graphics g){
        // Escribimos los atributos del sujeto por pantalla
        Font titulo = new Font("txt",3,25);
        g.setColor(new Color (0xFFB070A0));
        g.setFont(titulo);
        g.drawString("Player 1",15,35);
        Font f = new Font("txt",3,13);
        g.setFont(f);
        g.setColor(new Color (0xE2E07C));
        g.drawString("Resistencia ",10,55);
        g.drawString("Salud ",10,70);
        g.drawString("Exp " + exp,10,85);
        g.drawString("Nivel " + nivel,10,100);

        int est = 100 * estamina / 600;
        int vida = 100 * this.salud / 1000;
        Color rosadoOscuro = new Color (0xFFB070A0);
        Color rosadoClaro = new Color (0xFFE8B0D8);

        // Dibujamos la barra de estamina
        g.drawRect(90,44,101,12);
        g.fillRect(91,45,100,11);
        g.setColor(rosadoClaro);
        g.fillRect(91,45,est,3);
        g.setColor(rosadoOscuro);
        g.fillRect(91,48,est,7);

        // Dibujamos la barra de vida
        g.setColor(new Color (0xE2E07C));
        g.drawRect(90,59,101,12);
        g.fillRect(91,60,100,11);
        g.setColor(rosadoClaro);
        g.fillRect(91,60,vida,3);
        g.setColor(rosadoOscuro);
        g.fillRect(91,63,vida,7);



    }

    // Getters de las variables del Hud

    public int getEstamina() {
        return estamina;
    }

    public int getSalud() {
        return salud;
    }

    public int getExp() {
        return exp;
    }

    public int getNivel() {
        return nivel;
    }

    public Jugador getPlayer() {
        return player;
    }
}

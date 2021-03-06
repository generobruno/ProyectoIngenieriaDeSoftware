package juego;

import control.Teclado;
import entes.criaturas.Jugador;
import entes.criaturas.Viale;
import graficos.Pantalla;
import graficos.Sprite;
import graficos.observer.Hud;
import graficos.observer.Inventario;
import mapa.Mapa;
import mapa.MapaCargado;

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;


/**
 * Clase Juego (Ejecutable):
 * Esta clase utiliza las librerías de AWT y Swing para generar
 * una interfaz gráfica donde será ejecutado el juego. También ejecuta el thread en el
 * que se correrá el juego.
 */
public class Juego extends Canvas implements Runnable{

    // Sirve para identificar la clase en caso de reutilizarla en otras circunstancias
    //private static final long serialVersionUID = 1L;

    // Instancia del Juego
    private static Juego instancia;

    private static final int ANCHO = 1280;
    private static final int ALTO = 720;

    // Variable booleana que nos permite saber cuando el juego está en ejecución.
    // Al ser Volatile, la palabra no puede ejecutarse simultáneamente por 2 threads.
    private static volatile boolean  enFuncionamiento = false;

    // TODO: Cambiar nombre del juego
    private static final String NOMBRE = "ProyectoMenta";

    private static String CONTADOR_APS = "";
    private static String CONTADOR_FPS = "";

    // Actualizaciones por segundo
    private static int aps = 0;
    // Frames por segundo
    private static int fps = 0;

    private static JFrame ventana;
    private static Thread thread;
    private static Teclado teclado;
    private static Pantalla pantalla;

    // Mapa
    private static Mapa mapa;

    // Jugador
    private static Jugador jugador;

    // Enemigo Final
    private static Viale enemigofinal;

    // Imagen en buffer, en blanco
    private static BufferedImage imagen =
            new BufferedImage(ANCHO,ALTO,BufferedImage.TYPE_INT_RGB);
    // Array de int con los pixeles de la imagen
    private static int[] pixeles =
            ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();

    // HUD
    private static Hud hud;

    // Inventario
    private static Inventario inventario;

    // Estados del Juego
    private String  gameState;

    /**
     * Método Juego:
     * Constructor de la clase Juego:
     * Crea la interfaz gráfica
     */
    private Juego() {
        setPreferredSize(new Dimension(ANCHO, ALTO));

        // Pantalla
        pantalla = new Pantalla(ANCHO, ALTO);

        // Mapa
        mapa = new MapaCargado("/texturas/MapaPrincipal64px.png");

        // Teclado
        teclado = new Teclado();
        addKeyListener(teclado);

        // Creamos al jugador
        jugador = new Jugador(mapa, teclado,992,1900, Sprite.ABAJO0);
        // Creamos al enemigo final
        enemigofinal = new Viale(1,"Viale",800);
        //Inicializamos el estado del juego
        gameState = "run";

        // Creamos el HUD del jugador;
        hud = new Hud(jugador);
        // Creamos en Inventario
        inventario = new Inventario(jugador);
        // Agregamos el HUD como Observer de Jugador
        jugador.agregarObs(hud);
        // Creamos la ventana
        ventana = new JFrame(NOMBRE);
        //Para que se cierre la ventana al hacer clic en la cruz
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Para que el usuario no pueda cambiar el tamaño de la ventana
        ventana.setResizable(true);
        ventana.setLayout(new BorderLayout());
        // Hace que el canvas tengas las mismas dimensiones que la ventana
        ventana.add(this, BorderLayout.CENTER);
        // Ventana sin bordes
        //ventana.setUndecorated(true);
        // Escala la ventana
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    /**
     * Método getInstance
     * Implementación de Singleton para que no pueda existir más de una
     * instancia de Juego.
     * @return Instancia de Juego.
     */
    public static Juego getInstance() {
        if(instancia == null) {
            instancia = new Juego();
        }
        return instancia;
    }

    /**
     * Método main:
     * El main es público, ya que debemos poder acceder a él.
     */
    public static void main(String[] args) {
        //Juego juego = new Juego();
        Juego juego = Juego.getInstance();

        // Ejecutamos el juego con el método "iniciar()"
        juego.iniciar();
    }

    /**
     * Método iniciar:
     * Método que se encarga de iniciar el thread del Juego.
     * Es decir, el juego en sí.
     */
    private synchronized void iniciar(){
        enFuncionamiento = true;
        thread = new Thread(this, "graficos");
        thread.start();
    }

    /**
     * Método detener:
     * Método que se encarga de detener el juego.
     */
    private synchronized void detener(){
        enFuncionamiento = false;

        try {
            // Espera a que el thread acabe de ejecutarse antes de detenerlo
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * Método actualizar:
     * Este método se encarga de actualizar todas las variables del juego.
     */
    private void actualizar(){
        teclado.actualizar();
        if(teclado.enter && gameState != "run") {
            jugador.quitarObs(inventario);
            gameState = "run";
        }
        else if(teclado.inventario && gameState != "inventario")
        {
            gameState = "inventario";
            jugador.agregarObs(inventario);
        }
        jugador.actualizar();
        enemigofinal.actualizar();
        // Acciones que se toman dependiendo de las teclas presionadas
        if (teclado.salir){
            System.exit(0);
        }

        aps++;
    }

    /**
     * Método mostrar:
     * Muestra el juego en pantalla, aumentando la cantidad de frames
     * por segundo mostrados.
     */
    private void dibujar() {
        // Se crea un buffer, es decir, un espacio de memoria
        // donde guardamos las imágenes
        BufferStrategy estrategia = getBufferStrategy();
        if(estrategia == null) {
            // Se crean 3 buffers
            createBufferStrategy(3);
            return;
        }

        //pantalla.limpiar();
        mapa.dibujar(jugador.getPosicionX() - pantalla.getAncho()/2 + jugador.getSprite().getLado()/2 ,
                jugador.getPosicionY() - pantalla.getAlto()/2 + jugador.getSprite().getLado()/2, pantalla);
        jugador.dibujar(pantalla);
        enemigofinal.dibujar(pantalla);
        //pantalla.mostrarCuadro();

        // Método para copiar el array de Pantalla al de Juego
        System.arraycopy(pantalla.pixeles,0,pixeles,0,pixeles.length);

        // Este objeto se encarga de dibujar lo que este dentro del buffer
        Graphics g = estrategia.getDrawGraphics();

        // Se le pasa la imagen que debe dibujar
        g.drawImage(imagen,0,0,getWidth(),getHeight(),null);

        hud.displayInfo(g); // Se muestra el HUD
        // Luego de dibujar, se libera la memoria

        switch(gameState) {
            case "inventario" -> inventario.show(g,CONTADOR_FPS,ventana.getHeight(),ventana.getWidth()); // Se muestra el Inventario
        }
        g.dispose();

        estrategia.show();





        fps++;
    }

    @Override
    /**
     * Método run:
     * Se encarga de ejecutar el hilo del juego, utilizando un
     * búcle principal
     */
    public void run() {
        // TEMPORIZADOR PARA LOS FPS
        // Un segundo en nanosegundos
        final int NS_POR_SEGUNDO = 1000000000;
        // Actualizaciones por segundo objetivo
        final byte APS_OBJETIVO = 60;
        // Nanosegundos que transcurren por actualización
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO/APS_OBJETIVO;

        // System.nanoTime() divide el tiempo en ns, tomando como referencia
        // los ciclos de reloj del procesador (Since Java 1.5)
        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();

        double tiempoTranscurrido;
        // Delta es la cantidad de tiempo que transcurrió hasta que se realizó una actualización
        double delta = 0;

        // Hacemos que se reconozca el teclado solo si está seleccionada la ventana de juego
        requestFocus();

        // BUCLE PRINCIPAL DEL JUEGO
        while(enFuncionamiento){
            final long inicioBucle = System.nanoTime();

            // Calculamos la diferencia de tiempo desde que comienza el bucle
            // hasta la siguiente línea, para tener una referencia de tiempo
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;

            // Incrementamos delta TODO: Hacer cálculos
            delta += tiempoTranscurrido/NS_POR_ACTUALIZACION;

            // Los cálculos hechos más arriba sirven para que el juego se actualize 60 veces por segundo (aprox.)
            while (delta >= 1){
                actualizar();
                delta--;
            }
            dibujar();

            enemigofinal.dibujar(pantalla);
            // Si la diferencia de tiempo entre el momento que se ejecuta esta línea y la
            // referencia es mayor a un segundo se actualiza el contador y se muestran
            // las aps y los fps
            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO){
                CONTADOR_APS = "APS: " + aps;
                CONTADOR_FPS = "FPS: " + fps;
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
            }
        } // FIN DEL BUCLE PRINCIPAL
    }


}


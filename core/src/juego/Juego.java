package juego;

import control.Teclado;

import javax.swing.JFrame;

import java.awt.Canvas;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * Clase Juego (Ejecutable):
 * Esta clase utiliza las librerías de AWT y Swing para generar
 * una interfaz gráfica donde será ejecutado el juego. También ejecuta el thread en el
 * que se correrá el juego.
 */
public class Juego extends Canvas implements Runnable{

    // Sirve para identificar la clase en caso de reutilizarla en otras circunstancias
    //private static final long serialVersionUID = 1L;

    private static final int ANCHO = 800;
    private static final int ALTO = 600;

    // Variable booleana que nos permite saber cuando el juego esta en ejecucion.
    // Al ser Volatile, la palabra no puede ejecutarse simultaneamente por 2 threads.
    private static volatile boolean  enFuncionamiento = false;

    // TODO: Cambiar nombre del juego
    private static final String NOMBRE = "juego";

    // Actualizaciones por segundo
    private static int aps = 0;
    // Frames por segundo
    private static int fps = 0;

    private static JFrame ventana;
    private static Thread thread;
    private static Teclado teclado;

    /**
     * Método Juego:
     * Constructor de la clase Juego:
     * Crea la interfaz gráfica
     */
    public Juego() {
        setPreferredSize(new Dimension(ANCHO, ALTO));

        teclado = new Teclado();
        addKeyListener(teclado);

        // Creamos la ventana
        ventana = new JFrame(NOMBRE);
        //Para que se cierre la ventana al hacer click en la cruz
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Para que el usuario no pueda cambiar el tamaño de la ventana
        ventana.setResizable(false);
        ventana.setLayout(new BorderLayout());
        // Hace que el canvas tengas las mismas dimensiones que la ventana
        ventana.add(this, BorderLayout.CENTER);
        // Escala la ventana
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    /**
     * Método main:
     * El main es público, ya que debemos poder acceder a él.
     */
    public static void main(String[] args) {
        Juego juego = new Juego();

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

        // Acciones que se toman dependiendo de las teclas presionadas
        if (teclado.arriba){
            System.out.println("ARRIBA");
        }
        if (teclado.abajo){
            System.out.println("ABAJO");
        }
        if (teclado.izquierda){
            System.out.println("IZQUIERDA");
        }
        if (teclado.derecha){
            System.out.println("DERECHA");
        }

        aps++;
    }

    /**
     * Método mostrar:
     * Muestra el juego en pantalla, aumentando la cantidad de frames
     * por segundo mostrados.
     */
    private void mostrar(){
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
        // Actualizaciones por segundo objetivo TODO: PROBAR CON 30 MÁS TARDE
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
            mostrar();

            // Si la diferencia de tiempo entre el momento que se ejecuta esta línea y la
            // referencia es mayor a un segundo se actualiza el contador y se muestran
            // las aps y los fps
            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO){
                ventana.setTitle(NOMBRE + " || APS: " + aps + " || FPS: " + fps);
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
            }
        } // FIN DEL BUCLE PRINCIPAL
    }


}


package graficos;

/**
 * Clase Sprite:
 * Seleccionamos un Sprite de la Hoja de Sprites, utilizando las coordenadas
 * de la hoja.
 * NOTA: El atributo "pixeles" es público, ya que al dibujar la
 * pantalla debemos acceder muchas veces por segundo a este.
 */
public final class Sprite {
    // Tamaño del sprite (Lado de cada cuadrado)
    private final int lado;

    private int x;
    private int y;

    // Array con los colores del sprite
    public int[] pixeles;

    private HojaSprites hojaSprites;

    // Colección de Sprites Personaje
    public static final Sprite ABAJO0 = new Sprite(64,0,0,HojaSprites.jugador);
    public static final Sprite ABAJO1 = new Sprite(64,1,0,HojaSprites.jugador);
    public static final Sprite ABAJO2 = new Sprite(64,3,0,HojaSprites.jugador);

    public static final Sprite DERECHA0 = new Sprite(64,0,1,HojaSprites.jugador);
    public static final Sprite DERECHA1 = new Sprite(64,1,1,HojaSprites.jugador);
    public static final Sprite DERECHA2 = new Sprite(64,4,1,HojaSprites.jugador);

    public static final Sprite IZQUIERDA0 = new Sprite(64,0,2,HojaSprites.jugador);
    public static final Sprite IZQUIERDA1 = new Sprite(64,3,2,HojaSprites.jugador);
    public static final Sprite IZQUIERDA2 = new Sprite(64,4,2,HojaSprites.jugador);

    public static final Sprite ARRIBA0 = new Sprite(64,0,3,HojaSprites.jugador);
    public static final Sprite ARRIBA1 = new Sprite(64,1,3,HojaSprites.jugador);
    public static final Sprite ARRIBA2 = new Sprite(64,3,3,HojaSprites.jugador);
    // Fin colección de Sprites Personaje

    // Colección de Sprites Mapa
    public static final Sprite VACIO = new Sprite(32,0);
    public static final Sprite PISO = new Sprite(64,0,0,HojaSprites.texturasFinal);
    public static final Sprite UNION1 = new Sprite(64,0,1,HojaSprites.texturasFinal);
    public static final Sprite UNION2 = new Sprite(64,0,2,HojaSprites.texturasFinal);
    public static final Sprite UNION3 = new Sprite(64,0,3,HojaSprites.texturasFinal);
    public static final Sprite UNION4 = new Sprite(64,0,4,HojaSprites.texturasFinal);
    public static final Sprite UNION5 = new Sprite(64,0,5,HojaSprites.texturasFinal);
    public static final Sprite UNION6 = new Sprite(64,0,6,HojaSprites.texturasFinal);
    public static final Sprite ESQ_INF_IZQ = new Sprite(64,0,7,HojaSprites.texturasFinal);
    public static final Sprite ESQ_SUP_IZQ = new Sprite(64,4,0,HojaSprites.texturasFinal);
    public static final Sprite LADO_IZQ = new Sprite(64,4,1,HojaSprites.texturasFinal);
    public static final Sprite LADO_HORIZ = new Sprite(64,4,2,HojaSprites.texturasFinal);
    public static final Sprite ESQ_SUP_DER = new Sprite(64,4,3,HojaSprites.texturasFinal);
    public static final Sprite LADO_DER = new Sprite(64,4,4,HojaSprites.texturasFinal);
    public static final Sprite ESQ_INF_DER = new Sprite(64,4,5,HojaSprites.texturasFinal);
    public static final Sprite NEGRO = new Sprite(64,4,6,HojaSprites.texturasFinal);
    // Fin de Colección Sprites Mapa

    /**
     * Método Sprite:
     * Constructor de la clase.
     * @param lado Tamaño del sprite
     * @param columna Posición "x" del sprite
     * @param fila Posición "y" del sprite
     * @param hojaSprites Hoja de Sprites
     */
    public Sprite(final int lado, final int columna, final int fila, final HojaSprites hojaSprites){
        this.hojaSprites = hojaSprites;
        this.lado = lado;

        // Es lado*lado porque cargamos sprites cuadrados
        pixeles = new int[this.lado * this.lado];

        // De esta manera seleccionamos un sprite (cuadrado) de la hoja
        this.x = columna * lado;
        this.y = fila * lado;

        //NOTA: LAS OPERACIONES SE EJECUTAN SECUENCIALMENTE EN JAVA, POR ESO EL x + y * lado
        //Y NO (x + y) * lado
        //TODO cambiar por arraycopy?
        for (int y = 0; y < lado; y++) {
            for (int x = 0; x < lado; x++) {
                pixeles[x + y * lado] = hojaSprites.pixeles[(x + this.x) + (y + this.y) * hojaSprites.getAncho()];
            }
        }
    }

    /**
     * Constructor del Sprite (sin Hoja de sprites)
     * Crea un sprite del tamaño determinado compuesto solo por un color
     * @param lado Tamaño del sprite
     * @param color Color del sprite
     */
    public Sprite(final int lado, final int color) {
        this.lado = lado;
        pixeles = new int[lado*lado];

        for(int i = 0; i < pixeles.length; i++) {
            pixeles[i] = color;
        }
    }

    /**
     * Método getLado.
     * @return devuelve el lado del Sprite.
     */
    public int getLado() {
        return lado;
    }
}

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

    private final HojaSprites hojaSprites;

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

        // De esta manera seleccionamos un sprite (cuadrado) de la hoja TODO: Revisar si es correcto
        this.x = columna * lado;
        this.y = fila * lado;

        //NOTA: LAS OPERACIONES SE EJECUTAN SECUENCIALMENTE EN JAVA, POR ESO EL x + y * lado
        //Y NO (x + y) * lado

        for (int y = 0; y < lado; y++) {
            for (int x = 0; x < lado; x++) {
                pixeles[x + y * lado] = hojaSprites.pixeles[(x + this.x) + (y + this.y) * hojaSprites.getAncho()];
            }
        }
    }

}

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
    public static final Sprite ABAJO0 = new Sprite(64, 0, 0, 0, HojaSprites.jugador);
    public static final Sprite ABAJO1 = new Sprite(64, 1, 0, 0, HojaSprites.jugador);
    public static final Sprite ABAJO2 = new Sprite(64, 2, 0, 0, HojaSprites.jugador);
    public static final Sprite ABAJOGOLPE = new Sprite(64, 3, 0, 0, HojaSprites.jugador);

    public static final Sprite DERECHA0 = new Sprite(64, 0, 1, 0, HojaSprites.jugador);
    public static final Sprite DERECHA1 = new Sprite(64, 1, 1, 0, HojaSprites.jugador);
    public static final Sprite DERECHA2 = new Sprite(64, 2, 1, 0, HojaSprites.jugador);
    public static final Sprite DERECHAGOLPE = new Sprite(64, 3, 1, 0, HojaSprites.jugador);

    public static final Sprite IZQUIERDA0 = new Sprite(64, 0, 2, 0, HojaSprites.jugador);
    public static final Sprite IZQUIERDA1 = new Sprite(64, 1, 2, 0, HojaSprites.jugador);
    public static final Sprite IZQUIERDA2 = new Sprite(64, 2, 2, 0, HojaSprites.jugador);
    public static final Sprite IZQUIERDAGOLPE = new Sprite(64, 3, 2, 0, HojaSprites.jugador);

    public static final Sprite ARRIBA0 = new Sprite(64, 0, 3, 0, HojaSprites.jugador);
    public static final Sprite ARRIBA1 = new Sprite(64, 1, 3, 0, HojaSprites.jugador);
    public static final Sprite ARRIBA2 = new Sprite(64, 2, 3, 0, HojaSprites.jugador);
    public static final Sprite ARRIBAGOLPE = new Sprite(64, 3, 3, 0, HojaSprites.jugador);
    // Fin colección de Sprites Personaje

    // Colección sprites personaje berserk
    public static final Sprite ABAJO0_B = new Sprite(64, 0, 0, 0, HojaSprites.jugadorBerserk);
    public static final Sprite ABAJO1_B = new Sprite(64, 1, 0, 0, HojaSprites.jugadorBerserk);
    public static final Sprite ABAJO2_B = new Sprite(64, 2, 0, 0, HojaSprites.jugadorBerserk);
    public static final Sprite ABAJOGOLPE_B = new Sprite(64, 3, 0, 0, HojaSprites.jugadorBerserk);

    public static final Sprite DERECHA0_B = new Sprite(64, 0, 1, 0, HojaSprites.jugadorBerserk);
    public static final Sprite DERECHA1_B = new Sprite(64, 1, 1, 0, HojaSprites.jugadorBerserk);
    public static final Sprite DERECHA2_B = new Sprite(64, 2, 1, 0, HojaSprites.jugadorBerserk);
    public static final Sprite DERECHAGOLPE_B = new Sprite(64, 3, 1, 0, HojaSprites.jugadorBerserk);

    public static final Sprite IZQUIERDA0_B = new Sprite(64, 0, 2, 0, HojaSprites.jugadorBerserk);
    public static final Sprite IZQUIERDA1_B = new Sprite(64, 2, 2, 0, HojaSprites.jugadorBerserk);
    public static final Sprite IZQUIERDA2_B = new Sprite(64, 2, 2, 0, HojaSprites.jugadorBerserk);
    public static final Sprite IZQUIERDAGOLPE_B = new Sprite(64, 3, 2, 0, HojaSprites.jugadorBerserk);

    public static final Sprite ARRIBA0_B = new Sprite(64, 0, 3, 0, HojaSprites.jugadorBerserk);
    public static final Sprite ARRIBA1_B = new Sprite(64, 1, 3, 0, HojaSprites.jugadorBerserk);
    public static final Sprite ARRIBA2_B = new Sprite(64, 2, 3, 0, HojaSprites.jugadorBerserk);
    public static final Sprite ARRIBAGOLPE_B = new Sprite(64, 3, 3, 0, HojaSprites.jugadorBerserk);
    // Fin colección de personaje berserk

    // Colección de Sprites Enemigo Final
    public static final Sprite ABAJO_E0 = new Sprite(64, 0, 0, 0, HojaSprites.enemigoFinal);
    public static final Sprite ABAJO_E1 = new Sprite(64, 1, 0, 0, HojaSprites.enemigoFinal);
    public static final Sprite ABAJO_E2 = new Sprite(64, 2, 0, 0, HojaSprites.enemigoFinal);
    // Fin colección de Sprites Enemigo Final

    // Colección de Sprites Mapa
    public static final Sprite VACIO = new Sprite(32, 0);
/*
    public static final Sprite PISO = new Sprite(64,0,0,0,HojaSprites.texturasFinal);
    public static final Sprite ESQUINA1A = new Sprite(64,0,1,0,HojaSprites.texturasFinal);
    public static final Sprite ESQUINA1B = new Sprite(64,0,2,0,HojaSprites.texturasFinal);
    public static final Sprite PARED1 = new Sprite(64,0,3,0,HojaSprites.texturasFinal);
    public static final Sprite PARED2 = new Sprite(64,0,4,0,HojaSprites.texturasFinal);
    public static final Sprite ESQUINA1ARX = new Sprite(64, 1, 0, 0, HojaSprites.texturasFinal);
    public static final Sprite ESQUINA1BRX = new Sprite(64, 1, 1, 0, HojaSprites.texturasFinal);
    public static final Sprite ESQ_INF_IZQ = new Sprite(64, 1, 2, 0, HojaSprites.texturasFinal);
    public static final Sprite ESQ_SUP_IZQ = new Sprite(64, 1, 3, 0, HojaSprites.texturasFinal);
    public static final Sprite LADO_IZQ = new Sprite(64, 1, 4, 0, HojaSprites.texturasFinal);
    public static final Sprite LADO_HORIZ = new Sprite(64, 2, 0, 0, HojaSprites.texturasFinal);
    public static final Sprite ESQ_SUP_DER = new Sprite(64,2,1,0,HojaSprites.texturasFinal);
    public static final Sprite LADO_DER = new Sprite(64,2,2,0,HojaSprites.texturasFinal);
    public static final Sprite ESQ_INF_DER = new Sprite(64, 2, 3, 0, HojaSprites.texturasFinal);
*/
    // Fin de Colección Sprites Mapa

    // Colección de Sprites Reducida

    public static final Sprite PISO = new Sprite(64, 0, 0, 0, HojaSprites.texturasFinalReducidas);
    public static final Sprite ESQUINA1A = new Sprite(64, 0, 1, 0, HojaSprites.texturasFinalReducidas);
    public static final Sprite ESQUINA1B = new Sprite(64, 0, 2, 0, HojaSprites.texturasFinalReducidas);
    public static final Sprite ESQUINA1ARX = new Sprite(64, 0, 1, 0, HojaSprites.texturasFinalReducidas);
    public static final Sprite ESQUINA1BRX = new Sprite(64, 0, 2, 0, HojaSprites.texturasFinalReducidas);
    public static final Sprite PARED1 = new Sprite(64, 0, 3, 0, HojaSprites.texturasFinalReducidas);
    public static final Sprite PARED2 = new Sprite(64, 0, 4, 0, HojaSprites.texturasFinalReducidas);
    public static final Sprite ESQ_SUP_DER = new Sprite(64, 1, 0, 1, HojaSprites.texturasFinalReducidas);
    public static final Sprite ESQ_SUP_IZQ = new Sprite(64, 1, 0, 1, HojaSprites.texturasFinalReducidas);
    public static final Sprite ESQ_INF_DER = new Sprite(64, 1, 0, 1, HojaSprites.texturasFinalReducidas);
    public static final Sprite ESQ_INF_IZQ = new Sprite(64, 1, 0, 1, HojaSprites.texturasFinalReducidas);
    public static final Sprite LADO_DER = new Sprite(64, 4, 1, 1, HojaSprites.texturasFinalReducidas2);
    public static final Sprite LADO_IZQ = new Sprite(64, 4, 1, 1, HojaSprites.texturasFinalReducidas2);
    public static final Sprite LADO_HORIZ = new Sprite(64, 4, 1,1, HojaSprites.texturasFinalReducidas2);

    // Fin de Colección Sprites Reducidos

    /**
     * Método Sprite:
     * Constructor de la clase.
     *
     * @param lado        Tamaño del sprite
     * @param columna     Posición "x" del sprite
     * @param fila        Posición "y" del sprite
     * @param hojaSprites Hoja de Sprites
     */
    public Sprite(final int lado, final int columna, final int fila, final int version, final HojaSprites hojaSprites) {
        this.hojaSprites = hojaSprites;
        this.lado = lado;

        // Es lado*lado porque cargamos sprites cuadrados
        pixeles = new int[lado *lado];

        // De esta manera seleccionamos un sprite (cuadrado) de la hoja
        this.x = columna * lado;
        this.y = fila * lado;

        // Se llama a la función que carga el Sprite según la version solicitada
        cargarSprite(version);
    }

    /**
     * Constructor del Sprite (sin Hoja de sprites)
     * Crea un sprite del tamaño determinado compuesto solo por un color
     *
     * @param lado  Tamaño del sprite
     * @param color Color del sprite
     */
    public Sprite(final int lado, final int color) {
        this.lado = lado;
        pixeles = new int[lado * lado];

        for (int i = 0; i < pixeles.length; i++) {
            pixeles[i] = color;
        }
    }

    /**
     * Método getLado.
     *
     * @return devuelve el lado del Sprite.
     */
    public int getLado() {
        return lado;
    }

    /**
     * Método cargarSprite
     * Carga el Sprite según la versión especificada
     *
     * @param version 0 devuelve el Sprite normal, 1 lo hace espejado según el eje X,
     *               2 lo devuelve espejado según el eje Y, 3 lo hace invertido en ambos ejes,
     *                4 lo rota 90° a la izquierda y 5 90° a la derecha
     */
    private void cargarSprite(int version) {
        if (version == 0) {
            cargaNormal();
        } else if(version <= 5 ) {
            cargaManipulada(version);
        }
        else System.out.println("Error al convertir el sprite, operación invalida");
    }

    /**
     * Método cargaNormal
     * Carga el Sprite normal, sin rotarlo ni espejarlo
     */
    private void cargaNormal() {
        //NOTA: LAS OPERACIONES SE EJECUTAN SECUENCIALMENTE EN JAVA, POR ESO EL x + y * lado
        //Y NO (x + y) * lado
        for (int y = 0; y < lado; y++) {
            for (int x = 0; x < lado; x++) {
                pixeles[x + y * lado] = hojaSprites.pixeles[(x + this.x) + (y + this.y) * hojaSprites.getAncho()];
            }
        }
    }

    /**
     * Método cargaManipulada
     * Carga el Sprite rotado, espejado o ambas según la version que se solicite
     *
     * @param version 0 devuelve el Sprite normal, 1 lo hace espejado según el eje X,
     *                2 lo devuelve espejado según el eje Y, 3 lo hace invertido en ambos ejes,
     *                4 lo rota 90° a la izquierda y 5 90° a la derecha
     */
    private void  cargaManipulada(int version) { // TODO hacerlo andar
        int[] pixelesTemporales = iniciarPixelesTemp();
        switch (version) {
            case 1 -> invertirX(pixelesTemporales);
            case 2 -> invertirY(pixelesTemporales);
            case 3 -> invertirXY(pixelesTemporales);
            case 4 -> rotar90I(pixelesTemporales);
            case 5 -> rotar90D(pixelesTemporales);
            default -> cargaNormal();
        }
    }

    /**
     * Método iniciarPixelesTemp
     * Inicia un arreglo de píxeles temporal, para poder rotar los Sprites
     */
    private int[] iniciarPixelesTemp() {
        int[] pixelesTemporales = new int[lado * lado];
        for (int y = 0; y < lado; y++) {
            for (int x = 0; x < lado; x++) {
                pixelesTemporales[x + y * lado] = hojaSprites.pixeles[(x + this.x) + (y + this.y) * hojaSprites.getAncho()];
            }
        }
        return pixelesTemporales;
    }

    /**
     * Método invertirXY
     * Invierte el Sprite en ambos ejes
     */
    public void invertirXY(int[] pixelesTemporales){
        for (int i = 0; i < pixeles.length; i++) {
            pixeles[i] = pixelesTemporales[pixelesTemporales.length -1 - i];
        }
    }

    /**
     * Método invertirX
     * Invierte el Sprite en el ejeX
     */
    public void invertirX(int[] pixelesTemporales){
        int i =0;
        for (int y = 0; y < lado; y++) {
            for (int x = lado -1; x > -1; x--) {
                pixeles[i] = pixelesTemporales[x + y * lado];
                pixeles[i] = 0xffFEFEFF; //TODO sacar esta prueba cuando ande la función
                i++;

            }
        }
    }

    /**
     * Método invertirY
     * Invierte el Sprite en el ejeX
     */
    public void invertirY(int[] pixelesTemporales){
        int i =0;
        for (int y = lado - 1; y > -1; y--) {
            for (int x = 0; x < lado; x++) {
                pixeles[i] = pixelesTemporales[x + y * lado];
                i++;
            }
        }
    }

    /**
     * Método rotar90D
     * Rota el Sprite 90° hacia la derecha
     */
    public void rotar90I(int[] pixelesTemporales){
        int i =0;
        for (int x = lado -1; x > -1; x--) {
            for (int y = 0; y < lado; y++) {
                pixeles[i] = pixelesTemporales[x + y * lado];
                i++;
            }
        }
    }

    /**
     * Método rotar90D
     * Rota el Sprite 90° hacia la derecha
     */
    public void rotar90D(int[] pixelesTemporales){
        int i =0;
        for (int x = 0; x < lado; x++) {
            for (int y = lado - 1; y > -1 ; y--) {
                pixeles[i] = pixelesTemporales[x + y * lado];
                i++;
            }
        }
    }
}

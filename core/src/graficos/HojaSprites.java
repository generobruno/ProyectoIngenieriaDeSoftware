package graficos;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Clase HojaSprites:
 * Se encarga de cargar la imagen con los sprites (Sprites Sheet).
 * NOTA: El atributo "pixeles" es público, ya que al dibujar la
 * pantalla debemos acceder muchas veces por segundo a este.
 */
public class HojaSprites {
    private final int ancho;
    private final int alto;
    public final int[] pixeles;

    /**
     * Método HojaSprites:
     * Constructor de la clase. Cargamos una imagen y se crea un array con
     * todos sus pixeles.
     * @param ruta Ruta de la imagen (Carpeta y nombre del archivo)
     * @param ancho Ancho de la imagen
     * @param alto Largo de la imagen
     */
    public HojaSprites(final String ruta, final int ancho, final int alto){
        this.ancho = ancho;
        this.alto = alto;

        pixeles = new int[ancho*alto];

        BufferedImage imagen;
        try{
            // Lee la imagen
            imagen = ImageIO.read(HojaSprites.class.getResource(ruta));
            // Escanea la imagen y guarda los colores de los pixeles en este array
            imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método getAlto:
     * Retorna el alto de la imagen
     * @return Alto de la imagen
     */
    public int getAlto() {
        return alto;
    }

    /**
     * Método getAncho:
     * Retorna el ancho de la imagen
     * @return Ancho de la imagen
     */
    public int getAncho() {
        return ancho;
    }
}

package mapa;

import mapa.cuadro.Cuadro;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MapaCargado extends Mapa{

    // Array que guarda los píxeles que representan los cuadros en el mapa
    private int[] pixeles;

    /**
     * Constructor de la clase. Crea un mapa aleatorio.
     * @param ruta ruta desde donde se carga el Mapa
     */

    public MapaCargado(String ruta) {
        super(ruta);
    }

    /**
     * Método cargarMapa
     * carga el mapa en un buffer desde "ruta"
     * @param ruta ruta del archivo
     */
    protected void cargarMapa(String ruta){
        try {
            BufferedImage imagen = ImageIO.read(MapaCargado.class.getResource(ruta));
            ancho = imagen.getWidth();
            alto = imagen.getHeight();
            cuadrosCatalogo = new Cuadro[ancho*alto];
            pixeles = new int[ancho*alto];
            imagen.getRGB(0,0,ancho,alto,pixeles,0,ancho);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Método generarMapa
     * genera un array ordenado de los cuadros que forman el mapa; asigna a cada color un cuadro
     */
    protected void generarMapa(){
        for(int i=0; i < pixeles.length; i++){
            switch (this.pixeles[i]) {
                case 0xffada9a9 -> cuadrosCatalogo[i] = Cuadro.PISO;
                case 0xffa8a8e1 -> cuadrosCatalogo[i] = Cuadro.ESQUINA1A;
                case 0xff6565ff -> cuadrosCatalogo[i] = Cuadro.ESQUINA1B;
                case 0xffff004e -> cuadrosCatalogo[i] = Cuadro.ESQUINA1ARX;
                case 0xffff0000 -> cuadrosCatalogo[i] = Cuadro.ESQUINA1BRX;
                case 0xffd994ed -> cuadrosCatalogo[i] = Cuadro.PARED1;
                case 0xffc62af3 -> cuadrosCatalogo[i] = Cuadro.PARED2;
                case 0xff788d15 -> cuadrosCatalogo[i] = Cuadro.ESQ_INF_IZQ;
                case 0xff51d8d6 -> cuadrosCatalogo[i] = Cuadro.ESQ_SUP_IZQ;
                case 0xfff6f050 -> cuadrosCatalogo[i] = Cuadro.ESQ_INF_DER;
                case 0xffc0e8d7 -> cuadrosCatalogo[i] = Cuadro.ESQ_SUP_DER;
                case 0xff31a341 -> cuadrosCatalogo[i] = Cuadro.LADO_IZQ;
                case 0xff1b5756 -> cuadrosCatalogo[i] = Cuadro.LADO_DER;
                case 0xff28c682 -> cuadrosCatalogo[i] = Cuadro.LADO_HORIZ;
                default -> cuadrosCatalogo[i] = Cuadro.VACIO;
            }
        }
    }
}

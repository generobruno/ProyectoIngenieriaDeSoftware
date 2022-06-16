package mapa;

import java.util.Random;

public class MapaGenerado extends Mapa {
    private static final Random aleatorio = new Random();

    public MapaGenerado(int alto, int ancho) {
        super(ancho, alto);
    }

    /**
     * Método generarMapa
     * Genera un mapa con cuadros al azar.
     */
    protected void generarMapa() {
        for(int y = 0; y < alto; y++) {
            for(int x = 0; x < ancho; x++) {
                cuadros[x + y * ancho] = aleatorio.nextInt(15);
            }
        }
    }

}

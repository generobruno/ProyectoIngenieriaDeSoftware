package entes.criaturas;

public class RegistroEnemigos {

    public static Enemigo getEnemigo(final int idEnemigo) {
        Enemigo enemigo = null;

        switch (idEnemigo) {
            case 1:
                enemigo = new Viale(idEnemigo, "Viale", 800);
                break;
        }

        return enemigo;
    }

}

package entes.criaturas;

import graficos.observer.Observer;

import java.util.ArrayList;

public interface Subject {
    // Arreglo que guarda los observadores del Jugador
    ArrayList<Observer> observers = new ArrayList<>();

    /**
     * Método agregarObs
     * Agrega un observador al arreglo de observadores;
     * @param observer observador a agregar
     */
    void agregarObs(Observer observer);
    /**
     * Método quitarObs
     * Elimina un observador al arreglo de observadores;
     * @param observer observador a eliminar
     */
    void quitarObs(Observer observer);
    /**
     * Método notificar
     * Notifica de cambios a los observadores del arreglo de observadores;
     */
    void notificar();
}

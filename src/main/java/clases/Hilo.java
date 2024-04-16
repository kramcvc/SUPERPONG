/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import presentacion.TableroJuego;
import java.util.logging.Level;
import java.util.logging.Logger;
import menu.*;
import propiedades.Config;

/**
 *
 * @author mabos
 */
/**
 * Clase que representa el hilo principal del juego.
 */
public class Hilo extends Thread {
    
    TableroJuego lamina;
    private Pelota pelota;
    private Raqueta r1;
    private Raqueta r2;
    private Obstaculo o3;
    private Obstaculo o4;
    private Obstaculo o5;
    private Obstaculo o6;
    HiloSecundario scoreThread;
    boolean pararHiloPrincipal = false;
    PantallaPrincipal main;
    public static double deltaTimeTotalPelota = 1500000;
    public static double deltaTimeTotalRaqueta = 1000000;
    public static double deltaTimeTotalObstaculo = 10000000;
    private double deltaTimePelota = 0;
    private double deltaTimeRaqueta = 0;
    private double deltaTimeObstaculo = 0;
    private double deltaTimeDificultad = 0;

    public void resetDeltaTimeVariables() {
    deltaTimeTotalPelota = 1500000;
    deltaTimeTotalRaqueta = 1000000;
    deltaTimeTotalObstaculo = 10000000;
    deltaTimePelota = 0;
    deltaTimeRaqueta = 0;
    deltaTimeObstaculo = 0;
    deltaTimeDificultad = 0;
}
    
    

    /**
     * Constructor de la clase Hilo.
     *
     * @param lamina El tablero de juego al que pertenece el hilo.
     */
    public Hilo(TableroJuego lamina) {
        this.lamina = lamina;
    }

    /**
     * Método para establecer la pelota en el hilo.
     *
     * @param pelota La pelota del juego.
     */
    public void setPelota(Pelota pelota) {
        this.pelota = pelota;
    }

    /**
     * Método para establecer las raquetas en el hilo.
     *
     * @param r1 Raqueta del jugador 1.
     * @param r2 Raqueta del jugador 2.
     */
    public void setRaquetas(Raqueta r1, Raqueta r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    /**
     * Método para establecer los obstáculos en el hilo.
     *
     * @param o3 Obstáculo 3.
     * @param o4 Obstáculo 4.
     * @param o5 Obstáculo 5.
     * @param o6 Obstáculo 6.
     */
    public void setObstaculos(Obstaculo o3, Obstaculo o4, Obstaculo o5, Obstaculo o6) {
        this.o3 = o3;
        this.o4 = o4;
        this.o5 = o5;
        this.o6 = o6;
    }

    /**
     * Método para detener el hilo principal.
     */
    public void pararPutoHilo() {
        pararHiloPrincipal = true;
    }

    /**
     * Método que ejecuta las acciones del hilo principal.
     */
    @Override
    public void run() {
        System.out.println(Config.nivelDificultad);
        
        if(Config.singlePlayer){
        switch(Config.nivelDificultad){
            default:
                break;
            case 0: 
                deltaTimeTotalPelota *= 2;
                break;
            case 2: 
                break;
            case 3:
                deltaTimeTotalPelota *= 0.5;
                deltaTimeTotalRaqueta *= 0.5;
                break;
            case 4:
                deltaTimeTotalPelota *= 0.67;
                deltaTimeTotalRaqueta *= 0.5;
                deltaTimeTotalPelota /=2;
                deltaTimeTotalRaqueta *= 0.5;
                break;
            
        }
        }
        System.out.println(deltaTimeTotalPelota);
        // Inicialización de la música de fondo del juego
        Audio audio = new Audio("sonidos/musicaJuego.wav");
        audio.loop(); // Reproducción continua de la música

        // Inicialización del hilo secundario para el puntaje
        inicializarHiloSecundario();
        scoreThread.start();

        // Variables para controlar el tiempo de movimiento de los elementos del juego

        // Obtener el tiempo actual
        long lastUpdateTime = System.nanoTime();
        while (!pararHiloPrincipal) {
            if (pararHiloPrincipal) {
                // Si se debe detener el hilo, esperar un tiempo antes de continuar
                try {
                    Thread.sleep(40);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            long currentTime = System.nanoTime();
            long elapsedTime = currentTime - lastUpdateTime; // Tiempo transcurrido desde la última iteración
            deltaTimeRaqueta += elapsedTime;
            deltaTimePelota += elapsedTime;
            deltaTimeObstaculo += elapsedTime;
            deltaTimeDificultad += elapsedTime;

            // Aumentar la dificultad cada 20 segundos
////            if () {
//                deltaTimeTotalPelota *= 0.5; // Reducir el tiempo de movimiento de la pelota
//                System.out.println("se aumenta velocidad");
//            }

            if (deltaTimePelota >= deltaTimeTotalPelota) {
                if (!Pelota.finJuego) {
                    pelota.mover(lamina.getBounds(), lamina.colision(r1.getRaqueta()), lamina.colision(r2.getRaqueta()), lamina.colision(o3.getObstaculo()), lamina.colision(o4.getObstaculo()), lamina.colision(o5.getObstaculo()), lamina.colision(o6.getObstaculo()));
                    if (Config.singlePlayer){
                    r2.moverR2(lamina.getBounds(), Config.singlePlayer);
                    }
//                    System.out.println(deltaTimeTotalPelota);
//                    System.out.println(deltaTimePelota);
                }

                lamina.repaint();
                deltaTimePelota = 0; // Reiniciar el tiempo transcurrido
            }
            // Mover las raquetas
            if (deltaTimeRaqueta >= deltaTimeTotalRaqueta) {
                r1.moverR1(lamina.getBounds());
                if(!Config.singlePlayer){
                r2.moverR2(lamina.getBounds(), Config.singlePlayer);
                }
                lamina.repaint();
                deltaTimeRaqueta = 0;

                // Mover la pelota si ha pasado suficiente tiempo
            }

            // Mover los obstáculos
            if (deltaTimeObstaculo >= deltaTimeTotalObstaculo) {
                o6.moverObstaculo6(lamina.getBounds(), o6);
                o3.moverObstaculo3(lamina.getBounds(), o3);
                deltaTimeObstaculo = 0;
            }
            lastUpdateTime = System.nanoTime();
            if (Pelota.finJuego) {
                audio.stop(); // Detener la música cuando termina el juego
            }
        }

        // Detener el hilo secundario
        scoreThread.establecerParoHiloSecundario();
        lamina.repaint();
        resetDeltaTimeVariables();
        PantallaFinal.pFinal = new PantallaFinal();
        PantallaFinal.pFinal.setVisible(true);
    }

    public void inicioMain() {
        // main = new mai
    }

    /**
     * Método para inicializar el hilo secundario de puntaje.
     */
    public void inicializarHiloSecundario() {
        scoreThread = new HiloSecundario(lamina);
    }

    /**
     * Método para establecer la parada del hilo.
     */
}

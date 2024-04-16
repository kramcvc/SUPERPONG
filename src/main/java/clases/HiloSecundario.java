/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import BBDD.InsertMySQL;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import presentacion.TableroJuego;
import propiedades.Config;

/**
 * Clase que representa un hilo secundario para controlar ciertas tareas en el juego.
 */
public class HiloSecundario extends Thread {

    private BufferedImage fondo;
    public static int scoreMilisegundos = 0;
    public static int levelUp = 0;
    public static int mapa = 0;
    private volatile boolean detenerHiloSecundario = false;
    TableroJuego lamina;

    /**
     * Constructor de la clase HiloSecundario.
     *
     * @param lamina La lámina de juego asociada al hilo.
     */
    public HiloSecundario(TableroJuego lamina) {
        this.lamina = lamina;
    }

    /**
     * Método para detener el hilo secundario.
     */
    public void establecerParoHiloSecundario() {
        detenerHiloSecundario = true;
    }

    /**
     * Método que se ejecuta cuando el hilo es iniciado.
     */
    @Override
    public void run() {
 
        lamina.corazonesPng();
        long lastUpdateTime = System.currentTimeMillis(); // Obtener el tiempo actual antes de entrar en el bucle.
        while (!Pelota.finJuego) {

            long currentTime = System.currentTimeMillis(); // Obtener el tiempo de la iteración.
            long elapsedTime = currentTime - lastUpdateTime; // Obtener el tiempo transcurrido desde la última iteración.
            scoreMilisegundos += elapsedTime; // Actualizar el contador de milisegundos.
            levelUp += elapsedTime; // Actualizar el contador de milisegundos.
            if (levelUp >= 20000 || detenerHiloSecundario) {
                if (detenerHiloSecundario == true) {
                    break;
                }
                Audio audio = new Audio("sonidos/levelUp.wav");
                audio.play();
                if (mapa < 3) {
                    mapa++;
                    lamina.cambiarFondo(cambiarImagen(mapa));
                }
                levelUp = 0;
                Hilo.deltaTimeTotalPelota *= 0.9;
                Hilo.deltaTimeTotalRaqueta*=0.9;

            }
            lastUpdateTime = currentTime; // Actualizar el tiempo de la última actualización.

        }
        Config.tiempo = scoreMilisegundos/20000;
        switch(Config.nivelDificultad){
            default:
                break;
            case 1: 
                Config.puntos = Pelota.golpesR1 * (scoreMilisegundos / 2);
                break;
            case 2: 
                Config.puntos = Pelota.golpesR1 * scoreMilisegundos;
                break;
            case 3:
                Config.puntos = Pelota.golpesR1 * (scoreMilisegundos * 2);

            case 4:
                Config.puntos = Pelota.golpesR1 * (scoreMilisegundos * 3);

            
        }
        
        //InsertMySQL.insertMySQL();
        System.out.println("Se apago HILO SECUNDARIO");
        System.out.println(Pelota.golpesR1);
        System.out.println(Config.puntos);
        lamina.repaint();
        Config.tiempo = scoreMilisegundos/20000;
        scoreMilisegundos = 0;
        levelUp = 0;
        mapa = 0;
        Pelota.finJuego = false;
        pararControles();


    }
    public void pararControles(){
            Controles.down = false;
        Controles.up = false;
        Controles.w = false;
        Controles.s = false;
    }

    /**
     * Método para cambiar la imagen de fondo del juego.
     *
     * @param mapa Número que indica el mapa a cargar.
     * @return La imagen de fondo correspondiente al mapa especificado.
     */
    public BufferedImage cambiarImagen(int mapa) {
        if (mapa == 1) {
            try {
                fondo = ImageIO.read(new File("fondosNivel/brickwall2.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("No se pudo cargar la imagen");
            }
        }
        if (mapa == 2) {
            try {
                fondo = ImageIO.read(new File("fondosNivel/brickwall3.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("No se pudo cargar la imagen");
            }
        }
        if (mapa == 3) {
            try {
                fondo = ImageIO.read(new File("fondosNivel/brickwall4.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("No se pudo cargar la imagen");
            }
        }

        return fondo;
    }

    /**
     * Método para obtener el número de mapa actual.
     *
     * @return El número de mapa actual.
     */
    public int getMapa() {
        return mapa;
    }
}

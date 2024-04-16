/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Clase que representa un obstáculo en el juego.
 * Los obstáculos pueden tener diferentes comportamientos y pueden moverse en el juego.
 */
public class Obstaculo {

    private int x, y;
    private int ancho;
    private int alto;
    private BufferedImage imagen;
    private boolean finDeRuta1 = true;
    private boolean finDeRuta2 = true;

    /**
     * Constructor para un obstáculo estático.
     *
     * @param x La coordenada x del obstáculo.
     * @param y La coordenada y del obstáculo.
     * @param alto La altura del obstáculo.
     * @param ancho El ancho del obstáculo.
     */
    public Obstaculo(int x, int y, int alto, int ancho) {
        this.x = x;
        this.y = y;
        this.alto = alto;
        this.ancho = ancho;
    }

    /**
     * Constructor para un obstáculo con imagen.
     *
     * @param x La coordenada x del obstáculo.
     * @param y La coordenada y del obstáculo.
     * @param alto La altura del obstáculo.
     * @param ancho El ancho del obstáculo.
     * @param selector El número de la imagen del obstáculo.
     */
    public Obstaculo(int x, int y, int alto, int ancho, int selector) {
        this.x = x;
        this.y = y;
        this.alto = alto;
        this.ancho = ancho;
        try {
            // Carga la imagen del obstáculo
            imagen = ImageIO.read(new File("obstaculos/obstaculo" + selector + ".png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen del obstáculo");
        }
    }

    /**
     * Mueve el obstáculo 6.
     *
     * @param limites Los límites del área de juego.
     * @param ob6 El obstáculo 6.
     */
    public void moverObstaculo6(Rectangle limites, Obstaculo ob6) {
        if (HiloSecundario.mapa >= 3) {
            if (y > 15 && finDeRuta1) {
                y--;
            }
            if (y == 15) {
                finDeRuta1 = false;
            }
            if (y < 128 && !finDeRuta1) {
                y++;
            }
            if (y == 128) {
                finDeRuta1 = true;
            }
        }
    }

    /**
     * Mueve el obstáculo 3.
     *
     * @param limites Los límites del área de juego.
     * @param ob3 El obstáculo 3.
     */
    public void moverObstaculo3(Rectangle limites, Obstaculo ob3) {
        if (HiloSecundario.mapa >= 3) {
            if (y > 292 && !finDeRuta2) {
                y--;
            }
            if (y == 292) {
                finDeRuta2 = true;
            }
            if (y < 405 && finDeRuta2) {
                y++;
            }
            if (y == 405) {
                finDeRuta2 = false;
            }
        }
    }

    /**
     * Obtiene la imagen del obstáculo.
     *
     * @return La imagen del obstáculo.
     */
    public BufferedImage getImagen() {
        return this.imagen;
    }

    /**
     * Obtiene el rectángulo que representa el obstáculo.
     *
     * @return El rectángulo que representa el obstáculo.
     */
    public Rectangle2D getObstaculo() {
        return new Rectangle2D.Double(x, y, ancho, alto);
    }

    /**
     * Obtiene el ancho del obstáculo.
     *
     * @return El ancho del obstáculo.
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Obtiene la coordenada x del obstáculo.
     *
     * @return La coordenada x del obstáculo.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Obtiene la coordenada y del obstáculo.
     *
     * @return La coordenada y del obstáculo.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Establece la coordenada y del obstáculo.
     *
     * @param y La nueva coordenada y del obstáculo.
     */
    public void setY(int y) {
        this.y = y;
    }
}
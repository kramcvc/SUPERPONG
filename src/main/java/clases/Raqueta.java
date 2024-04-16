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
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author mabos
 */
public class Raqueta {

    private int x, y;
    public static final int ANCHO = 10, ALTO = 60;
    private BufferedImage imagen;
    private Pelota pelota;
    Random aleatorio;

    public Raqueta(int x, int y, Pelota pelota) {
        this.x = x;
        this.y = y;
        this.pelota = pelota;
        aleatorio = new Random();
        try {
            // Carga la imagen de la raqueta desde el archivo "raqueta.jpg"
            imagen = ImageIO.read(new File("raquetas/raqueta2.png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen de la raqueta");
        }
    }

    public BufferedImage getImagen() {
        return this.imagen;
    }

    public Rectangle2D getRaqueta() {
        return new Rectangle2D.Double(x, y, ANCHO, ALTO);
    }

    public int getAncho() {
        return ANCHO;
    }

        // Este metodo es para que puedas mover la raqueta.
    public void moverR1(Rectangle limites) {
        if (y > 5) {
            if (Controles.w) {
                y--;
            }
        }
        if (y < limites.getMaxY() - (ALTO + 5)) {
            if (Controles.s) {
                y++;
            }
        }
    }

    
    //Este metodo es la IA, primero coloque un aleatorio, estea aleatorio nos permitia poder ganar de vez en cuando a la maquina ya que sino seria imposible.
    //Por falta de tiempo a la entrega del trabajo decidi no implementarlo. 
    public void moverR2(Rectangle limites, boolean noJugable) {
        if (noJugable) {
            int numeroAleatorio = aleatorio.nextInt(2);

            if (pelota.getDy() == -1 && y > 5 && y > pelota.getY()) {
                int result = 1;
                this.y -= 1;
                // System.out.println(y + "raqueta");
            }
            if (pelota.getDy() == 1 && y <= limites.getMaxY() - (ALTO + 5) && y < pelota.getY()) {
                int result = (numeroAleatorio == 0) ? 1 : 0;
                this.y += 1;

            }

            if (pelota.getY() == this.y && pelota.getX() == x - 14) {


            }
            if (pelota.getDy() == 1 && y <= limites.getMaxY() - (ALTO + 5) && y < pelota.getY()) {
                int result = (numeroAleatorio == 0) ? 1 : 0;
                this.y += result;

            }
                // si no se ejecuta la IA pasa  la configuracion estandar de control.
        } if (!noJugable) {
            if (y > 5) {
                if (Controles.up) {
                    y--;

                }
            }
            if (y < limites.getMaxY() - (ALTO + 5)) {
                if (Controles.down) {
                    y++;
                }
            }
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public int getXdePelota(){
    return pelota.getX();
    }
    public int getYdePelota(){
    return pelota.getY();
    }

}

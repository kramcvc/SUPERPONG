package presentacion;

import clases.Audio;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Intro extends JPanel {
    private final int ANCHO = 800;
    private final int ALTO = 500;
    private BufferedImage imagenfondo;
    private JFrame frame;

    public Intro(JFrame ventana) {
        try {
            imagenfondo = ImageIO.read(new File("fondosNivel/intro.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No se pudo cargar la imagen");
        }
        this.frame = ventana; // Guardar la referencia al JFrame

    }
    public void esperaRapida(){
        long currentTime;
        long elapsedTime;
        int milisegundos = 0;
        long lastUpdateTime = System.currentTimeMillis(); // Obtener el tiempo actual antes de entrar en el bucle.
        while(milisegundos<5000){
        currentTime = System.currentTimeMillis(); // Obtener el tiempo de la iteración.
        elapsedTime = currentTime - lastUpdateTime; // Obtener el tiempo transcurrido desde la última iteración.
        milisegundos += elapsedTime; // Actualizar el contador de milisegundos.
       lastUpdateTime = currentTime;
    }
        System.out.println("acabe");
    }
    
 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagenfondo, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    
    public void cerrarVentana() {
        // Cerrar el JFrame al que pertenece este JPanel
        frame.setVisible(false);
        frame.dispose();
    }
}
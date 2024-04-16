package clases;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Clase que representa un objeto de audio para reproducir archivos de sonido.
 */
public class Audio {

    private Clip clip; // Objeto Clip para reproducir el audio
    private boolean isPlaying = false; // Estado de reproducción del audio

    /**
     * Constructor de la clase Audio.
     *
     * @param filename Nombre del archivo de audio a cargar y reproducir.
     */
    public Audio(String filename) {
        try {
            File file = new File(filename);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Agregar un LineListener para detectar cuando el Clip termina de reproducirse
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        //System.out.println("El audio ha terminado de reproducirse");
                        isPlaying = false;
                        //System.out.println("El audio ha terminado de reproducirse " + isPlaying);
                    }
                }
            });
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reproduce el audio desde el principio.
     */
    public void play() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
            isPlaying = true; // Actualizar el estado de reproducción
        }
    }

    /**
     * Reproduce el audio en bucle de manera continua.
     */
    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            isPlaying = true; // Actualizar el estado de reproducción
        }
    }

    /**
     * Detiene la reproducción del audio.
     */
    public void stop() {
        if (clip != null) {
            clip.stop();
            isPlaying = false; // Actualizar el estado de reproducción
        }
    }

    /**
     * Comprueba si el audio se está reproduciendo actualmente.
     *
     * @return true si el audio está reproduciéndose, false de lo contrario.
     */
    public boolean isPlaying() {
        return isPlaying;
    }

}

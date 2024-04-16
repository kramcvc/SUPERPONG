/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idioma;
import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Idioma para cargar propiedades de idiomas desde archivos .properties.
 * Extiende la clase Properties de Java.
 */
public class Idioma extends Properties {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor de la clase Idioma.
     * @param idioma El idioma que se desea cargar.
     */
    public Idioma(String idioma) {
        // Seleccionamos el idioma con un switch
        switch (idioma) {
            case "Espanol":
                getProperties("src\\main\\java\\idioma\\espanol.properties");
                break;
            case "Catalan":
                getProperties("src\\main\\java\\idioma\\catalan.properties");
                break;
            default:
                getProperties("espanol.properties"); // Si el idioma no está definido, carga el español por defecto
        }
    }

    /**
     * Descripció: Método para cargar las propiedades desde el archivo .properties.
     * @param idioma La ruta del archivo .properties que contiene las propiedades del idioma.
     */
    private void getProperties(String idioma) {
        try {
            FileInputStream input = new FileInputStream(new File(idioma));
            this.load(input); // Carga las propiedades desde el archivo .properties
        } catch (IOException ex) {
            Logger.getLogger(Idioma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


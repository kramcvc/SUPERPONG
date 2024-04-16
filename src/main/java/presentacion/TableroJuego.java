/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import clases.Audio;
import clases.Hilo;
import clases.HiloSecundario;
import clases.Obstaculo;
import clases.Pelota;
import clases.Raqueta;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import propiedades.Config;

/**
 *
 * @author mabos
 */

/**
 * Clase que representa el tablero de juego donde se desarrolla la partida de Pong.
 * Este tablero contiene la pelota, las raquetas, los obstáculos y gestiona el hilo de ejecución del juego.
 */
public class TableroJuego extends JPanel {

    private Pelota pelota;
    private Raqueta r1;
    private Raqueta r2;
    private Obstaculo o3;
    private Obstaculo o4;
    private Obstaculo o5;
    private Obstaculo o6;
    private Hilo hilo;
    private BufferedImage imagenfondo;
    private BufferedImage corazon;
    private boolean unJugador = Config.singlePlayer;
    private String jugador1 = Config.jugador1;
    private String jugador2 = Config.jugador2;
    private Ventana ventana;
    private boolean rafagazo = false;
  /**
     * Constructor de la clase TableroJuego.
     * @param ventana La ventana principal del juego.
     */
    public TableroJuego(Ventana ventana) {  //Constructor del Tablero, JPANEL es el lienzo encima de JFRAME.

        this.ventana = ventana;
        try {
 //Cargamos la foto del primer nivel, esta imagen es la primera que se va a ver, es decir este sera nuestro nivel uno. junto con un bloque try catch. 
            imagenfondo = ImageIO.read(new File("fondosNivel/brickwall.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No se pudo cargar la imagen");

        }
//Estas lineas de codigo de aqui nos señalan la posicion en el panel cuando clicamos, es bastante util, me ha ayudado muchisimo para establecer la posicion de los objeos y sus colisiones con la pelota. 

//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                // Obtener la posición del clic del ratón
//                int x = e.getX();
//                int y = e.getY();
//                // Imprimir la posición en la terminal
//                System.out.println("Mouse clicked at: (" + x + ", " + y + ")");
//            }
//        });
    }

    //En este meodo instanciamos todos los componentes del juego que vamos a usar. Esta parte es importante.
    //porque para poder recibir bien los parametros de la ventana necesitamos usando variables genericas, necesitamos generar estos objetos despues de la creacion del tablero en sí
    //Los metodos getBounds provienen de la clase Rectangle y los usamos para saber las medidas de nuestro tablero.
      /**
     * Método para inicializar todos los componentes del juego.
     * Instancia la pelota, las raquetas y los obstáculos.
     */
    public void iniciarTodo() {
        pelota = new Pelota((int) getBounds().getMaxX() / 2, (int) getBounds().getMaxY() / 2, getBounds(), this);
        r1 = new Raqueta(Raqueta.ANCHO, ((int) getBounds().getMaxY() / 2) - Raqueta.ANCHO / 2, pelota);
        //Esta raquea utiliza otro constructor, esta implementado asi para poder generar un seguimineto de pelota. Esto basicamente es para poder jugar contra la IA.
        r2 = new Raqueta((int) getBounds().getMaxX() - Raqueta.ANCHO * 2, ((int) getBounds().getMaxY() / 2) - Raqueta.ANCHO / 2, pelota);
        //Creacion de Objteos, Los objteos 4 y 4, no usan variables genericas obteniendo las posiciones desde getbounds, eso es por el hecho de como 
        // he generado el control de colisiones con la pelota. 
        //**He encontrado problemas con la generacion de imagenes en los obstaculos, por eso a veces las colisiones parece que ocurran antes, pero el objeto aparentemente se genera en esa posicion de rebote**
        o4 = new Obstaculo(88, 225, 15, 70, 1); //Lateral izquierdo
        o5 = new Obstaculo(602, 225, 15, 70, 1);  //Lateral derecho
        o3 = new Obstaculo((int) getBounds().getMaxX() / 2, 360, 35, 15, 2); //Objeto Vertical de arriba,
        o6 = new Obstaculo((int) getBounds().getMaxX() / 2, 90, 35, 15, 2); //Objeot Vertical abajo
    }

    //Metodo para la creacion del hilo, es decir el Thread que se encarga de ejecutar el juego. 
    // Es importante remarcar aqui la clase hilo se ha generado para intentar no uilizar el maximo de lo posible el comando Thread.sleep. 
    // *****OBSERVACIONES****
    // Utilizamos delta time para controlar la velocidad de la pelota. 
    // ¿Que quiere decir esto? -----> Usamos tiempo real para saber cuantos calculos por minuto tiene que hacer, esto globaliza todos los ordenadores. 
    //                                Ya que generaliza el tiempo y marca un estandar, no la velocidad que tiene el procesador generica, ya que podria ser diferente para cada caso
     /**
     * Método para iniciar el hilo de ejecución del juego.
     * Se encarga de crear el hilo y establecer los objetos necesarios.
     */
    public void iniciarHilo() {
        // Crearmos Hilo y le pasamos los parametros de los objetos. **Hacerlo en esta secuencia es importante, el hilo se crea despues que iniciamos todos los parametros, de lo contrario,
        // las iteraciones que coniene el hilo se empezarian a ejecutar antes siquiera de crear en pantalla las cosas, Ya hemos comentado esto antes.
        hilo = new Hilo(this);
        hilo.setPelota(pelota);
        hilo.setRaquetas(r1, r2);
        hilo.setObstaculos(o3, o4, o5, o6);
        hilo.start(); 
    }
 /**
     * Método que se encarga de pintar los componentes en el tablero.
     * @param g El objeto Graphics utilizado para pintar.
     */
    @Override
    public void paintComponent(Graphics g) { //metodo pertenenciente a la clase padre
        super.paintComponent(g); //super funciona para ejcutar ese comando de la clase padre Graphics.
        g.drawImage(imagenfondo, 0, 0, this.getWidth(), this.getHeight(), this); //Pintamos el fondo nivel 0
        Graphics2D g2 = (Graphics2D) g; //Hacemos esto para trabajar con la clase 2D, es una clase hija de la clase Padre Graphics, pero esta tiene mas funcionalidades.
        g2.setColor(Color.YELLOW);  //Esto pinta todo lo instanciado en graficos del color que yo quiera
        dibujar(g2); //Metodo para llamar a dibujar.
        scorePrint(g2); //metodo para dibujar por pantalla
    }

    // Este metodo lo utilizo para cambiar de imagen, segun va subiendo la dificultad/nivel, cada 20 segundos.
     /**
     * Método para cambiar el fondo del tablero durante el juego.
     * @param nuevaImagen La nueva imagen de fondo.
     */
    public void cambiarFondo(BufferedImage nuevaImagen) {
        this.imagenfondo = nuevaImagen;
        repaint();  //Comando para repintar el tablero.
    }

    // Este metodo ees igual que el anterior, lo utilizo tambien para poder pintar corazones, que son las vidas. 
     /**
     * Método para cargar la imagen de los corazones (vidas).
     */
    public void corazonesPng() {
        try {
            corazon = ImageIO.read(new File("corazon.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No se pudo cargar la imagen");

        }
    }

    //Este metodo dibuja todos los mapas y todos los corazones que he explicado anteriormente, segun el nivel que estemos y las vidas que tengamos, además al final llmamamos al metodo scoreprint.
    //Haciendo esto me di cuenta que si los eliminaba la pelota seguia colisionando con ellos asique tube que arreglar eso dentro de la clase pelota.
     /**
     * Método para dibujar los componentes en el tablero.
     * @param g El objeto Graphics2D utilizado para dibujar.
     */
    public void dibujar(Graphics2D g) {
        g.drawImage(pelota.getImagen(), pelota.getX(), pelota.getY(), null);

        
        g.drawImage(r1.getImagen(), r1.getX(), r1.getY(), null);
        g.drawImage(r2.getImagen(), r2.getX(), r2.getY(), null);
        if (HiloSecundario.mapa == 0) {

        }
        if (HiloSecundario.mapa == 1) {
            g.drawImage(o4.getImagen(), o4.getX(), o4.getY(), null);
            g.drawImage(o5.getImagen(), o5.getX(), o5.getY(), null);
        }
        if (HiloSecundario.mapa == 2) {
            g.drawImage(o3.getImagen(), o3.getX(), o3.getY(), null);
            g.drawImage(o6.getImagen(), o6.getX(), o6.getY(), null);
        }
        if (HiloSecundario.mapa == 3) {
            g.drawImage(o3.getImagen(), o3.getX(), o3.getY(), null);
            g.drawImage(o6.getImagen(), o6.getX(), o6.getY(), null);
            g.drawImage(o4.getImagen(), o4.getX(), o4.getY(), null);
            g.drawImage(o5.getImagen(), o5.getX(), o5.getY(), null);
        }

        if (Config.corazonesJ1 == 3) {
            g.drawImage(corazon, 63, 429, null);
            g.drawImage(corazon, 98, 429, null);
            g.drawImage(corazon, 133, 429, null);
        }
        if (Config.corazonesJ1 == 2) {
            g.drawImage(corazon, 63, 429, null);
            g.drawImage(corazon, 98, 429, null);
        }
        if (Config.corazonesJ1 == 1) {
            g.drawImage(corazon, 63, 429, null);

        }
        if (Config.corazonesJ1 == 0) {

        }

        if (Config.corazonesJ2 == 3) {
            g.drawImage(corazon, 688, 429, null);
            g.drawImage(corazon, 654, 429, null);
            g.drawImage(corazon, 619, 429, null);

        }
        if (Config.corazonesJ2 == 2) {
            g.drawImage(corazon, 698, 429, null);
            g.drawImage(corazon, 654, 429, null);

        }
        if (Config.corazonesJ2 == 1) {
            g.drawImage(corazon, 698, 429, null);

        }
        if (Config.corazonesJ2 == 0) {

        }
                   if(rafagazo){
        g.drawImage(pelota.getRafagazo(), r1.getX(), pelota.getyPelotaGolpe(), null);
        }
//        scorePrint(g);

    }

//Boleano capaz de detectar si un objeto y otro han colisionado en el lienzo.
     /**
     * Método para detectar colisiones entre la pelota y otros objetos.
     * @param r El rectángulo con el que se comprueba la colisión.
     * @return true si hay colisión, false si no la hay.
     */
    public boolean colision(Rectangle2D r) {
        return pelota.getPelota().intersects(r);
    }
// Ese metodo Define las puntuaciones y si el juego ha de acabar* (*estoy segurisimo que esto no debería esar aqui dentro. Pero no he sabido estructurarlo de otra forma.) Además de eso tambien tengo metodos dentro que llaman a diferentes sonidos.
    /**
     * Método para mostrar el puntaje y gestionar el fin del juego.
     * @param g El objeto Graphics2D utilizado para mostrar el puntaje.
     */
    private void scorePrint(Graphics2D g) {
        Graphics2D g1 = g, g2 = g;
        Font puntuaje = new Font("Arial", Font.BOLD, 30);
        g.setFont(puntuaje);
        if (!unJugador) {
            g1.drawString(jugador1, 24, 50);
            g1.drawString(jugador2, (float) getBounds().getMaxX() - 200, 50);
            //manejo el fin del juego aqui.
            if (Config.corazonesJ1 == 0) {
                //eso no se acaba de mostrar, esoy casiseguro que es como manejo las insrucciones de los hilos.
                g.drawString("PLAYER 2 WINS", (float) getBounds().getCenterX() - 155, (float) getBounds().getCenterY() - 100); // creo que es por el metodo reprint. que no le da tiempo a actualizar el paintComponent.
                Pelota.finJuego = true;
                musicaFinJuego();
            }
            //Manejo el fin del juego aqui.
            if (Config.corazonesJ2 == 0 && !Config.singlePlayer) {
                //eso tampoco se acaba de mostrar
                g.drawString("PLAYER 2 WINS", (float) getBounds().getCenterX() - 155, (float) getBounds().getCenterY() - 100); // creo que es por el metodo reprint. que no le da tiempo a actualizar el paintComponent.
                Pelota.finJuego = true;
                musicaFinJuego();

            }
        }
        //Lo mismo pero si es un jugador.
        if (unJugador) {

            g1.drawString(Integer.toString(HiloSecundario.scoreMilisegundos), (float) getBounds().getCenterX() - 20, 30);
            g1.drawString(jugador1, 24, 50);
            if (Config.corazonesJ1 == 0) {
                //Este mensaje tampoco se acaba de mostrar, no entiendo muy bien porque pero sin duda creo que es por culpa de como manejo los hilos.
                g.drawString("GAME OVER", (float) getBounds().getCenterX() - 155, (float) getBounds().getCenterY() - 100); // creo que es por el metodo reprint. que no le da tiempo a actualizar el paintComponent.
                Pelota.finJuego = true;

                musicaFinJuego();
            }
        }
    }
    //En este metodo manejo las musiquitas..... Y el paro y fin de hilos...., aqui me he pasado muchisimo rato haciendo pruebas para poder hacer que el juego terminara, de nuevo problemas con los hilos, no responden bien a los comandos,
    //Empeze con una idea muy reticente de usar thread.sleep, y administrar mi tiempo con delta time, de nuevo, creo que ahi he tenido problemas, cuando queria salir del bucle dentro de los hilos,
    // ocurrian problemas que no lograba entender, y que todavía sigo sin entender muy bien, no se mucho ingles, asique me ha sido casi imposible buscar informacion. ha estos problemas posiblemente les he dedicado
    //mas de 25 horas en intentar hacer cambios o modificar algunas cosas. Me he dado cuenta de que deberia de utilizar git para poder acctualizar mi codigo y hacer una guia de "versiones". eso me faciliaria hacer cambios,
    //porque la verdad soy muy desordenado escribiendo codigo, y se me olvidan las cosas que hago o dejo de hacer.
    //La solucion a poder finalizar los hilos ha sido colocando thread sleep antes de administrar una instruccion de paro,  a esta conclusion llegue a base de prueba y error, al hacer el juego instanciandolo en una clase Hilo Extens thread,
    //y ese hilo instanciar otra clase hiloSecundario extends thread, la posibilidad de hacer debug se me veia limitada porque no conozco las herramientas y la informacion para debugar mas de un hilo a la vez, y pausar uno de ellos suponia que el juego no funcionara bien
    // a demas que al utilizar el modo debug paso a paso aparentemente todo funcionaba bien (este problema creo que es porque el hilo se instanciaba demasiado deprisa)
    // Entonces me vi en la obligacion de poner prints por muchisimos sitios para saber que hacia le bucle, si paraba si no paraba... Porque me encontre que algunas veces el primer hilo cerraba pero el segundo se quedaba abierto, o viceversa.
    //Di con una solucion bastante peculiar, que estoy seguro que no es buena practica de programacion pero es lo unico que he sabido hacer, Y es que al colocar prints en segun que sitios (whiles) vi que el proceso si cerraba. Enonces entendi que
    //antes dar una orden a un hilo mientras hago otras cosas necesito hacer un thread sleep para que el hilo consiga leerlo, o quizas no pero es lo que yo he intuido.

    // Esto funciona de la siguiente manera, Se reproduce el sonido, si perdio el jugador 1 y Un jugador estaba falso se reproduce el audio de jugador 2 gana, se hace una parada de thread sleep en bucle, para que el procesador no vaya a parar las cosas antes de tiempo,
    //al acabar otro thread sleep. y bueno el siguiente if es lo mismo para el jugador2 cuando pierde.
    
     /**
     * Método para reproducir sonidos y gestionar el fin del juego.
     */
    public void musicaFinJuego() {
        Audio muerte = new Audio("sonidos/death.wav"); //Iniciacion de musica 
        Audio risa = new Audio("sonidos/boosLaugth.wav"); //Iniciacion de musica.
        muerte.play();
        risa.play();
        if (Config.corazonesJ1 == 0 && !Config.singlePlayer) {
            Audio win = new Audio("sonidos/player2Wins.wav"); //Iniciacion de musica base del juego. 
            win.play();
            while (win.isPlaying()) {
                try {
                    Thread.sleep(40);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                Thread.sleep(40);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (Config.corazonesJ2 == 0) {
            Audio win = new Audio("sonidos/player1Wins.wav"); //Iniciacion de musica base del juego.
            win.play();
            while (win.isPlaying()) {
                try {
                    Thread.sleep(40);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {
                Thread.sleep(40);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            Thread.sleep(40);
        } catch (InterruptedException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (muerte.isPlaying()) {
            try {
                Thread.sleep(40);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        hilo.pararPutoHilo();

        try {
            Thread.sleep(40);
        } catch (InterruptedException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!muerte.isPlaying()) {
            ahoraSiParate();
            //System.exit(ABORT);
        }
    }

        /**
     * Método para finalizar el juego y cerrar la ventana.
     */
    public void ahoraSiParate() {
        ventana.dispose();

    }

    public void setRafagazo(boolean rafagazo) {
        this.rafagazo = rafagazo;
    }
    

}

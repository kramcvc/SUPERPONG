/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import presentacion.TableroJuego;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import propiedades.Config;

/**
 *
 * @author mabos
 */

/**
 * Clase que representa la pelota en el juego.
 */
public class Pelota {
    public static int golpesR1 = 0;
    private int x;
    private int y;
    private int dx = 1, dy = -1;
    private int yPelotaGolpe;
    private final int ANCHO = 15, ALTO = 15;
    private BufferedImage imagen;
    private BufferedImage rafagazo;
    public static boolean finJuego = false;
    private TableroJuego tablero;
    // public static boolean gameOver = false;
    private boolean enColision = false;
    private Rectangle2D limites; // Los límites del área de juego
   /**
     * Constructor de la clase Pelota.
     *
     * @param x La coordenada x inicial de la pelota.
     * @param y La coordenada y inicial de la pelota.
     * @param limites Los límites del área de juego.
     */
    public Pelota(int x, int y, Rectangle2D limites, TableroJuego tablero) {
        this.x = x;
        System.out.println("x = " + x);
        this.y = y;
        System.out.println("y = " + y);
        this.limites = limites;
        this.tablero= tablero;
        try {
            // Carga la imagen de la raqueta desde el archivo "raqueta.jpg"
            imagen = ImageIO.read(new File("pelota.png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen de la raqueta");
        }
                try {
            // Carga la imagen de la raqueta desde el archivo "raqueta.jpg"
            rafagazo = ImageIO.read(new File("rafagazo.png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen de la raqueta");
        }
    }
    
    // Métodos de reproducción de sonido

    /**
     * Reproduce el sonido de la magia.
     */
    public void playMagicSound() {
        Audio magic = new Audio("sonidos/magic.wav");
        magic.play();
    }
    /**
     * Reproduce el sonido del impacto con la raqueta.
     */
    public void playRaquetSound() {
        Audio raquetaSound = new Audio("sonidos/raquetaHit.wav");
        raquetaSound.play();
    }
    
       /**
     * Reproduce el sonido de los huesos.
     */
    public void playBonesSound() {
        Audio bones = new Audio("sonidos/bones.wav");
        bones.play();
    }
// Este metodo devuelve una clase Rectangle2D para gestionar colisiones.
      /**
     * Obtiene el rectángulo que representa la pelota.
     *
     * @return El rectángulo que representa la pelota.
     */
    public Rectangle2D getPelota() {
        return new Rectangle2D.Double(x, y, ANCHO, ALTO);
    }
    //metodo para mover la pelota
    //Vale, comenzamos con el turrón,  este metodo ha sido modificado cientos de veces, y tiene demasiadas horas, muchisimas, de hecho mas de las que me gustaria admitir,
    //Ese metodo controla las posiciones de todos los objetos cuando toca, como usé rectangle2d el boleano colision no me dice donde se produce la colision, asique hice las colisiones
    //a mano, a base de prueba y error ayudandome del mouseclicker listen event del meotdo tablero, por mas que me pese, ese metodo todavia tiene que acabar de pulirse, me he dado cuenta que las imagenes que se acoplan a los objetos,
    //no están perfectamente alineadas con el objeto real de colision, o por lo menos yo no he sabido darme cuenta de porque pasa eso. Aun así he de decir que estoy orgulloso de haber manejado las colisiones de una manera relativamente buena.
    //he modificado muchas veces las posiciones de pelota en base a que objeto y que lugar en base del objeto colisionaba, pero si arreglaba algunas colisiones estropeaba otras. El tiempo se me acaba, asique lo dejo así.
    //En definitiva consigo en "cierta manera" que la pelota colisione adecuadamente con los bordes de los objetos, los unicos que no colisionan en laterales son los 3 y 6, estos decidi coger un "rol de magia" que le da dificultad y aleatoriedad al juego.
    //Tambien controlo cuando la pelota colisiona con ellos, es decir si aparecen en pantalla se activaran, si no aparecen en pantalla la pelota los ignorará.
    
       /**
     * Mueve la pelota y maneja las colisiones.
     *
     * @param limites Los límites del área de juego.
     * @param colisionR1 Indica si la pelota colisiona con la raqueta 1.
     * @param colisionR2 Indica si la pelota colisiona con la raqueta 2.
     * @param colisionObj3 Indica si la pelota colisiona con el objeto 3.
     * @param colisionObj4 Indica si la pelota colisiona con el objeto 4.
     * @param colisionObj5 Indica si la pelota colisiona con el objeto 5.
     * @param colisionObj6 Indica si la pelota colisiona con el objeto 6.
     */
    public void mover(Rectangle limites, boolean colisionR1, boolean colisionR2, boolean colisionObj3, boolean colisionObj4, boolean colisionObj5, boolean colisionObj6) {
 
        //Me di cuenta de que la pelota a veces hacia cosas raras, es decir como que "se metia dentro" de el objeto en si, entonces las colisiones se volvian locas. Pongo un ejemplo:
        //si una condicion se cumplia, manejaba la direccion de la pelota al lugar que le correspondia. Pero que sucedia? que al modificar la posicion otra "condicion2" se cumplia antes de poder salir de la primera colision 
        //Eso hacia que la pelota pareciese estar "atrapada" rebotando en el objetot indefinidas veces hasta lograr salir (x al no modificarse cuando pasaba el objeto salia) como que se quedara atrapada en el objeto 
        //Decidi cambiar a if else if, a veces funcionaba y otras parece que no. Hasta que decidi crear un booleano "enColision" este boleano me permite saber cuando la pelota deja de estar en colision para poder volver a ejecutar las colisiones.
        //Es decir que solo hiciera una  condicion, y que la siguiente solo se volviera a ejecutar cuando dejara de estar en colision.
     

// Este booleano indica si la pelota está en colisión con algún objeto  
        if (!enColision) {
            
        // Se verifica si los objetos son visibles depeniendo del mapa.

            if (HiloSecundario.mapa == 1 || HiloSecundario.mapa >= 3) {
                //Sonido cuando choca contra los objetos 5 y 4.
                if (colisionObj5 || colisionObj4){
                this.playBonesSound();
                }
             // Aquí se manejan las colisiones con objetos 4 y 5 desde diferentes direcciones
            // Se modifican las direcciones de la pelota según la dirección y posición del choque
            // Se registran mensajes de depuración para identificar las colisiones

               
                if ((colisionObj5) && dx == -1 && dy == -1 && y >= 223 && y <= 239 && x >= 679) {
                    dx = 1;
                    dy = -1;
                  //  System.out.println("ENTRADALATERAL 1");   // OBJETO DERECHA LATERAL DERECHO viene subiendo

                } else if ((colisionObj5) && dx == -1 && dy == 1 && y >= 223 && y <= 239 && x >= 679 ) {
                    dx = 1;
                    dy = 1;
                    x += 5;
                //  System.out.println("ENTRADALATERAL 2");   // OBJETO DERECHA LATERAL DERECHO viene bajando

                } else if ((colisionObj5) && dx == 1 && dy == -1 && y >= 223 && y <= 239 && (x <= 610 && x> 582)) {
                    dx = -1;
                    dy = -1;
                    x -= 5;
                //   System.out.println("ENTRADALATERAL 3");   // OBJETO DERECHA LATERAL izquierdo viene subiendo

                } else if ((colisionObj5) && dx == 1 && dy == 1 && y >= 223 && y <= 239 && x <= 671) {
                    dx = -1;
                    dy = 1;
                    x -= 5;
//                   System.out.println("ENTRADALATERAL 4");   // OBJETO DERECHA LATERAL izquierdo viene bajando

                } else if ((colisionObj4) && dx == -1 && dy == -1 && y >= 223 && y <= 239 && x >= 158 ) {
                    dx = 1;
                    dy = -1;
                    x += 5;
//                   System.out.println("ENTRADALATERAL 5");   // OBJETO izquierda LATERAL DERECHO viene subiendo

                } else if ((colisionObj4) && dx == -1 && dy == 1 && y >= 223 && y <= 239 && x >= 158) {
                    dx = 1;
                    dy = 1;
                    x += 5;
//                    System.out.println("ENTRADALATERAL 6");   // OBJETO izquierda LATERAL DERECHO viene bajando

                } else if ((colisionObj4) && dx == 1 && dy == -1 && y >= 223 && y <= 239 && x <= 87) {
                    dx = -1;
                    dy = -1;
                    x -= 5;
//                   System.out.println("ENTRADALATERAL 7");   // OBJETO izquierda LATERAL izquierdo viene subiendo

                } else if ((colisionObj4) && dx == 1 && dy == 1 && y >= 223 && y <= 239 && x >= 87) {
                    dx = -1;
                    dy = 1;
                    x -= 5;
//                  System.out.println("ENTRADALATERAL 8");   // OBJETO izquierda LATERAL izquierdo viene bajando

                } 
                
                //Colision objetos 4 y 5 por arriba y por abajo.
                
                else if ((colisionObj4 || colisionObj5) && dx == 1 && dy == 1 && ( (x<168 && x>94) || (x< 687 && x>606))) {

                    dy = -1;
                    dx = 1;
//                 System.out.println("ENTRADA 1");
                } else if ((colisionObj4 || colisionObj5) && dx == -1 && dy == 1 && y<230-ANCHO) {
                    dx = -1;
                    dy = -1;
//                    System.out.println("ENTRADA2");

                }
                else if ((colisionObj4 || colisionObj5) && dx == 1 && dy == -1) {
                    dx = 1;
                    dy = 1;
//                  System.out.println("ENTRADA3");
                } else if ((colisionObj4 || colisionObj5) && dx == -1 && dy == -1) {
                    dx = -1;
                    dy = 1;
                  //  System.out.println("ENTRADA4");
                }
            }
           // Se verifica si los objetos son visibles depeniendo del mapa.
            if (HiloSecundario.mapa == 2 || HiloSecundario.mapa >= 3) {
            // Se reproducen sonidos al colisionar con objetos 3 y 6

            if (colisionObj3 || colisionObj6){
                this.playMagicSound();
               
            }// Aquí se manejan las colisiones con objetos 3 y 6 desde diferentes direcciones
            // Se modifican las direcciones de la pelota según la dirección y posición del choque
            // Se registran mensajes de depuración para identificar las colisiones
                //Colisiones objetos 3 y 6
                if ((colisionObj3 || colisionObj6) && dx == 1 && dy == 1) {
                    dx = -1;
                    dy = 1;
//                    System.out.println("ENTRADA5");
                } else if ((colisionObj3 || colisionObj6) && dx == -1 && dy == 1) {
                    dx = 1;
                    dy = -1;
//                    System.out.println("ENTRADA6");
                } else if ((colisionObj3 || colisionObj6) && dx == -1 && dy == -1 ) {
                    dx = 1;
                    dy = -1;
//                    System.out.println("ENTRADA7");
                } else if ((colisionObj3 || colisionObj6) && dx == 1 && dy == -1 ) {
                    dx = -1;
                    dy = -1;
//                    System.out.println("ENTRADA8");
                }
            }
                    // Se indica que la pelota está en colisión para evitar múltiples colisiones simultáneas

            enColision = true;
        }
//Colisiones con las raquetas.

//Aqui si puede implementar basicamente la modificacion de  x para evitar colisiones indeseables, en los objetos intente hacer la misma implementacion y no funcionaba bien.

// Manejo de colisiones con las raquetas

    // Se reproducen sonidos y se actualizan los contadores de golpes
    // Se ajusta la posición y dirección de la pelota al colisionar con las raquetas
        if ((colisionR1 || colisionR2) && (Config.corazonesJ1 > 0 && ( Config.corazonesJ2 > 0 || Config.corazonesJ2<=-1))) {
            this.playRaquetSound();
        }
        if (colisionR1) {
            yPelotaGolpe = y;
            tablero.setRafagazo(true);
            golpesR1++;
            dx = 1;
            x = 25;
        }
        if(x > 35){
            tablero.setRafagazo(false);
}

        if (colisionR2) {
            //configuracion mia -----> dx = -1;
            dx = -1;
            x = 740;
        }
        
        //Estas condiciones las agregue para dar mas oportunidades al juego para tratar de no repeir siempre las mismas iteracciones, la pelota cuando rebote cojera la direccion y que nosotros le demos segunen en la direccion que nosotros nos movamos.
        if (colisionR1 && Controles.w) {
            dy = -1;
        }
        if (colisionR1 && Controles.s) {
            dy = 1;
        }
        if (colisionR2 && Controles.up) {
            dy = -1;
        }
        if (colisionR2 && Controles.down) {
            dy = 1;
        }
       
        
     //esta parte es la que modifica las vidas de los jugadores, al llegar a 0 las vidas se acaban y el juego empieza la operacion finalizar.   
        if (x < limites.getMinX()) {
            x = (int) limites.getCenterX();
            y = (int) limites.getCenterY(); 
            if(Config.corazonesJ1>1){
            dañoGolpe();
            }
            Config.corazonesJ1--;

            dx = -dx;
        }

        if (x + ANCHO >= limites.getMaxX()) {

            x = (int) limites.getCenterX();
            y = (int) limites.getCenterY();
            dx = -dx;
            if(!Config.singlePlayer){
                       if(Config.corazonesJ1>1){
            dañoGolpe();
            }
                Config.corazonesJ2--;
            }
            
        }
        //colision con arriba y abajo de la ventana.
        if (y < limites.getMinY()) {
            y = (int) limites.getMinY();
            dy = -dy;
        }
        if (y + ALTO >= limites.getMaxY()) {
            y = (int) (limites.getMaxY() - ALTO);
            dy = -dy;
        }

        x += dx;
        y += dy;

        //Aqui evaluo lo que he dicho antes, cuando la peloa ya no tiene colision, permito que vuelva a  entrar en las iteracciones de colision.
        if (!colisionObj3 && !colisionObj4 && !colisionObj5 && !colisionObj6) {
            enColision = false;
        }
    }
    
  public void dañoGolpe(){
   Audio oooohg = new Audio("sonidos/damage.wav"); // Hace OOOOOOHG. cuando te dan.
            oooohg.play();
  }  

    /**
     * Obtiene la coordenada x de la pelota.
     *
     * @return La coordenada x de la pelota.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Obtiene la coordenada y de la pelota.
     *
     * @return La coordenada y de la pelota.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Obtiene la imagen de la pelota.
     *
     * @return La imagen de la pelota.
     */
    public BufferedImage getImagen() {
        return this.imagen;
    }

    /**
     * Obtiene la velocidad horizontal de la pelota.
     *
     * @return La velocidad horizontal de la pelota.
     */
    public int getDx() {
        return dx;
    }

    /**
     * Obtiene la velocidad vertical de la pelota.
     *
     * @return La velocidad vertical de la pelota.
     */
    public int getDy() {
        return dy;
    }

    public BufferedImage getRafagazo() {
        return rafagazo;
    }

    public int getyPelotaGolpe() {
        return yPelotaGolpe;
    }
    
    

}
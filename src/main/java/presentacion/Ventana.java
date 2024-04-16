/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;


import clases.Controles;

import javax.swing.JFrame;
import menu.ReglasPartida;

/**
 *
 * @author mabos
 */
public class Ventana extends JFrame {

    //Declaración de variables principales de la clase Ventana.
    public final int ANCHO = 800, ALTO = 500;
    private TableroJuego lamina; //Instanciamos el JPanel dentro de JFrame

    public Ventana(ReglasPartida reglas) {
       // Intro introduccion = new Intro(this);
        setTitle("SUPER-PØNG"); //Comando para el titulo de la ventana.  
        setSize(ANCHO, ALTO); //Comando para establecer el tamaño.
        setLocationRelativeTo(null); //ubicando la ventana en el centro de la pantalla.
        setResizable(false); //Prohibimos modificar el tamaño de la pantalla.      
        //add(introduccion); 
        
        lamina = new TableroJuego(this);  //Iniciamos en memoria el Jpanel.
        add(lamina); //Añadimos el JPANEL  dentro de la Ventana JFrame
        addKeyListener(new Controles()); //Comando para iniciar los controles del juego.
        setVisible(true); //Comando para poder ver la ventana.
        
        
        
        //Esto es importante, este metodo es el encargado de instanciar las pelotas, raquetas y obstaculos dentro de tablero. 
        //Si esto se iniciara despues que Hilo, el juego seria inestable porque se generaria todo en posicion 0, o por lo menos la pelota, que me dio problemas incomprensibles hasta que logre llegar a la solucion y al motivo yo solo.
        // durante unas milesimas de segundo modificaba las variables y eventos y al empezar el juego ya habiamos perdido.

        lamina.iniciarTodo();
        
        //Aqui iniciamos el Thread encargado de ejecutar el juego.
        lamina.iniciarHilo();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Comando para cerrar ventana cuando le das click a X.
        reglas.dispose();
    }
    public int getANCHO() {
        return ANCHO;
    }

    public int getALTO() {
        return ALTO;
    }

}

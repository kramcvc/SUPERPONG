/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.awt.event.*;

/**
 * Clase que maneja los eventos de teclado para el control de movimientos en un juego.
 */
public class Controles extends KeyAdapter {

    static boolean w, s, up, down, tab;

    /**
     * Método llamado cuando se presiona una tecla.
     * @param e El evento de teclado asociado.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int id = e.getKeyCode();
        if (id == KeyEvent.VK_W) {
            w = true;
        }
        if (id == KeyEvent.VK_S) {
            s = true;
        }
        if (id == KeyEvent.VK_UP) {
            up = true;
        }
        if (id == KeyEvent.VK_DOWN) {
            down = true;
        }
        if (id == KeyEvent.VK_TAB){
            tab = true;
        }
    }

    /**
     * Método llamado cuando se suelta una tecla.
     * @param e El evento de teclado asociado.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int id = e.getKeyCode();
        if (id == KeyEvent.VK_W) {
            w = false;
        }
        if (id == KeyEvent.VK_S) {
            s = false;
        }
        if (id == KeyEvent.VK_UP) {
            up = false;
        }
        if (id == KeyEvent.VK_DOWN) {
            down = false;
        }
        if (id == KeyEvent.VK_TAB){
            tab = false;
        }
    }
}

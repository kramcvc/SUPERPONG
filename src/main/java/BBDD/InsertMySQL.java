/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BBDD;

import java.sql.Connection;
import propiedades.Config;

/**
 *
 * @author Fernando Flores
 */
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertMySQL {

    public static Connection connexio = null;
    public static String query = "Insert into puntuacion(tempsPartida, nomJugador, puntsTotals) values (?,?,?)";

    public static void insertMySQL() {

        try {
            connexio = Connexio.conn();
            PreparedStatement pr = connexio.prepareStatement(query);
            pr.setInt(1, Config.tiempo);
            pr.setString(2, Config.jugador1);
            pr.setInt(3, Config.puntos);
            pr.executeUpdate();
            System.out.println("Se ha hecho el insert.");
            
        } catch (SQLException e) {
            System.out.println("No se pudo hacer el insert. Error: " + e.getMessage());
        }
    }
}

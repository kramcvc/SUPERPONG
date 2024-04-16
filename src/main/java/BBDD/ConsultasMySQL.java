/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BBDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 *
 * @author Fernando Flores
 */
public class ConsultasMySQL {

    // Declaració de variables.
    private static Connection connexio = null;
    private static Statement st = null;
    private static ResultSet rs = null;

    /**
     * Descripció: Métode per fer consultas sobre la base de dades.
     */
    public static void consulta() {
        try {
            connexio = Connexio.conn();
            st = connexio.createStatement();
            rs = st.executeQuery("""
                                 SSELECT *
                                 FROM puntuacion
                                 ORDER BY puntos DESC;""");
//Fem un while per recorre el registres de la base de dades.
            while (rs.next()) {
                int numeroPartida = rs.getInt(1);
                int tempsPartida = rs.getInt(2);
                String nombrePartida = rs.getString(3);
                int puntosPartida = rs.getInt(4);
                System.out.println("Estadistiques partida: " + "\nNumero partida:" + numeroPartida + "\nTiempo partida: " + tempsPartida + "\nJugador partida: " + nombrePartida + "\nPuntos partida: " + puntosPartida);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se puede connectar. Error: " + e);
        }
    }
}

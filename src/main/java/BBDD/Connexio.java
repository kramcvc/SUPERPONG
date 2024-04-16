/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Fernando Flores
 */
public class Connexio {
    public static Connection conn = null;

    // Librería de MySQL
    public static String driver = "com.mysql.cj.jdbc.Driver";

    // Nombre de la base de datos
    public static String database = "estadistiques_abp";

    // Host
    public static String hostname = "172.19.1.3";
    
    // Puerto
    public static String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    public static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?allowPublicKeyRetrieval=true";

    // Nombre de usuario
    public static String username = "usuario_RetroGame";

    // Clave de usuario
    public static String password = "root";
    /**
     * Descripció: Métode per enllaçar la base de dades amb al nostre joc.
     * @return Connexio MySQL   
     */
    public static Connection conn() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) { //Catch en cas que la connexió falle
            //Mostrem el missatje d'error.
            e.printStackTrace();
            System.out.println("No se puede connectar. Error: " + e);
        }
        return conn; //Returnem la connexió.
    }
}

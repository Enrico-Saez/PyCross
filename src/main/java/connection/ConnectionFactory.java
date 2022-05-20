/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Enrico
 */
public class ConnectionFactory {

    private final static String DRIVER = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/db_pycross";
    private final static String USER = "root";
    private final static String PASS = "trex2004";

    public static Connection getConnection() {
        try {
            Connection c = DriverManager.getConnection(URL, USER, PASS);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

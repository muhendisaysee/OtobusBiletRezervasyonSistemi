/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ayse
 */
public class DBConnection {

    private final String url = "jdbc:postgresql://localhost:5432/OtobusBiletRezervasyonSistemi";
    private final String user = "postgres";
    private final String password = "123";
    
    private static DBConnection dBConnection;
    private static int sayi = 0;

    private DBConnection() {

        System.out.println("Ben oluştum.");
    }

    public static DBConnection getDBConnection() {
        if (dBConnection == null) {
            synchronized (DBConnection.class) {
                if (dBConnection == null) {
                    dBConnection = new DBConnection();
             
                }
            }
        }
        sayi++;
        //System.out.println("Sayi : " + sayi);
        return dBConnection;

    }

    public Connection connect() {
        Connection con = null;
        try {
           
            
            con = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            System.out.println("Veri tabanına bağlanılamadı");
            System.out.println(e.getMessage());
        }
        return con;
    }
    
}

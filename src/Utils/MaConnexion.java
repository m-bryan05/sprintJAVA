/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author user
 */
public class MaConnexion {
     //DB CREDENTIALS
    final static String URL = "jdbc:mysql://127.0.0.1:3306/test";
    final static String USERNAME = "root";
    final static String PWD = "";
    
    //Connection init
    static MaConnexion instance = null;
    private Connection cnx;
    
    //constructor
    private MaConnexion() {
        
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PWD);
            System.out.println("Connexion avec succes");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    //getters
    public static MaConnexion getInstance() {
        if (instance == null) {
            instance = new MaConnexion();
        }
        
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
    
    
    
}

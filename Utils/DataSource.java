/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entite.Produits;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Yassine Karoui
 */
public class DataSource {
    final static String URL = "jdbc:mysql://localhost:3306/SPORTify";
    final static String LOGIN = "root";
    final static String PWD = "";
    private Connection cnx;
    private static DataSource instance;
    private DataSource(){
        try {
            cnx = DriverManager.getConnection(URL, LOGIN,PWD);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    public static DataSource getInstance(){
        if (instance == null){
            instance = new DataSource();
        }
        return instance;
    }
    public Connection getCnx(){
        return cnx;
    }
    
    
     
}
    

   
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Cours;
import Entite.Utilisateur;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class ServiceClients {
    Connection cnx = DataSource.getInstance().getCnx();;
      Statement stm;
    
    
     public  List<Utilisateur> afficherClients() throws SQLException{
        ArrayList<Utilisateur> listClients = new ArrayList<>();
        String req = "SELECT * FROM `utilisateur` WHERE `role` = 'client'";
        stm = cnx.createStatement();
        ResultSet result = stm.executeQuery(req);
        while (result.next()) {
            listClients.add(new Utilisateur(result.getInt(1), result.getString(2), result.getString(3),
                    result.getString(4), result.getString(5), result.getString(6), result.getString(7),
                    result.getString(8), result.getString(9), result.getString(10)));
        }
        
        return listClients;
   }

     public Utilisateur rechercherClient(String surnom) throws SQLException {
        Utilisateur u = null;
        String req = "SELECT * FROM `utilisateur` WHERE ((`surnom` = '" + surnom + "') && (`role` = 'client'))";
        stm = cnx.createStatement();
        ResultSet result = stm.executeQuery(req);
            while (result.next()) {
            u = new Utilisateur(result.getInt(1), result.getString(2), result.getString(3),
                    result.getString(4), result.getString(5), result.getString(6), result.getString(7),
                    result.getString(8), result.getString(9), result.getString(10));
        }
        if (u == null){
            System.out.println("Client introuvable");
        } 
        return u;  
    }
     
    public void reserverCours (Cours c) throws SQLException {
         Cours c1 = null;
    String req0 = " SELECT `ID_Cours`, `Place_Disponible` FROM `cours` WHERE `ID_Cours` = '" +c.getID_Cours() +"' ";
          stm = cnx.createStatement();
          ResultSet result0 = stm.executeQuery(req0);
      while (result0.next()){
          c1 = new Cours(result0.getString(1),result0.getInt(2));
         
           if (c1.getPlace_Disponible() > 0) {
       
       String req = "Update `cours`  set `Place_Disponible` = '" + (c1.getPlace_Disponible()-1 )+"'WHERE `ID_Cours` = '" +c.getID_Cours() +"' ";
          stm = cnx.createStatement();
          stm.executeUpdate(req);
          	
           }else {
		System.out.println("Pas de Place dans ce cours");
	}
      }
        
         
          
         
        
         
     }
      
     
     
}

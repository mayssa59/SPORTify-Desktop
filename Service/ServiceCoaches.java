/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

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
public class ServiceCoaches {
    
      Connection cnx = DataSource.getInstance().getCnx();;
    Statement stm;
    
    
     public Utilisateur rechercherCoach(String surnom) throws SQLException {
        Utilisateur u = null;
        String req = "SELECT * FROM `utilisateur` WHERE ((`surnom` = '" + surnom + "') && (`role` = 'coach'))";
        stm = cnx.createStatement();
        ResultSet result = stm.executeQuery(req);
        
            while (result.next()) {
            u = new Utilisateur(result.getInt(1), result.getString(2), result.getString(3),result.getString(4), result.getString(5), result.getString(6), result.getString(7),result.getString(8), result.getString(9), result.getString(10), result.getInt(11),result.getFloat(12));
                    
                    
        }
        if (u == null){
            System.out.println("Coach introuvable");
        } 
        return u;  
    }
     
      public  List<Utilisateur> afficherCoaches() throws SQLException{
        ArrayList<Utilisateur> listCoach = new ArrayList<>();
        String req = "SELECT * FROM `utilisateur` WHERE `role` = 'coach'";
        stm = cnx.createStatement();
        ResultSet result = stm.executeQuery(req);
        while (result.next()) {
            Utilisateur user = new Utilisateur(result.getInt(1), result.getString(2), result.getString(3),
                    result.getString(4), result.getString(5), result.getString(6), result.getString(7),
                    result.getString(8), result.getString(9), result.getString(10),result.getInt(11), (int) result.getFloat(12));
                    user.setEvaluationCoach(result.getFloat("evaluationCoach"));
                    
            listCoach.add(user);
        }
        
        return listCoach;
   }
      
     
        

  public void evaluerCoach(Utilisateur u, int note) throws SQLException{
        Utilisateur utilisateur = null;
        String req = "SELECT * FROM utilisateur WHERE (surnom = '" + u.getSurnom() + "' && role = 'coach')";
        stm = cnx.createStatement();
        ResultSet result = stm.executeQuery(req);  
        while (result.next()) {
            utilisateur = new Utilisateur(result.getString(2), result.getString(3),
                    result.getString(4), result.getString(5), result.getString(6), result.getString(7),
                    result.getString(8), result.getString(9), result.getString(10), result.getInt(11), result.getFloat(12));
        }
        System.out.println(utilisateur);
        if (utilisateur.getNbrEvaluation() != 0)
        {
            float  somme = (utilisateur.getNbrEvaluation() * utilisateur.getEvaluationCoach());
            int nbr = utilisateur.getNbrEvaluation()+1;
            float res = (note + somme)/ nbr ;
            String req1 = "Update utilisateur  set nbrEvaluation = '" + nbr+ "', evaluationCoach = '"+ res +"' WHERE surnom = '" +u.getSurnom() +"' ";
            stm = cnx.createStatement();
            stm.executeUpdate(req1);
         
        }else {
            String req2 = "Update utilisateur  set nbrEvaluation = '" +1+ "', evaluationCoach = '"+ note +"' WHERE surnom = '" +u.getSurnom() +"' ";
            stm = cnx.createStatement();
            stm.executeUpdate(req2);
             
         
         }
         

     }
     
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import java.sql.Statement;
import java.sql.ResultSet;
import Entite.Utilisateur;
import Entite.Cours;
import Interface.IService1;
import Utils.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class ServiceCoachDansCours implements IService1 {
    
    Connection cnx = DataSource.getInstance().getCnx();;
    Statement stm, stm1;

    @Override
    public void ajouterCoachDansCours(Cours c, Utilisateur u) throws SQLException {
        
        
        if(u.getRole().equals("coach")){
            String req = "Update `cours`  set `MailCoach` ='" + u.getEmail() +"' WHERE `ID_Cours` = '"+c.getID_Cours()+"'"; 
            stm = cnx.createStatement();
            stm.executeUpdate(req);
            }else{
          System.out.println("l'utilisateur n est pas un coach");
        }
        
    }
    
    


    @Override
    public void supprimerCoachDeCours(Cours c) throws SQLException {
         
            String req = "Update `cours`  set `MailCoach` = NULL WHERE `ID_Cours` = '"+c.getID_Cours()+"'"; 
            stm = cnx.createStatement();
            stm.executeUpdate(req);
            
        
        }

    @Override
    public void modifierCoachDansCours(Cours c, Utilisateur u) throws SQLException {
        if(u.getRole().equals("coach")){
            String req1 = "Update `cours`  set `MailCoach` ='" + u.getEmail() +"' WHERE `ID_Cours` = '"+c.getID_Cours()+"'"; 
            stm = cnx.createStatement();
            stm.executeUpdate(req1);
        }else{
          System.out.println("l'utilisateur n est pas un coach");
        }
    }


     public  List<Cours> afficherCours() throws SQLException{
         System.out.println("inside affichercours");
        ArrayList<Cours> listCours = new ArrayList<>();
        String req = "SELECT * FROM `cours`";
        stm = cnx.createStatement();
        ResultSet result = stm.executeQuery(req);
        while (result.next()) {
            if (result.getString("MailCoach")==null) {
                Cours cours = new Cours(result.getString("ID_Cours"),
                                        result.getString("Type_Cours"), 
                                        result.getDate("Date"),
                                        result.getFloat("Heure"), 
                                        result.getInt("Duree"), 
                                        result.getInt("Place_Disponible"));
                cours.setMailCoach("pas de coach pour ce cours");
                listCours.add(cours);
                
            }else{
                Cours cours = new Cours(result.getString("ID_Cours"),
                                        result.getString("Type_Cours"), 
                                        result.getDate("Date"),
                                        result.getFloat("Heure"), 
                                        result.getInt("Duree"), 
                                        result.getInt("Place_Disponible"));
                cours.setMailCoach(result.getString("MailCoach"));
                listCours.add(cours);

            }
        }   
        
        return listCours;
    }

   
    
}
            
         
     
     
     
    

    

   

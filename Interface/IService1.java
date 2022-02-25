/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entite.*;
import java.sql.*;
import java.util.List;


/**
 *
 * @author asus
 * 
 * 
 */
public interface IService1 {
    void ajouterCoachDansCours (Cours c, Utilisateur u) throws SQLException;
    void supprimerCoachDeCours (Cours c) throws SQLException;
    void modifierCoachDansCours (Cours c, Utilisateur u) throws SQLException;
   
    
    
}





  
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entite.Utilisateur;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Yassine Karoui
 */
public interface IServiceAffichage {
    
        public ArrayList<Utilisateur> getUsers() throws SQLException ;
        
        public ArrayList<Utilisateur> getUsersParRole(String role) throws SQLException ;
        
        public Utilisateur getUser(String surnom) throws SQLException ;

}

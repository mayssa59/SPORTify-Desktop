/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entite.Administrateur;
import Entite.Utilisateur;
import java.sql.SQLException;

/**
 *
 * @author Yassine Karoui
 */
public interface IServiceAdministrateur {

    public int ajouterAdmin(String email, String password) throws SQLException;

    public int authentification(String email, String password) throws SQLException;
    
    public void supprimerUser(String surnom) throws SQLException;
    
}

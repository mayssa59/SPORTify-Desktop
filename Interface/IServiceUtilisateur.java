/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entite.Utilisateur;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Yassine Karoui
 */
public interface IServiceUtilisateur {

    public int inscription(String surnom, String prenom, String nom, String numeroDeTelephone, String email,
            String password, String dateDeNaissance, String sexe, String role) throws SQLException;
    
    public void modifierUtilisateur(String surnom, Utilisateur u) throws SQLException;
    
    public void modifierUtilisateur(String surnom, String mdp) throws SQLException;

    public int authentification(String surnom, String password) throws SQLException;
    
}

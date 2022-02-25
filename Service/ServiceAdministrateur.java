/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Interface.IServiceAdministrateur;
import Utils.CryptDecrypt;
import Utils.DataSource;
import Utils.EmailValide;
import java.sql.*;


/**
 *
 * @author Yassine Karoui
 */
public class ServiceAdministrateur implements IServiceAdministrateur {
        private final Connection cnx = DataSource.getInstance().getCnx();
        private Statement ste;
        private ResultSet result;
        private Statement ste0;

    @Override
    public int ajouterAdmin(String email, String password) throws SQLException {
        String req0 = "SELECT `Email_admin` FROM `administrateur`";
        ste = cnx.createStatement();
        result = ste.executeQuery(req0);
        while (result.next()){
            if (result.getString(1).equals(email)){
                System.out.println("Email deja existant");
                return 2;
            }
        }
        if (EmailValide.verifierEmail(email)){
            String req = "INSERT INTO `administrateur`(`Email_admin`, `MDP_admin`) VALUES ('"+ email+"','"+CryptDecrypt.encrypt(password)+"')";
            ste0 = cnx.createStatement();
            ste0.executeUpdate(req);
            System.out.println("Admin inscrit");
            return 1;
        } else{
            System.out.println("Email invalide.\nAdmin non inscrit.");
        }     
            return 0;
        
    }

    @Override
    public int authentification(String email, String password) throws SQLException {
       int test = 0;
        String req = "SELECT *  FROM `administrateur`";
        ste = cnx.createStatement();
        result = ste.executeQuery(req);
        while (result.next() && test == 0) {
            if ((result.getString("Email_admin").equals(email))
                    && CryptDecrypt.decrypt(result.getString("MDP_admin")).equals(password)) {
                test = result.getInt(1);
                System.out.println("Administrateur connecté.\nL'id de l'admin est = "+test);
                return test;
            } 
        }
        return test;
    }

    @Override
    public void supprimerUser(String surnom) throws SQLException {
        try{
            String req = "DELETE FROM `utilisateur` WHERE `surnom` = '"+ surnom + "'";
            ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("Utilisateur supprimer.");
        } catch (SQLException e){
            System.out.println(e);
        } 
    }
}
    

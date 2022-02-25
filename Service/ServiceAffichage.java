/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Utilisateur;
import Interface.IServiceAffichage;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Yassine Karoui
 */
public class ServiceAffichage implements IServiceAffichage{
    private final Connection cnx = DataSource.getInstance().getCnx();
    private Statement ste;
    private ResultSet result;

    @Override
    public ArrayList<Utilisateur> getUsers() throws SQLException {
         ArrayList<Utilisateur> listUser = new ArrayList<>();
        String req = "SELECT * FROM `utilisateur`";
        ste = cnx.createStatement();
        result = ste.executeQuery(req);
        while (result.next()) {
            listUser.add(new Utilisateur(result.getInt(1), result.getString(2), result.getString(3),
                    result.getString(4), result.getString(5), result.getString(6), result.getString(7),
                    result.getString(8), result.getString(9), result.getString(10)));
        }
        return listUser;
    }

    @Override
    public ArrayList<Utilisateur> getUsersParRole(String role) throws SQLException {
        ArrayList<Utilisateur> listUser = new ArrayList<>();
        String req = "SELECT * FROM `utilisateur` WHERE `role` = '" + role + "'";
        ste = cnx.createStatement();
        result = ste.executeQuery(req);
        while (result.next()) {
            listUser.add(new Utilisateur(result.getInt(1), result.getString(2), result.getString(3),
                    result.getString(4), result.getString(5), result.getString(6), result.getString(7),
                    result.getString(8), result.getString(9), result.getString(10)));
        }
        return listUser;
    }

    @Override
    public Utilisateur getUser(String surnom) throws SQLException {
        Utilisateur u = null;
        String req = "SELECT * FROM `utilisateur` WHERE `surnom` = '" + surnom + "'";
        ste = cnx.createStatement();
        result = ste.executeQuery(req);
        while (result.next()) {
            u = new Utilisateur(result.getInt(1), result.getString(2), result.getString(3),
                    result.getString(4), result.getString(5), result.getString(6), result.getString(7),
                    result.getString(8), result.getString(9), result.getString(10));
        }
        return u;  
    }
    
}

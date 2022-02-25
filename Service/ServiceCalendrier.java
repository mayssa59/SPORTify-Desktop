/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Entite.Calendrier;
import Interface.IserviceCalendrier;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class ServiceCalendrier implements IserviceCalendrier<Calendrier> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void AjouterCoursDansCalendrier(Calendrier cal) throws SQLException {
        
        
        try {
            String requete = "INSERT INTO calendrier VALUES (?,?,CONVERT(?, DATE),?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, cal.getID_Cours());
            pst.setString(2, cal.getType_Cours());
            pst.setString(3, cal.getDate());
            pst.setString(4, cal.getHeure());

            pst.executeUpdate();
            System.out.println("Cours ajoutée dans le calendrier !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerCoursDansCalendrier(Calendrier cal) throws SQLException {
        try {
            String requete = "DELETE FROM calendrier WHERE ID_Cours=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, cal.getID_Cours());
            pst.executeUpdate();
            System.out.println("Cours supprimée dans le calendrier !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifierCoursDansCalendrier(Calendrier cal) throws SQLException {
        try {
            String requete = "UPDATE calendrier SET Type_Cours=?,Heure=?,Date=? WHERE ID_Cours=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(4, cal.getID_Cours());
            pst.setString(1, cal.getType_Cours());
            pst.setString(2, cal.getHeure());
            pst.setString(3, cal.getDate());
            pst.executeUpdate();
            System.out.println("Calendrier modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Calendrier> afficherCalendrier() throws SQLException {
        List<Calendrier> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM calendrier";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Calendrier(rs.getString(2), rs.getString(3), rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public List<Calendrier> rechercherCoursDansCalendrier(String cours) throws SQLException {
        List<Calendrier> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM calendrier where Type_Cours= ? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, cours);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               list.add(new Calendrier(rs.getString(2), rs.getString(3), rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

}
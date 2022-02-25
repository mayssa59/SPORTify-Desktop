/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Entite.Reclamation;
import Interface.IServices;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Houssem
 */
public class ServiceReclamation implements IServices<Reclamation> {

Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouterReclamation(Reclamation rec) {
        try {
            String requete = "INSERT INTO reclamation (ref,objet,MSG) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, rec.getRef());
            pst.setString(2, rec.getObjet());
            pst.setString(3, rec.getMSG());
            pst.executeUpdate();
            System.out.println("votre reclamation a ete envoyee !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerReclamation(Reclamation rec) {
        try {
            String requete = "DELETE FROM reclamation WHERE ref=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, rec.getRef());
            pst.executeUpdate();
            System.out.println("reclamation supprimee !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }    
    
    /**
     *
     * @param rec
     */
    @Override
    public void modifierReclamation(Reclamation rec) {
        try {
            String requete = "UPDATE reclamation SET  Objet=? , MSG=? WHERE ref=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(3, rec.getRef());
            pst.setString(1, rec.getObjet());
            pst.setString(2, rec.getMSG());
            pst.executeUpdate();
            System.out.println("Reclamation modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
@Override
    public List<Reclamation> afficherReclamation() {
        List<Reclamation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM reclamation";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    @Override
    public List<Reclamation> rechercherReclamation() {
        List<Reclamation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM reclamation where Objet='important'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    
}

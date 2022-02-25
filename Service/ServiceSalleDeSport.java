/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.SalleDeSport;
import Interface.IService;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yassine Karoui
 */
public class ServiceSalleDeSport implements IService<SalleDeSport>{
   Connection cnx = DataSource.getInstance().getCnx();

   
    @Override
    public void ajouter(SalleDeSport t) {
        try {
            String requete = "INSERT INTO SalleDeSport (ID_Salle,Nom_Salle,Adresse,Region,HDebut,HFin,Min,NumTel) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            if((t.getHDebut()>=0)&&(t.getHDebut()<24)&&(t.getHFin()>0)&&(t.getHFin()<24)&&(t.getMin()>=0)&&(t.getMin()<60)){
            pst.setString(1, t.getIdSalle());
            pst.setString(2, t.getNomSalle());
            pst.setString(3, t.getAdresse());
            pst.setString(4, t.getRegion());
            pst.setInt(5, t.getHDebut());
            pst.setInt(6, t.getHFin());
            pst.setInt(7, t.getMin());
            pst.setInt(8, t.getNumTel());
            pst.executeUpdate();
            System.out.println("Salle de sport ajoutée !");
        }else
                System.out.println("1.L'Heure Doit Etre Entre 00h et 23h\n2.Les minutes Doit Etre Entre 00min et 60min.\nVeuillez Ressaisir.");
            

        } catch (SQLException ex) {
            
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(String t) {
        try {
            String requete = "DELETE FROM SalleDeSport WHERE Nom_Salle=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t);
            pst.executeUpdate();
            System.out.println("Salle de sport supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

   
    
    @Override
    public void modifier(SalleDeSport t) {
        try {
      
            
            String req = "UPDATE SalleDeSport SET ID_Salle='" + t.getIdSalle() + "',Adresse='" + t.getAdresse() + "',Region='" +t.getRegion() + "',HDebut='" + t.getHDebut()+ "',HFin='" + t.getHFin() +"',Min='" + t.getMin() +"',NumTel='" + t.getNumTel() +"' WHERE Nom_Salle ='"+t.getNomSalle()+"'";
            Statement st1 = cnx.createStatement();
            st1.executeUpdate(req);
            System.out.println("Salle de sport modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

  
    @Override
    public List<SalleDeSport> afficher() {
        List<SalleDeSport> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM SalleDeSport";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            System.out.println("La Liste Des Salles de sport :\n");
            while (rs.next()) {
                list.add(new SalleDeSport(rs.getString("ID_Salle"), rs.getString("Nom_Salle"), rs.getString("Adresse"),rs.getString("Region"),rs.getInt("HDebut"),rs.getInt("HFin"),rs.getInt("Min"),rs.getInt("NumTel")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    public List<String> afficherALLNom() {
        List<String> list = new ArrayList<>();

        try {
            String requete = "SELECT Nom_Salle FROM SalleDeSport";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            System.out.println("La Liste Des noms :\n");
            while (rs.next()) {
                list.add(rs.getString("Nom_Salle"));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
   public  List<SalleDeSport> rechercher(String t){
        ResultSet rst;
          List<SalleDeSport> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM SalleDeSport WHERE Region LIKE '" + t+ "' OR Nom_Salle LIKE '" + t+ "' ";
            Statement st = cnx.createStatement();
            rst= st.executeQuery(requete); 
            
            while(rst.next()){
                list.add(new SalleDeSport(rst.getString("ID_Salle"), rst.getString("Nom_Salle"), rst.getString("Adresse"),rst.getString("Region"),rst.getInt("HDebut"),rst.getInt("HFin"),rst.getInt("Min"),rst.getInt("NumTel")));
            }
            int size = list.size();
             if(size!=0){
                 System.out.println("Salle de sport Retrouvée"); 
            }else 
                       System.out.println("Salle de sport Non Retrouvée");
           
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
         return list;
   }
     public SalleDeSport rechercherID(String t){
        ResultSet rst;
        SalleDeSport ss=new SalleDeSport();
         
        try {
            String requete = "SELECT * FROM SalleDeSport WHERE Region LIKE '" + t+ "' OR Nom_Salle LIKE '" + t+ "' ";
            Statement st = cnx.createStatement();
            rst= st.executeQuery(requete); 
            
            while(rst.next()){
              ss=new SalleDeSport(rst.getString("ID_Salle"), rst.getString("Nom_Salle"), rst.getString("Adresse"),rst.getString("Region"),rst.getInt("HDebut"),rst.getInt("HFin"),rst.getInt("Min"),rst.getInt("NumTel"));
            }
         
             
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
         return ss;
   }
       public List<SalleDeSport> TrierRegion() {
        List<SalleDeSport> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM SalleDeSport ORDER BY Region ASC";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new SalleDeSport(rs.getString("ID_Salle"), rs.getString("Nom_Salle"), rs.getString("Adresse"),rs.getString("Region"),rs.getInt("HDebut"),rs.getInt("HFin"),rs.getInt("Min"),rs.getInt("NumTel")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("Les Cours Trier Par Région"); 

        return list;
    }
         public List<SalleDeSport> TrierNom() {
        List<SalleDeSport> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM SalleDeSport ORDER BY Nom_Salle ASC";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                 list.add(new SalleDeSport(rs.getString("ID_Salle"), rs.getString("Nom_Salle"), rs.getString("Adresse"),rs.getString("Region"),rs.getInt("HDebut"),rs.getInt("HFin"),rs.getInt("Min"),rs.getInt("NumTel")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         System.out.println("Les Cours Trier Par Nom"); 
        return list;
    }
         public int nombre() {

        int i = 0;
        String requete = "SELECT COUNT(*) as nbr FROM SalleDeSport";

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {

                i = rs.getInt("nbr");

            }
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
        return i;
    } 
         public SalleDeSport getAdresse(int i) {
        SalleDeSport salle = null;
        int nombre = 0;
        String requete = "SELECT * FROM `SalleDeSport`";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                if (i == nombre) {
                    salle= new SalleDeSport(rs.getString("ID_Salle"), rs.getString("Nom_Salle"), rs.getString("Adresse"),rs.getString("Region"),rs.getInt("HDebut"),rs.getInt("HFin"),rs.getInt("Min"),rs.getInt("NumTel"));

                   
                }
                nombre++;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return salle;

    }
}

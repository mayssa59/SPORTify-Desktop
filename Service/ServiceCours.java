/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Cours;
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
public class ServiceCours implements IService<Cours>{
     Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Cours t) {
        try {
            String requete = "INSERT INTO Cours (ID_Cours,Type_Cours,Date,Heure,Duree,MailCoach,Place_Disponible) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            if((t.getHeure()>=0)&&(t.getHeure()<24)&&(t.getDuree()>0)&&(t.getDuree()<240)){
            pst.setString(1, t.getIdCours());
            pst.setString(2, t.getTypeCours());
            pst.setDate(3, t.getDate());
            pst.setFloat(4, t.getHeure());
            pst.setInt(5, t.getDuree());
            pst.setString(6, t.getMailCoach());
             pst.setInt(7, t.getPlace_Disponible());
            pst.executeUpdate();
            System.out.println("Cours ajoutée !");
        }else
                System.out.println("1.L'Heure Doit Etre Entre 00h et 23h\n2.La Durée Ne Doit Pas Depasser 4 Heures Pour Un Cours.\nVeuillez Ressaisir.");
            

        } catch (SQLException ex) {
            
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(String t) {
        try {
            String requete = "DELETE FROM Cours WHERE ID_Cours=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t);
            pst.executeUpdate();
            System.out.println("Cours supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

   
    @Override
public void modifier(Cours t) {
        try {
      
            
            String req = "UPDATE Cours SET Type_Cours='" + t.getTypeCours() + "',Date='" + t.getDate() + "',Heure='" +t.getHeure() + "',Duree='" + t.getDuree()+ "',MailCoach='" + t.getMailCoach() +"',Place_Disponible='" + t.getPlace_Disponible() +"' WHERE ID_Cours ='"+t.getIdCours()+"'";
            Statement st1 = cnx.createStatement();
            st1.executeUpdate(req);
            System.out.println("Cours modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Cours> afficher() {
        List<Cours> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM Cours";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            System.out.println("La Liste Des Cours :\n");
            while (rs.next()) {
                list.add(new Cours(rs.getString("ID_Cours"), rs.getString("Type_Cours"), rs.getDate("Date"),rs.getInt("Heure"),rs.getInt("Duree"),rs.getString("MailCoach"),rs.getInt("Place_Disponible")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    public List<String> afficherALLCoach() {
        List<String> list = new ArrayList<>();

        try {
            String requete = "SELECT email FROM utilisateur";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            System.out.println("La Liste Des Coach :\n");
            while (rs.next()) {
                list.add(rs.getString("email"));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    public List<String> afficherALLId() {
        List<String> list = new ArrayList<>();

        try {
            String requete = "SELECT ID_Cours FROM Cours";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            System.out.println("La Liste Des ID :\n");
            while (rs.next()) {
                list.add(rs.getString("ID_Cours"));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
   public  List<Cours> rechercher(String t){
        ResultSet rst;
          List<Cours> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM Cours WHERE Type_Cours LIKE '" + t+ "' OR ID_Cours LIKE '" + t+ "'OR MailCoach LIKE '" + t+ "' ";
            Statement st = cnx.createStatement();
            rst= st.executeQuery(requete); 
            
            while(rst.next()){
                list.add(new Cours(rst.getString("ID_Cours"), rst.getString("Type_Cours"), rst.getDate("Date"),rst.getInt("Heure"),rst.getInt("Duree"),rst.getString("MailCoach"),rst.getInt("Place_Disponible")));
            }
            int size = list.size();
             if(size!=0){
                 System.out.println("Cours Retrouvée"); 
            }else 
                       System.out.println("Cours Non Retrouvée");
           
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
         return list;
   }
     public Cours rechercherID(String t){
        ResultSet rst;
        Cours cc=new Cours();
         
        try {
            String requete = "SELECT * FROM Cours WHERE Type_Cours LIKE '" + t+ "' OR ID_Cours LIKE '" + t+ "'OR MailCoach LIKE '" + t+ "' ";
            Statement st = cnx.createStatement();
            rst= st.executeQuery(requete); 
            
            while(rst.next()){
              cc=new Cours(rst.getString("ID_Cours"), rst.getString("Type_Cours"), rst.getDate("Date"),rst.getInt("Heure"),rst.getInt("Duree"),rst.getString("MailCoach"),rst.getInt("Place_Disponible"));
            }
         
             
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
         return cc;
   }
       public List<Cours> TrierDate() {
        List<Cours> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM Cours ORDER BY Date ASC";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Cours(rs.getString("ID_Cours"), rs.getString("Type_Cours"), rs.getDate("Date"),rs.getInt("Heure"),rs.getInt("Duree"),rs.getString("MailCoach"),rs.getInt("Place_Disponible")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("Les Cours Trier Par Date"); 

        return list;
    }
         public List<Cours> TrierType() {
        List<Cours> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM Cours ORDER BY Type_Cours ASC";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                 list.add(new Cours(rs.getString("ID_Cours"), rs.getString("Type_Cours"), rs.getDate("Date"),rs.getInt("Heure"),rs.getInt("Duree"),rs.getString("MailCoach"),rs.getInt("Place_Disponible")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         System.out.println("Les Cours Trier Par Type"); 
        return list;
    }
         public int nombre() {

        int i = 0;
        String requete = "SELECT COUNT(*) as nbr FROM cours";

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
}


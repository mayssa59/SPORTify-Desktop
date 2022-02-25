/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Produits;
import Entite.SalleDeSport;
import Interface.Iproduits;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Yassine Karoui
 */
public class ProduitsCrud implements Iproduits<Produits>{
   Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouterProduits(Produits p) {
         try {
            String requete = "INSERT INTO produits (id,libelle,type,quantites,prix) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            if((p.getQuantites()>=0)&&(p.getPrix()>=0)){
            pst.setInt(1, p.getId());
            pst.setString(2,  p.getLibelle());
            pst.setString(3, p.getType());
            pst.setInt(4, p.getQuantites());
            pst.setInt(5, p.getPrix());
            
            pst.executeUpdate();
            System.out.println("Produit ajouté !");
        }else
                System.out.println("quantité et prix > 0");
            

        } catch (SQLException ex) {
            
            System.err.println(ex.getMessage());
        }
        
        
        
        
        
    }

   

    @Override
    public void modifierProduits(Produits t) {
        try {
      
            
            String req = "UPDATE produits SET id='" + t.getId() + "',type='" +t.getType() + "',quantites='" + t.getQuantites()+ "',prix='" + t.getPrix() +"' WHERE libelle ='"+t.getLibelle()+"'";
            Statement st1 = cnx.createStatement();
            st1.executeUpdate(req);
            System.out.println("Produit modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        

        
        
    }

    @Override
    public List<Produits> afficherProduits() {
        List<Produits> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM Produits";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            System.out.println("La Liste Des produits :\n");
            while (rs.next()) {
                list.add(new Produits(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5)));

            

        }
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }

        return list;
 
    
    
    }
    public List<String> afficherALLLibelle() {
        List<String> list = new ArrayList<>();

        try {
            String requete = "SELECT libelle FROM Produits";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            System.out.println("La Liste Des Libelles :\n");
            while (rs.next()) {
                list.add(rs.getString("libelle"));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
     
     @Override
    public void supprimerProduits(String p) {
        
         try {
            String requete = "DELETE FROM produits where libelle=?";
            PreparedStatement pst = DataSource.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, p);
            pst.executeUpdate();
            System.out.println("Produit supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        
        
    }
  public Produits rechercherLibelle(String t){
        ResultSet rst;
        Produits p = new Produits();
         
        try {
            String requete = "SELECT * FROM produits WHERE libelle='" + t + "'";
            Statement st = cnx.createStatement();
            rst= st.executeQuery(requete); 
            
            while(rst.next()){
              p =new Produits(rst.getInt("id"), rst.getString("libelle"), rst.getString("type"),rst.getInt("quantites"),rst.getInt("prix"));
            }
         
             
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
         return p;
   }
      
      
    @Override
    public ArrayList<Produits> rechercherProduitByid(int id ) {
       ArrayList<Produits> listProduits = new ArrayList<>();   
   try {
          String requete= "select * from produits WHERE id='" + id + "' ";

        PreparedStatement pst = DataSource.getInstance().getCnx() .prepareStatement(requete);;                

        ResultSet res = pst.executeQuery("select * from produits   WHERE id='" + id + "' ");
        Produits com = null;
        while (res.next()) {
              

            com = new Produits(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5));

            listProduits.add(com);
            
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
                 return listProduits ;
 
    }

    @Override
    public ArrayList<Produits> TrierParprix() {
         ArrayList<Produits> listProduits = new ArrayList<>();
       try {
           String requete= "select * from produits ORDER BY prix"; 
           PreparedStatement pst = DataSource.getInstance().getCnx() .prepareStatement(requete);;
           ResultSet res = pst.executeQuery("select * from produits ORDER BY prix DESC");
            Produits com = null;
            while (res.next()) {
            com = new Produits(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5));

            listProduits.add(com);
        }
         } catch (SQLException ex) {
            Logger.getLogger(ProduitsCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
                 return listProduits ;
    
      
    }

    public int deleteProduits(int id) throws SQLException {
        int i = 0;
        try {
            Statement ste = cnx.createStatement();
            String sql = "delete from produits where id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    
  
        
     
     public static ObservableList<Produits> getDataProduits(){
        Connection cnx = DataSource.getInstance().getCnx();
        
        ObservableList<Produits> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = cnx.prepareStatement("select * from produits");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Produits(rs.getInt("id") ,rs.getString("libelle"), rs.getString("type"),rs.getInt("quantites"),rs.getInt("prix")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
   
    
    
}

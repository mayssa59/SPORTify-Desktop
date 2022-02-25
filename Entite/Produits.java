/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author Yassine Karoui
 */
public class Produits {
    private int id ; 
      private String libelle ;
      private String type   ;
      private int quantites ;
      private int prix ; 

    public Produits() {
    }

    public Produits(int id) {
        this.id = id;
    }
      
       public Produits(String libelle, String type, int quantites, int prix) {
        this.libelle = libelle;
        this.type = type;
        this.quantites = quantites;
        this.prix = prix;
    }


    public Produits(int id, String libelle, String type, int quantites , int prix ) {
        this.id = id;
        this.libelle = libelle;
        this.type = type;
        this.quantites = quantites;
        this.prix=prix;
        
      
    }

   
   

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getQuantites() {
        return quantites;
    }

    public String getType() {
        return type;
    }

    public int getPrix() {
        return prix;
    }
    

    

    public void setId(int id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setQuantites(int quantites) {
        this.quantites = quantites;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produits{" + "id=" + id + ", libelle=" + libelle + ", type=" + type + ", quantites=" + quantites + ", prix=" + prix + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produits other = (Produits) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}

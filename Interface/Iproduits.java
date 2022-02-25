/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entite.Produits;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yassine Karoui
 */
public interface Iproduits<P> {
     public void ajouterProduits(P p);
     public void supprimerProduits(String p);
     public void modifierProduits(P p);
     public List<P> afficherProduits();
     public ArrayList<Produits> rechercherProduitByid(int id);
     public ArrayList<Produits> TrierParprix();
}

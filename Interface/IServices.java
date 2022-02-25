/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;
import java.util.List;
import javafx.event.ActionEvent;

/**
 *
 * @author Houssem
 * @param <R>
 */
public interface IServices<R> {
    
    public void ajouterReclamation(R rec) ;
    public void supprimerReclamation(R rec);
    public void modifierReclamation(R rec) ;
    public List<R> afficherReclamation();
    public List<R> rechercherReclamation();
   
    
}

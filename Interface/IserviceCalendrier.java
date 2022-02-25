/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lenovo
 * @param <Calendrier>
 */
public interface IserviceCalendrier<Calendrier> {

    public void AjouterCoursDansCalendrier(Calendrier cal) throws SQLException;

    public void supprimerCoursDansCalendrier(Calendrier cal) throws SQLException;

    public void modifierCoursDansCalendrier(Calendrier cal) throws SQLException;

    public List<Calendrier> afficherCalendrier() throws SQLException;
    
    public List<Calendrier> rechercherCoursDansCalendrier(String cours) throws SQLException;
    
    

}
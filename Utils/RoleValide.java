/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Yassine Karoui
 */
public class RoleValide {
    public static boolean verifierRole(String role){
        return ((role.equals("Client"))||(role.equals("Coach"))||(role.equals("Proprietaire salle de sport")));
    }
}

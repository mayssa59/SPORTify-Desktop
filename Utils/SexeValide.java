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
public class SexeValide {
    public static boolean verifierSexe(String sexe){
        return (sexe.equals("Homme"))||(sexe.equals("Femme"));
    }
}

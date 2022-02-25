/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Yassine Karoui
 */
public class NumeroTelValide {
    public static boolean verifierNumeroTel(String num) 
    { 
        Pattern p = Pattern.compile("([0-9]){8}"); 
 
        Matcher m = p.matcher(num); 
        return (m.find() && m.group().equals(num)); 
    } 
    
    
    
}

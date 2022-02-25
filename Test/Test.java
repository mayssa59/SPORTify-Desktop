/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Entite.Utilisateur;
import Service.ServiceUtilisateur;
import Service.ServiceAdministrateur;
import Utils.CryptDecrypt;
import Utils.NumeroTelValide;
import Utils.SendEmail;
import java.nio.charset.Charset;

import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sun.net.www.http.HttpClient.New;

/**
 *
 * @author Yassine Karoui
 *///md5
public class Test {
    public static void main(String args[]) throws SQLException {
        

    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 5;
    Random random = new Random();
    StringBuilder buffer = new StringBuilder(targetStringLength);
    for (int i = 0; i < targetStringLength; i++) {
        int randomLimitedInt = leftLimit + (int) 
          (random.nextFloat() * (rightLimit - leftLimit + 1));
        buffer.append((char) randomLimitedInt);
    }
    String generatedString = buffer.toString();

    System.out.println(generatedString);

    }
       
       
    

}
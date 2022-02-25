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
import Entite.Utilisateur;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {
    private final Connection cnx = DataSource.getInstance().getCnx();
    private Statement ste;
    private ResultSet result;
    
    public  String TrouverEmail(String surnom) throws SQLException{
        String email = null;
        String req = "Select `email` from utilisateur WHERE `surnom` ='"+surnom+"'";
        ste = cnx.createStatement();
        result = ste.executeQuery(req);
        while (result.next()) {
            email = result.getString("email");
        }

        return email;
    }
    
    public String trouverMotDePasse(String email) throws SQLException{
        String mdp = null;
        if (email != null){
            String req = "Select `password` from utilisateur WHERE `email` ='"+email+"'";
            ste = cnx.createStatement();
            result = ste.executeQuery(req);
            while (result.next()) {
                mdp = CryptDecrypt.decrypt(result.getString("password"));
            }
        }
        return mdp;
    }
    
     public String trouverMotDePasseA(String email) throws SQLException{
        String mdp = null;
        if (email != null){
            String req = "Select `MDP_admin` from administrateur WHERE `Email_admin` ='"+email+"'";
            ste = cnx.createStatement();
            result = ste.executeQuery(req);
            while (result.next()) {
                mdp = CryptDecrypt.decrypt(result.getString("MDP_admin"));
            }
        }
        return mdp;
    }
     
    public static int Send(String surnom) throws SQLException {
        SendEmail se = new SendEmail();
		//authentication info
                if (se.TrouverEmail(surnom) != null){
                    final String username = "sportify.feelgood@gmail.com";
                    final String password = "HELLOMYFRIENDS";
                    String fromEmail = "SPORTify@gmail.com";

                    String toEmail = se.TrouverEmail(surnom);

                    Properties properties = new Properties();
                    properties.put("mail.smtp.auth", "true");
                    properties.put("mail.smtp.starttls.enable", "true");
                    properties.put("mail.smtp.host", "smtp.gmail.com");
                    properties.put("mail.smtp.port", "587");

                    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(username,password);
                            }
                    });
                    //Start our mail message
                    MimeMessage msg = new MimeMessage(session);
                    try {
                        msg.setFrom(new InternetAddress(fromEmail));
                        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
                        msg.setSubject("réinitialisation du mot de passe");
                        msg.setText("bonjour "+surnom+",\nBesoin de réinitialiser votre mot de passe?\n\nUtilisez votre code secret!\n"+ CodeGenerator.genererCode() +"\n\nSi vous n'avez pas oublié votre mot de passe, vous pouvez ignorer cet e-mail." );		
                        Transport.send(msg);
                        System.out.println("Email envoyé");
                        return 1;
                    }catch (MessagingException e) {
                            e.printStackTrace();
                    }
                } else 
                    return 0;
                return 0;
	}
    
    public static int SendA(String email) throws SQLException {
                SendEmail se = new SendEmail();
		//authentication info
                if (email != null){
                final String username = "sportify.feelgood@gmail.com";
		final String password = "HELLOMYFRIENDS";
		String fromEmail = "SPORTify@gmail.com";
                
		String toEmail = email;
	
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		//Start our mail message
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(fromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setSubject("Mot de passe oublié");
                        msg.setText("bonjour Admin,\nBesoin de réinitialiser votre mot de passe?\n\nUtilisez votre code secret!\n"+ CodeGenerator.genererCode() +"\n\nSi vous n'avez pas oublié votre mot de passe, vous pouvez ignorer cet e-mail.");		
			Transport.send(msg);
			System.out.println("Email envoyé");
                        return 1;
		}catch (MessagingException e) {
			e.printStackTrace();
		}
                return 0;
                }
        return 0;
	}
    
    public static int SendCompteVerification(String surnom) throws SQLException {
                SendEmail se = new SendEmail();
		//authentication info
                if (se.TrouverEmail(surnom) != null){
                    final String username = "sportify.feelgood@gmail.com";
                    final String password = "HELLOMYFRIENDS";
                    String fromEmail = "SPORTify@gmail.com";

                    String toEmail = se.TrouverEmail(surnom);

                    Properties properties = new Properties();
                    properties.put("mail.smtp.auth", "true");
                    properties.put("mail.smtp.starttls.enable", "true");
                    properties.put("mail.smtp.host", "smtp.gmail.com");
                    properties.put("mail.smtp.port", "587");

                    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(username,password);
                            }
                    });
                    //Start our mail message
                    MimeMessage msg = new MimeMessage(session);
                    try {
                        msg.setFrom(new InternetAddress(fromEmail));
                        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
                        msg.setSubject("Verifier votre adresse email");
                        msg.setText("bonjour "+surnom+",\nPour completer votre inscription, nous devons juste verifier votre adresse email\nVoici le code de verification :\n"+ CodeGenerator.genererCode());		
                        Transport.send(msg);
                        System.out.println("Email envoyé");
                        return 1;
                    }catch (MessagingException e) {
                            e.printStackTrace();
                    }
                } else 
                    return 0;
                return 0;
}
}
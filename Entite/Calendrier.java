/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author lenovo
 */
public class Calendrier {

    private int ID_Cours;
    private String Type_Cours;
    private String Date;
    private String Heure;
    
    private String Duree;
    private String MailCoach;
    private int Place_Disponible;

    public Calendrier() {
    }

    public Calendrier(int ID_Cours, String Type_Cours, String Date, String Heure) {
        this.ID_Cours = ID_Cours;
        this.Type_Cours = Type_Cours;
        this.Date = Date;
        this.Heure = Heure;

    }

    public Calendrier(String Type_Cours, String Date, String Heure) {
        this.Type_Cours = Type_Cours;
        this.Date = Date;
        this.Heure = Heure;
    }

    public Calendrier(String Type_Cours, String Date, String Heure, String Duree, String MailCoach, int Place_Disponible) {
        this.Type_Cours = Type_Cours;
        this.Date = Date;
        this.Heure = Heure;
        this.Duree = Duree;
        this.MailCoach = MailCoach;
        this.Place_Disponible = Place_Disponible;
    }

    public String getDuree() {
        return Duree;
    }

    public void setDuree(String Duree) {
        this.Duree = Duree;
    }

    public String getMailCoach() {
        return MailCoach;
    }

    public void setMailCoach(String MailCoach) {
        this.MailCoach = MailCoach;
    }

    public int getPlace_Disponible() {
        return Place_Disponible;
    }

    public void setPlace_Disponible(int Place_Disponible) {
        this.Place_Disponible = Place_Disponible;
    }
    
    
    

    public int getID_Cours() {
        return ID_Cours;
    }

    public void setID_Cours(int ID_Cours) {
        this.ID_Cours = ID_Cours;
    }

    public String getType_Cours() {
        return Type_Cours;
    }

    public void setType_Cours(String Type_Cours) {
        this.Type_Cours = Type_Cours;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getHeure() {
        return Heure;
    }

    public void setHeure(String Heure) {
        this.Heure = Heure;
    }
    

    @Override
    public String toString() {
        return "Calendrier{" + "ID_Cours=" + ID_Cours + ", Type_Cours=" + Type_Cours + ", Date=" + Date + ", Heure=" + Heure + ", Duree=" + Duree + ", MailCoach=" + MailCoach + ", Place_Disponible=" + Place_Disponible + '}';
    }

    
}

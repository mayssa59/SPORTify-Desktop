/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.sql.Date;

/**
 *
 * @author Yassine Karoui
 */
public class Cours {
    
  private String ID_Cours;
    private String Type_Cours;
    private Date Date;
    private  float Heure;
    private int Duree;
    private String MailCoach;
    private int Place_Disponible;

     
      public Cours(String ID_Cours) {
        this.ID_Cours = ID_Cours;
    }
       public Cours() {
      
    }

    public Cours(String ID_Cours, int Place_Disponible) {
        this.ID_Cours = ID_Cours;
        this.Place_Disponible = Place_Disponible;
    }

    public Cours(String ID_Cours, String Type_Cours, Date Date, float Heure, int Duree, int Place_Disponible) {
        this.ID_Cours = ID_Cours;
        this.Type_Cours = Type_Cours;
        this.Date = Date;
        this.Heure = Heure;
        this.Duree = Duree;
        this.Place_Disponible = Place_Disponible;
    }
      

    public Cours(String ID_Cours, String Type_Cours, Date Date, float Heure, int Duree, String MailCoach, int Place_Disponible) {
        this.ID_Cours = ID_Cours;
        this.Type_Cours = Type_Cours;
        this.Date = Date;
        this.Heure = Heure;
        this.Duree = Duree;
        this.MailCoach = MailCoach;
        this.Place_Disponible = Place_Disponible;
    }

    public String getID_Cours() {
        return ID_Cours;
    }

    public void setID_Cours(String ID_Cours) {
        this.ID_Cours = ID_Cours;
    }


    @Override
    public String toString() {
        return "Cours{" + "ID_Cours=" + ID_Cours + ", Type_Cours=" + Type_Cours + ", Date=" + Date + ", Heure=" + Heure + ", Duree=" + Duree + ", MailCoach=" + MailCoach + ", Place_Disponible=" + Place_Disponible + '}';
    }
    
    public String getIdCours() {
        return ID_Cours;
    }

    public void setIdCours(String ID_Cours) {
        this.ID_Cours = ID_Cours;
    }

    public String getTypeCours() {
        return Type_Cours;
    }

    public void setType_Cours(String Type_Cours) {
        this.Type_Cours = Type_Cours;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public float getHeure() {
        return Heure;
    }

    public void setHeure(float Heure) {
        this.Heure = Heure;
    }

    public int getDuree() {
        return Duree;
    }

    public void setDuree(int Duree) {
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

    
}

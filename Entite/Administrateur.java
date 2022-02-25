/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import Utils.EmailValide;
/**
 *
 * @author Yassine Karoui
 */
public class Administrateur {
    private int ID_admin;
    private String Email_admin;
    private String MDP_admin;

    public Administrateur(int ID_admin) {
        this.ID_admin = ID_admin;
    }

    public Administrateur(String Email_admin, String MDP_admin) {
        if (EmailValide.verifierEmail(Email_admin)){
            this.Email_admin = Email_admin;
            this.MDP_admin = MDP_admin;
        } else {
            System.out.print("Email invalide");
        }
    }

    public Administrateur(int ID_admin, String Email_admin, String MDP_admin) {
        if (!EmailValide.verifierEmail(Email_admin)){
            this.ID_admin = ID_admin;
            this.Email_admin = Email_admin;
              this.MDP_admin = MDP_admin;
        } else {
            System.out.print("Email invalide");
        }
    }

    public int getID_admin() {
        return ID_admin;
    }

    public void setID_admin(int ID_admin) {
        this.ID_admin = ID_admin;
    }

    public String getEmail_admin() {
        return Email_admin;
    }

    public void setEmail_admin(String Email_admin) {
        this.Email_admin = Email_admin;
    }

    public String getMDP_admin() {
        return MDP_admin;
    }

    public void setMDP_admin(String MDP_admin) {
        this.MDP_admin = MDP_admin;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.ID_admin;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Administrateur other = (Administrateur) obj;
        if (this.ID_admin != other.ID_admin) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Administrateur{" + "ID_admin=" + ID_admin + ", Email_admin=" + Email_admin + ", MDP_admin=" + MDP_admin + '}';
    }

    
}

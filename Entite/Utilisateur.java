/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import Utils.EmailValide;
import Utils.NumeroTelValide;
import Utils.RoleValide;
import Utils.SexeValide;
import java.util.Objects;
/**
 *
 * @author Yassine Karoui
 */
public class Utilisateur {
    private int id;
    private String surnom;
    private String prenom;
    private String nom;
    private String numeroDeTelephone;
    private String email;
    private String password;
    private String dateDeNaissance;
    private String sexe;
    private String role;
    private int nbrEvaluation;
    private float evaluationCoach;

    public Utilisateur() {
    }

    public Utilisateur(String surnom, String prenom, String nom, String numeroDeTelephone, String email, String dateDeNaissance, String sexe) {
        this.surnom = surnom;
        this.prenom = prenom;
        this.nom = nom;
        this.numeroDeTelephone = numeroDeTelephone;
        this.email = email;
        this.dateDeNaissance = dateDeNaissance;
        this.sexe = sexe;
    }

    public Utilisateur(String surnom, String prenom, String nom, String numeroDeTelephone, String email, String password, String dateDeNaissance, String sexe, String role, int nbrEvaluation, float evaluationCoach) {
        this.surnom = surnom;
        this.prenom = prenom;
        this.nom = nom;
        this.numeroDeTelephone = numeroDeTelephone;
        this.email = email;
        this.password = password;
        this.dateDeNaissance = dateDeNaissance;
        this.sexe = sexe;
        this.role = role;
        this.nbrEvaluation = nbrEvaluation;
        this.evaluationCoach = evaluationCoach;
    }

    
    public Utilisateur(int id, String surnom, String prenom, String nom, String numeroDeTelephone, String email, String password, String dateDeNaissance, String sexe, String role) {
        this.id = id;
        this.surnom = surnom;
        this.prenom = prenom;
        this.nom = nom;
        this.numeroDeTelephone = numeroDeTelephone;
        this.email = email;
        this.password = password;
        this.dateDeNaissance = dateDeNaissance;
        this.sexe = sexe;
        this.role = role;
    }

    public Utilisateur(int id, String surnom, String prenom, String nom, String numeroDeTelephone, String email, String password, String dateDeNaissance, String sexe, String role, int nbrEvaluation, float evaluationCoach) {
        this.id = id;
        this.surnom = surnom;
        this.prenom = prenom;
        this.nom = nom;
        this.numeroDeTelephone = numeroDeTelephone;
        this.email = email;
        this.password = password;
        this.dateDeNaissance = dateDeNaissance;
        this.sexe = sexe;
        this.role = role;
        this.nbrEvaluation = nbrEvaluation;
        this.evaluationCoach = evaluationCoach;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumeroDeTelephone() {
        return numeroDeTelephone;
    }

    public void setNumeroDeTelephone(String numeroDeTelephone) {
        this.numeroDeTelephone = numeroDeTelephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(String dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getNbrEvaluation() {
        return nbrEvaluation;
    }

    public void setNbrEvaluation(int nbrEvaluation) {
        this.nbrEvaluation = nbrEvaluation;
    }

    public float getEvaluationCoach() {
        return evaluationCoach;
    }

    public void setEvaluationCoach(float evaluationCoach) {
        this.evaluationCoach = evaluationCoach;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.email);
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
        final Utilisateur other = (Utilisateur) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", surnom=" + surnom + ", prenom=" + prenom + ", nom=" + nom + ", numeroDeTelephone=" + numeroDeTelephone + ", email=" + email + ", password=" + password + ", dateDeNaissance=" + dateDeNaissance + ", sexe=" + sexe + ", role=" + role + '}';
    }
}
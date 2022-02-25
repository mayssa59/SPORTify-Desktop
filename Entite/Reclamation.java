/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author Houssem
 */
public class Reclamation {
    private int ref;
    private String objet;
    private String MSG;

    public Reclamation(int ref, String objet ,String MSG) {
        this.ref = ref;
        this.objet = objet;
        this.MSG = MSG;
    }

    public Reclamation( String objet, String MSG) {
        this.objet = objet;
        this.MSG = MSG;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }


    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "ref=" + ref +  ", objet=" + objet + ", MSG=" + MSG + '}';
    }
    
    
    
    
}

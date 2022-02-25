/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author Yassine Karoui
 */
public class SalleDeSport {
    private String Id_Salle;
    private String Nom_Salle;
    private String Adresse;
    private String Region;
    private int HDebut;
    private int HFin;
    private int NumTel;
    private int Min;
    
    
    public SalleDeSport(String Id_Salle, String Nom_Salle, String Adresse,String Region,int HDebut,int HFin,int Min,int NumTel) {
        this.Id_Salle = Id_Salle;
        this.Nom_Salle = Nom_Salle;
        this.Adresse = Adresse;
        this.Region= Region;
        this.HDebut=HDebut;
        this.HFin=HFin;
        this.Min=Min;
        this.NumTel=NumTel;
        
        
    }
    public SalleDeSport(String Nom_Salle, String Adresse,String Region,int HDebut,int HFin,int Min,int NumTel) {
        
        this.Nom_Salle = Nom_Salle;
        this.Adresse = Adresse;
        this.Region= Region;
        this.HDebut=HDebut;
        this.HFin=HFin;
        this.Min=Min;
        this.NumTel=NumTel;
        
        
    }
    public SalleDeSport() {
     
    }

    public SalleDeSport(String Id_Salle, String Nom_Salle) {
       this.Id_Salle = Id_Salle;
       this.Nom_Salle = Nom_Salle;
    }

    public String getIdSalle() {
        return Id_Salle;
    }

    public void setIdSalle(String Id_Salle) {
        this.Id_Salle= Id_Salle;
    }

    public String getNomSalle() {
        return Nom_Salle;
    }

    public void setNomSalle(String Nom_Salle) {
        this.Nom_Salle = Nom_Salle;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }
     public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }
        public int getHDebut() {
        return HDebut;
    }

    public void setHDebut(int HDebut) {
        this.HDebut = HDebut;
    }

    public int getHFin() {
        return HFin;
    }

    public void setHFin(int HFin) {
        this.HFin = HFin;
    }

    public int getNumTel() {
        return NumTel;
    }

    public void setNumTel(int NumTel) {
        this.NumTel = NumTel;
    }
    public int getMin() {
        return Min;
    }

    public void setMin(int Min) {
        this.Min = Min;
    }


    @Override
    public String toString() {
        return "Salle de sport{" + "Id de la Salle= " + Id_Salle + ", Nom de la salle= " + Nom_Salle + ", Adresse de la salle = " + Adresse + ", Lieu de la salle= " + Region +", horaire: ouvert-fermé= " + HDebut +":"+Min+"-"+HFin+":"+Min+" , Num de Tel de la salle= " + NumTel +'}';
    }
    
}

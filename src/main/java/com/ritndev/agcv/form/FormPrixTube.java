package com.ritndev.agcv.form;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormPrixTube {
    
    @Getter @Setter
    private long id;
    
    @Getter @Setter
    private long idTypeTube;
    
    @Getter @Setter
    private String marque;
    
    @Getter @Setter
    private String prix;
    
    @Getter @Setter
    private String prixMembre;
    
    @Getter @Setter
    private boolean actif;
    
    
    
    //Constructeur
    public FormPrixTube() {}

    public FormPrixTube(long id, String marque, String prix, String prixMembre, boolean actif) {
        this.id = id;
        this.marque = marque;
        this.prix = prix;
        this.prixMembre = prixMembre;
        this.actif = actif;
    }
    

    
    
    /*
        Methodes
    */
    
    //Renvoie le prix au format double
    public double getPrixDouble() {
        if (!prix.equals("")){
            return Double.valueOf(prix);
        }else{
            return 0.00;
        }
    }
    
    //Renvoie le prixMembre au format double
    public double getPrixMembreDouble() {
        if (!prixMembre.equals("")){
            return Double.valueOf(prixMembre);
        }else{
            return 0.00;
        }
    }
    
}

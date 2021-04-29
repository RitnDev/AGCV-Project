package com.ritndev.agcv.form;

import java.util.List;

/**
 *
 * @author Ritn
 */

//
public class FormCommande {
    
    //Membre qui commande des tubes
    private String membre;
    public String getMembre() {return membre;}
    public void setMembre(String membre) {this.membre = membre;}
    
    //Nombres de tubes commandés
    private int nbTubeCommande;
    public int getNbTubeCommande() {return nbTubeCommande;}
    public void setNbTubeCommande(int nbTubeCommande) {this.nbTubeCommande = nbTubeCommande;}
    
    //La facture est-elle déjà réglée ?
    private boolean regler;
    public boolean isRegler() {return regler;}
    public void setRegler(boolean regler) {this.regler = regler;}

    
    //Constructeur
    public FormCommande(String membre, int nbTubeCommande, boolean regler) {
        this.membre = membre;
        this.nbTubeCommande = nbTubeCommande;
        this.regler = regler;
    }

    public FormCommande() {}
    
    
    
}

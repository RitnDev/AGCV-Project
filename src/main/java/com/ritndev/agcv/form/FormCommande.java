package com.ritndev.agcv.form;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */

//
public class FormCommande {
    
    //Membre qui commande des tubes
    @Getter @Setter private String membre;
    
    //Nombres de tubes commandés
    @Getter @Setter private int nbTubeCommande;
    
    //La facture est-elle déjà réglée ?
    @Getter @Setter private boolean regler;


    
    //Constructeur
    public FormCommande(String membre, int nbTubeCommande, boolean regler) {
        this.membre = membre;
        this.nbTubeCommande = nbTubeCommande;
        this.regler = regler;
    }

    public FormCommande() {}
    
    
    
}

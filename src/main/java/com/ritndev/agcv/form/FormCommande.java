package com.ritndev.agcv.form;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */

//
public class FormCommande {
    
    //ID
    @Getter @Setter
    private long id;
   
    //id du prix tube correspondant à cette commande
    @Getter @Setter private long idPrixTube;
    
    //id du membre qui commande
    @Getter @Setter private long idMembre;
    
    //Nombres de tubes commandés
    @Getter @Setter private int nbTubeCommande;
    
    //La facture est-elle déjà réglée ?
    @Getter @Setter private boolean regler;


    
    //Constructeur
    public FormCommande() {}

    public FormCommande(long idMembre, int nbTubeCommande, boolean regler) {
        this.idMembre = idMembre;
        this.nbTubeCommande = nbTubeCommande;
        this.regler = regler;
    }

    public FormCommande(long id, boolean regler) {
        this.id = id;
        this.regler = regler;
    }
    
    
    
    
    
    
    
    
    
}

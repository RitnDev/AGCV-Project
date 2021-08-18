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
    @Getter @Setter private long id;
   
    //Nom du mois de la commande
    @Getter @Setter private String nomMois;
    
    //id du membre qui commande
    @Getter @Setter private long idMembre;
    
    //Nombres de tubes commandés
    @Getter @Setter private String nbTubeCommande;
    
    //La facture est-elle déjà réglée ?
    @Getter @Setter private boolean regler;
    
    //id du prix tube correspondant à cette commande
    @Getter @Setter private long idPrixTube;
    
    //Id de la saison active au moment de la commande
    @Getter @Setter private long idSaison;

    //Mois de la commande
    @Getter @Setter private long idConsoMois;
    
    
    
    
    //Constructeur
    public FormCommande() {}

    public FormCommande(String nomMois, long idPrixTube, long idSaison, long idConsoMois) {
        this.nomMois = nomMois;
        this.idPrixTube = idPrixTube;
        this.idSaison = idSaison;
        this.idConsoMois = idConsoMois;
    }
    
    public FormCommande(long idMembre, String nbTubeCommande, boolean regler) {
        this.idMembre = idMembre;
        this.nbTubeCommande = nbTubeCommande;
        this.regler = regler;
    }

    public FormCommande(long id, boolean regler) {
        this.id = id;
        this.regler = regler;
    }
    
    
    
    
    
    
    
    
    
}

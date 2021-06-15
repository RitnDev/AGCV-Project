package com.ritndev.agcv.form;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormConsoMois {
    
    @Getter @Setter
    private long id;
    
    @Getter @Setter
    private String nom;
    
    //id du prix tube utilises lors de ce mois-ci
    @Getter @Setter
    private long idPrixTube;
    
    //id de la reference à la conse de tube (type de tube utilise)
    @Getter @Setter
    private long idTypeVolant;
    
    //Nombre de tubes utilises ce mois-ci
    @Getter @Setter
    private int nbTubeUtilise;
    
    //Nombre de tubes commandé ce mois-ci
    @Getter @Setter
    private int nbTubeCommande;
    
    
    //Constructeur

    public FormConsoMois() {}

    public FormConsoMois(long id, long idPrixTube, int nbTubeUtilise, int nbTubeCommande) {
        this.id = id;
        this.idPrixTube = idPrixTube;
        this.nbTubeUtilise = nbTubeUtilise;
        this.nbTubeCommande = nbTubeCommande;
    }

    public FormConsoMois(long id, int nbTubeUtilise) {
        this.id = id;
        this.nbTubeUtilise = nbTubeUtilise;
    }

    public FormConsoMois(long id, long idPrixTube, int nbTubeCommande) {
        this.id = id;
        this.idPrixTube = idPrixTube;
        this.nbTubeCommande = nbTubeCommande;
    }
    
    
    
}

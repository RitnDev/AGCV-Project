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
    
    //Nombre de tubes commandé ce mois-ci
    @Getter @Setter
    private boolean suivant;
    
    
    //Constructeur
    public FormConsoMois() {}

    public FormConsoMois(long id, String nom, long idPrixTube) {
        this.id = id;
        this.nom = nom;
        this.idPrixTube = idPrixTube;
    }

    public FormConsoMois(long id, String nom, int nbTubeUtilise) {
        this.id = id;
        this.nom = nom;
        this.nbTubeUtilise = nbTubeUtilise;
    }
    
    public FormConsoMois(long id, int nbTubeCommande, String nom) {
        this.id = id;
        this.nom = nom;
        this.nbTubeCommande = nbTubeCommande;
    }

    public FormConsoMois(String nom, long idPrixTube, long idTypeVolant) {
        this.nom = nom;
        this.idPrixTube = idPrixTube;
        this.idTypeVolant = idTypeVolant;
    }
    
    
    
}

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
    
    //id du type volant utilises lors de ce mois-ci
    @Getter @Setter
    private long idTypeVolant;
    
    //Nom du type volant de ce conso mois
    @Getter @Setter
    private String typeVolant;
    
    //Nombre de tubes utilises ce mois-ci
    @Getter @Setter
    private String nbTubeUtilise;
    
    //Nombre de tubes commandé ce mois-ci
    @Getter @Setter
    private String nbTubeCommande;
    
    //Nombre de tubes commandé ce mois-ci
    @Getter @Setter
    private boolean suivant;
    
    
    //Constructeur
    public FormConsoMois() {}

    public FormConsoMois(long id, String nom, long idPrixTube, String typeVolant) {
        this.id = id;
        this.nom = nom;
        this.idPrixTube = idPrixTube;
        this.typeVolant = typeVolant;
        this.nbTubeUtilise = "0";
        this.nbTubeCommande = "0";
    }

    public FormConsoMois(long id, String nom, String nbTubeUtilise) {
        this.id = id;
        this.nom = nom;
        this.nbTubeUtilise = nbTubeUtilise;
    }
    
    public FormConsoMois(String nom, long id, String nbTubeCommande) {
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

package com.ritndev.agcv.form;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormCompet {
    
    //ID
    @Getter @Setter private Long id;
    
    //Nombre de tubes utilisés pendant la compétition
    @Getter @Setter private int nbTubesUtilises;
    
    //Nom de la competition
    @Getter @Setter private String nom;
    
    //Nom de la saison
    @Getter @Setter private String nomSaison;
    
    
    //Constructeur
    public FormCompet() {}

    public FormCompet(int nbTubesUtilises, String nom) {
        this.nbTubesUtilises = nbTubesUtilises;
        this.nom = nom;
    }

    public FormCompet(Long id, int nbTubesUtilises, String nom) {
        this.id = id;
        this.nbTubesUtilises = nbTubesUtilises;
        this.nom = nom;
    }

    public FormCompet(Long id, int nbTubesUtilises, String nom, String nomSaison) {
        this.id = id;
        this.nbTubesUtilises = nbTubesUtilises;
        this.nom = nom;
        this.nomSaison = nomSaison;
    }

    
    //toString
    @Override
    public String toString() {
        return nom;
    }
    
    
    
    
}

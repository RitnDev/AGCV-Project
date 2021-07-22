package com.ritndev.agcv.form;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormMembre {
    
    // ID
    @Getter @Setter private Long id;
    
    // Prenom du membre à créer
    @Getter @Setter private String prenom;
    
    // Nom du membre à créer
    @Getter @Setter private String nom;
    
    // Membre actif ?
    @Getter @Setter private boolean actif;
    
    
    // Constructeur
    public FormMembre(){}
    
    public FormMembre(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public FormMembre(Long id, String prenom, String nom, boolean actif) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.actif = actif;
    }
       
    
    
    
    @Override
    public String toString() {
        return prenom + " " + nom;
    }
}

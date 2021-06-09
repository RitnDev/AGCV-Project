package com.ritndev.agcv.form;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormTypeTube {
    
    @Getter @Setter private long id;
      
    //Nom du type de tube
    @Getter @Setter private String nom;
    
    //Est-ce que ce type de tube est encore utilisé ?
    @Getter @Setter private boolean actif;
    
    //Est-ce que ce type de tube est commandable pour les membres ?
    @Getter @Setter private boolean commande;

    
    //Constructeur
    public FormTypeTube() {}

    public FormTypeTube(long id, String nom, boolean actif, boolean commande) {
        this.id = id;
        this.nom = nom;
        this.actif = actif;
        this.commande = commande;
    }
    
    
    
}

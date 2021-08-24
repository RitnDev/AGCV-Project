package com.ritndev.agcv.form;

import com.ritndev.agcv.model.enumeration.NomTypeTube;
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
     
    //Est-ce que ce type de tube est commandable pour les membres ?
    @Getter @Setter private boolean commande;

    
    //Constructeur
    public FormTypeTube() {}

    public FormTypeTube(long id, String nom, boolean commande) {
        this.id = id;
        this.nom = nom;
        this.commande = commande;
    }

    public FormTypeTube(int i, NomTypeTube nomTypeTube, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

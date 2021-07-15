package com.ritndev.agcv.form;

import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Ritn
 */
public class FormTypeVolant {
    
    @Getter @Setter
    private long id;
       
    //id du type de tube de ce type de volant
    @Getter @Setter
    private long idTypeTube;
    
    //Nom du type de volant
    @Getter @Setter
    private String nom;
    
    //Nombre de tube avant consommation de volant (stock de départ)
    @Getter @Setter
    private int stock;
    
    
    //Constructeur

    public FormTypeVolant() {}

    public FormTypeVolant(int stock, long idTypeTube) {
        this.idTypeTube = idTypeTube;
        this.stock = stock;
    }
    
    public FormTypeVolant(long id, String nom, int stock) {
        this.id = id;
        this.nom = nom;
        this.stock = stock;
    }
    
    public FormTypeVolant(long id, int stock) {
        this.id = id;
        this.stock = stock;
    }
    
}

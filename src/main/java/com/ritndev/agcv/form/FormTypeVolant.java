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
    
    //Nombre de tube avant consommation de volant (stock de départ)
    @Getter @Setter
    private int initTube;
    
    
    //Constructeur

    public FormTypeVolant() {}

    public FormTypeVolant(long id, long idTypeTube, int initTube) {
        this.id = id;
        this.idTypeTube = idTypeTube;
        this.initTube = initTube;
    }

    public FormTypeVolant(long id, int initTube) {
        this.id = id;
        this.initTube = initTube;
    }
    
    
    
    
}

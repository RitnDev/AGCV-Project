
package com.ritndev.agcv.form;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormStock {
    
    //ID
    @Getter @Setter private long id;
     
    //Stock pour les competition actuel
    @Getter @Setter private int stock;
    
    //Valeur à ajouter au stock
    @Getter @Setter private int ajout;
    
      
    //Constructeur
    public FormStock() {
    }

    public FormStock(long id, int stock) {
        this.id = id;
        this.stock = stock;
    }
        
}

package com.ritndev.agcv.form;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormData {
    
    @Getter @Setter private long id;
    @Getter @Setter private long idSaison;
    @Getter @Setter private long idStockCompet;
    @Getter @Setter private double budget;
    @Getter @Setter private int seuilBas;
    @Getter @Setter private boolean actif;
    
    //Construteur
    public FormData() {}
    
    public FormData(long id, long idSaison, long idStockCompet, boolean actif) {
        this.id = id;
        this.idSaison = idSaison;
        this.idStockCompet = idStockCompet;
        this.actif = actif;
    }
    
}

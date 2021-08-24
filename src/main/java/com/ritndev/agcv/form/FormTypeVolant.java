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
    private String stock;
    
    //id de la saison
    @Getter @Setter
    private long idSaison;
    
    
    //Constructeur

    public FormTypeVolant() {}

    public FormTypeVolant(long idTypeTube, long idSaison, String stock) {
        this.idTypeTube = idTypeTube;
        this.stock = stock;
        this.idSaison = idSaison;
    }
    
    public FormTypeVolant(long id, String nom, String stock) {
        this.id = id;
        this.nom = nom;
        this.stock = stock;
    }
    
    public FormTypeVolant(long id, String stock) {
        this.id = id;
        this.stock = stock;
    }
    
    
    /*
        Methodes
    */
    
    
    //Renvoie le Stock au format Integer
    public int getStockInteger(){
        if (!stock.equals("")){
            return Integer.parseInt(stock);
        }else{
            return 0;
        }
    }
    
    
    
}

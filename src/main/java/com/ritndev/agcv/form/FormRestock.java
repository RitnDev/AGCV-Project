package com.ritndev.agcv.form;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormRestock {
    
    //ID
    @Getter @Setter private long id;
    
    //Valeur de restockage
    @Getter @Setter private String valeur;
    
    //ID de la saison en cours lors du restock
    @Getter @Setter private long idSaison;
    
    //ID de la MainData
    @Getter @Setter private long idStock;
    
    //ID du conso-mois lors du restock
    @Getter @Setter private long idConsoMois;
    
    
    //Constructeur
    public FormRestock() {}    

    //Creation d'un nouveau restockage
    public FormRestock(String valeur, long idSaison, long idStock, long idConsoMois) {
        this.valeur = valeur;
        this.idSaison = idSaison;
        this.idStock = idStock;
        this.idConsoMois = idConsoMois;
    }

    //Modification d'un restockage
    public FormRestock(long id, String valeur, long idConsoMois) {
        this.id = id;
        this.valeur = valeur;
        this.idConsoMois = idConsoMois;
    }
    
    
    
    
}

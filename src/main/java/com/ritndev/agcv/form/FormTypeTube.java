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
     
    //Est-ce que ce type de tube est commandable pour les membres ?
    @Getter @Setter private boolean commande;
    
    //Seuil bas pour ce type de tube
    @Getter @Setter private String seuilBas;

    
    //Constructeur
    public FormTypeTube() {}

    public FormTypeTube(long id, String nom, boolean commande) {
        this.id = id;
        this.nom = nom;
        this.commande = commande;
    }

    public FormTypeTube(long id, String nom, String seuilBas) {
        this.id = id;
        this.nom = nom;
        this.seuilBas = seuilBas;
    }
    
    

    
    //Renvoie le seuil au format int
    public int getSeuilInteger() {
        if (!seuilBas.equals("")){
            return Integer.parseInt(seuilBas);
        }else{
            return 0;
        }
    }
    
    
}

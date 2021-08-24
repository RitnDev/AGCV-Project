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
    @Getter @Setter private String budget;
    @Getter @Setter private String seuilBas;
    @Getter @Setter private boolean actif;
    
    //Construteur
    public FormData() {}
    
    public FormData(long id, long idSaison, boolean actif) {
        this.id = id;
        this.idSaison = idSaison;
        this.actif = actif;
    }

    public FormData(String budget, String seuilBas) {
        this.budget = budget;
        this.seuilBas = seuilBas;
    }
    
    
    /*
        Methodes
    */
    
    //Renvoie le budget au format double
    public double getBudgetDouble() {
        if (!budget.equals("")){
            return Double.parseDouble(budget);
        }else{
            return 0.00;
        }
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

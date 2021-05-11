/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritndev.agcv.form;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormSaison {
    
    //Année de début de la saison
    @Getter @Setter private int annee_debut;

    //budget prévisionnelle de la saison
    @Getter @Setter private String budget;
      
    
    //Constructeur
    public FormSaison(int annee_debut, String budget) {
        this.annee_debut = annee_debut;
        this.budget = budget;
    }
    
    public FormSaison(){}
    
    
    
    
    @Override
    public String toString() {
        return String.valueOf(annee_debut) + " - " + String.valueOf(annee_debut + 1);
    }
    
    
    public double getBudgetDouble() {
        return Double.valueOf(budget);
    }
    
    
}

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
    
    //ID de la saison
    @Getter @Setter private long id;
    
    //Année de début de la saison
    @Getter @Setter private int annee_debut;

    //budget prévisionnelle de la saison
    @Getter @Setter private double budget;
    
    //Est-ce la saison actuelle ?
    @Getter @Setter private boolean actuelle;
    
    
    
    //Constructeur
    public FormSaison(){}
  
    public FormSaison(int annee_debut, double budget, boolean actuelle) {
        this.annee_debut = annee_debut;
        this.budget = budget;
        this.actuelle = actuelle;
    }
    
    public FormSaison(long id, double budget, boolean actuelle) {
        this.id = id;
        this.budget = budget;
        this.actuelle = actuelle;
    }

    public FormSaison(long id, int annee_debut) {
        this.id = id;
        this.annee_debut = annee_debut;
    }
    
    
    
    
    
    @Override
    public String toString() {
        return String.valueOf(annee_debut) + " - " + String.valueOf(annee_debut + 1);
    }
    
    /*
    public double getBudgetDouble() {
        return Double.valueOf(budget);
    }
    */
    
}

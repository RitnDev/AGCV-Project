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
    @Getter @Setter private String annee_debut;

    //budget prévisionnelle de la saison
    @Getter @Setter private String budget;
    
    //Est-ce la saison actuelle ?
    @Getter @Setter private boolean actuelle;
    
    
    
    //Constructeur
    public FormSaison(){}
  
    public FormSaison(String annee_debut, String budget, boolean actuelle) {
        this.annee_debut = annee_debut;
        this.budget = budget;
        this.actuelle = actuelle;
    }
    
    public FormSaison(long id, String budget, boolean actuelle) {
        this.id = id;
        this.budget = budget;
        this.actuelle = actuelle;
    }

    public FormSaison(long id, String annee_debut) {
        this.id = id;
        this.annee_debut = annee_debut;
    }
    
    
    
    
    
    @Override
    public String toString() {
        return annee_debut + " - " + String.valueOf(getAnneeInteger() + 1);
    }
    
    
    //Renvoie le budget au format double
    public double getBudgetDouble() {
        if (!budget.equals("")){
            return Double.parseDouble(budget);
        }else{
            return 0.00;
        }
    }
    
    //Renvoie le seuil au format int
    public int getAnneeInteger() {
        if (!annee_debut.equals("")){
            return Integer.parseInt(annee_debut);
        }else{
            return 0;
        }
    }
  
    
}

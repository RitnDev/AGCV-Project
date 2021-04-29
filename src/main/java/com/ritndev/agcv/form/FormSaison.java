/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritndev.agcv.form;

/**
 *
 * @author Ritn
 */
public class FormSaison {
    
    //Année de début de la saison
    private int annee_debut;
    public int getAnnee_debut() {return annee_debut;}
    public void setAnnee_debut(int annee_debut) {this.annee_debut = annee_debut;}
    
    //Année de fin de la saison
    private int annee_fin;
    public int getAnnee_fin() {return annee_fin;}
    public void setAnnee_fin(int annee_fin) {this.annee_fin = annee_fin;}
    
    //budget prévisionnelle de la saison
    private String budget;
    public String getBudget() {return budget;}
    public void setBudget(String budget) {this.budget = budget;}
       
    
    //Constructeur
    public FormSaison(int annee_debut, int annee_fin, String budget) {
        this.annee_debut = annee_debut;
        this.annee_fin = annee_fin;
        this.budget = budget;
    }
    
    public FormSaison(){}
    
    
    
    
    @Override
    public String toString() {
        return String.valueOf(annee_debut) + " - " + String.valueOf(annee_fin);
    }
    
    
    public double getBudgetDouble() {
        return Double.valueOf(budget);
    }
    
    
}

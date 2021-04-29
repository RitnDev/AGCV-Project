package com.ritndev.agcv.form;

/**
 *
 * @author Ritn
 */
public class FormTest {
    
    // Prenom du membre à créer
    private String prenom;
    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    
    // Nom du membre à créer
    private String nom;
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    
    
    // Constructeur
    public FormTest(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public FormTest() {}
    
    
    
    
    @Override
    public String toString() {
        return prenom + " " + nom;
    }
    
    
    
}

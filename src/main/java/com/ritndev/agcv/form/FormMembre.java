package com.ritndev.agcv.form;

/**
 *
 * @author Ritn
 */
public class FormMembre {
    
    // ID
    private Long id;
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    
    // Prenom du membre à créer
    private String prenom;
    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    
    // Nom du membre à créer
    private String nom;
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    
    
    // Constructeur
    public FormMembre(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public FormMembre(Long id, String prenom, String nom) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
    }
    
    public FormMembre() {}
    
    
    
    
    @Override
    public String toString() {
        return prenom + " " + nom;
    }
}

package com.ritndev.agcv.model;

import com.ritndev.agcv.form.FormTypeVolant;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Ritn
 */
@Entity
@Table(name = "SAISON")
public class Saison implements Serializable {
    
    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter @Setter private long id;
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    @Getter @Setter private Timestamp horodatage;
    
    //Annee de début de la saison
    @Column(name = "anneeDebut", nullable = false)
    @Getter @Setter private int anneeDebut;
    
    //Annee de fin de la saison
    @Column(name = "anneeFin", nullable = false)
    @Getter @Setter private int anneeFin;
    
    //Budget prévisionnel de cette saison
    @Column(name = "budget", nullable = false)
    @Getter @Setter private double budget;
    
    //Est-ce la saison actuelle ?
    @Column(name = "actuelle", nullable = false)
    @Getter @Setter private boolean actuelle;
    
    
    
    //Liste des commandes de la saison
    @OneToMany(targetEntity=Commande.class, mappedBy="idSaison", cascade=CascadeType.REMOVE)
    @Getter @Setter private List<Commande> commandes = new ArrayList<>();
    
    //Liste des competitions de la saison
    @OneToMany(targetEntity=Competition.class, mappedBy="idSaison", cascade=CascadeType.REMOVE)
    @Getter @Setter private List<Competition> competitions = new ArrayList<>();
    
    //Les types volants de la saison
    @OneToMany(targetEntity=TypeVolant.class, mappedBy="idSaison", cascade=CascadeType.REMOVE)
    @Getter @Setter private List<TypeVolant> typeVolants = new ArrayList<>();
    
    //Liste des restockages de la saison
    @OneToMany(targetEntity=Restock.class, mappedBy="idSaison", cascade=CascadeType.REMOVE)
    @Getter @Setter private List<Restock> restocks = new ArrayList<>();
    
    
    //Constructeur
    public Saison() {}

    public Saison(int anneeDebut, double budget, boolean actuelle) {
        this.anneeDebut = anneeDebut;
        this.anneeFin = anneeDebut + 1;
        this.budget = budget;
        this.actuelle = actuelle;
    }

    public Saison(long id, double budget, boolean actuelle) {
        this.id = id;
        this.budget = budget;
        this.actuelle = actuelle;
    }
  
    
    
    
    @Override public String toString() {
        String strResult = "";
        if(anneeDebut>0){
            strResult = String.valueOf(anneeDebut) + " - " + String.valueOf(anneeFin);
        }
        return strResult;
    }
    
    
    /*
        Méthodes
    */
    
    public String getIdH() {
        String strResult = "saison-actif-on";
        if(!actuelle) strResult = "saison-actif-off";
        return strResult;
    }
    
    public String getActif() {
        String strResult = "Non";
        if(actuelle) strResult = "Oui";
        return strResult;
    }
    
    //Renvoie le budget au format String
    public String getBudgetString(){
        return String.format("%.2f", budget);
    }
    
    //Coût total des volants commandés par le club sur une saison
    public double getCoutTotalCommandesClub() {
        double total = 0.0;
        for (TypeVolant tv : typeVolants){
            total = total + tv.getTotalCoutCommandes();
        }
        return total;
    }
    public String getStringCoutTotalCommandesClub() {
        return String.format("%.2f", getCoutTotalCommandesClub());
    }
    
    //Coût total des volants utilisés sur une saison
    public double getCoutTotalUtilises() {
        double total = 0.0;
        for (TypeVolant tv : typeVolants){
            total = total + tv.getTotalCoutUtilises();
        }
        return total;
    }
    public String getStringCoutTotalUtilises() {
        return String.format("%.2f", getCoutTotalUtilises());
    }
    
    //Coût total des volants commandés par les membres sur une saison (membre)
    public double getMontantCommandesMembres() {
        double total = 0.0;
        for (Commande c : commandes){
            total = total + c.getPrixCommandeMembre();
        }
        return total;
    }
    public String getStringMontantCommandesMembres() {
        return String.format("%.2f", getMontantCommandesMembres());
    }
    
    //Coût total des volants commandés par les membres sur une saison (club)
    public double getMontantCommandesClub() {
        double total = 0.0;
        for (Commande c : commandes){
            total = total + c.getPrixCommandeClub();
        }
        return total;
    }
    public String getStringMontantCommandesClub() {
        return String.format("%.2f", getMontantCommandesClub());
    }
    
    //Montant total restant à payé par les membres sur la saison
    public double getMontantRestant() {
        double total = 0.0;
        for (Commande c : commandes){
            total = total + c.getMontantRestant();
        }
        return total;
    }
    public String getStringMontantRestant() {
        return String.format("%.2f", getMontantRestant());
    }
    
    //Montant total des commandes déduit apres membre
    public double getMontantDeduit() {
        double total = 0.0;
        for (Commande c : commandes){
            total = total + c.getDeductionClub();
        }
        return total;
    }
    public String getStringMontantDeduit() {
        return String.format("%.2f", getMontantDeduit());
    }
    
    //Nombres de tubes utilisés pour des compétitions dans la saison
    public int getNbTubesCommandes() {
        int total = 0;
        for (Commande c : commandes){
            total = total + c.getNbTubeCommande();
        }
        return total;
    }
    
    
    
    //Renvoie le TypeVolant par son nom
    public TypeVolant getTypeVolantName(String nom){
        for(TypeVolant tv : typeVolants){
            if(tv.toString().equals(nom)){
                return tv;
            }
        }
        return null;
    }
    
    //Renvoie un Consomois selon le nom du mois d'un type de volant particulier (nomVolant)
    public ConsoMois getConsoMois(String nomVolant, String mois){
        return getTypeVolantName(nomVolant).getConsoMoisName(mois);
    }
    
    //Renvoie Le PrixTube associé à un TypeVolant et un ConsoMois particulier (mois)
    public PrixTube getPrixTube(String nomVolant, String mois) {
        return getTypeVolantName(nomVolant).getConsoMoisName(mois).getIdPrixTube();
    }
    
    //Renvoie le FormTypeVolant par le nom du typeVolant
    public FormTypeVolant getFormVolantName(String nom) {
        if (!nom.isEmpty() && nom!=null){
            TypeVolant tv = getTypeVolantName(nom);
            if (tv!=null) {
                return new FormTypeVolant(tv.getId(), nom, tv.getStockString());
            }
        }
        return null;
    }
    
    //Nombres de tubes utilisés pour des compétitions dans la saison
    public int getNbTubesCompet() {
        int total = 0;
        for (Competition c : competitions){
            total = total + c.getNbTubesUtilises();
        }
        return total;
    }
    
    public double getRestantBudget() {
        return budget - getCoutTotalUtilises() + getMontantCommandesMembres();
    }
    public String getStringRestantBudget() {
        return String.format("%.2f", getRestantBudget());
    }
    
}

package com.ritndev.agcv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @GeneratedValue
    @Column(name = "saison_id", nullable = false)
    private long id;
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    private Timestamp horodatage;
    public Timestamp getHorodatage() {return horodatage;}
    public void setHorodatage(Timestamp horodatage) {this.horodatage = horodatage;}
    
    //Nom de la saison
    @Column(name = "nom", nullable = false)
    private String nom;
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    
    //Budget prévisionnelle de la saison
    @Column(name = "budget", nullable = false)
    private double budgetPrevisionnelle;
    public double getBudgetPrevisionnelle() {return budgetPrevisionnelle;}
    public void setBudgetPrevisionnelle(double budgetPrevisionnelle) {this.budgetPrevisionnelle = budgetPrevisionnelle;}
    
    //Liste de la consommation des volants par type lors de cette saison
    @ElementCollection
    @CollectionTable(name = "type_volant", joinColumns = @JoinColumn(name = "saison_id"))
    @OneToMany(fetch = FetchType.LAZY)
    private List<TypeVolant> listTypeTube = new ArrayList<TypeVolant>();
    public List<TypeVolant> getListTypeTube() {return listTypeTube;}
    public void setListTypeTube(List<TypeVolant> listTypeTube) {this.listTypeTube = listTypeTube;}
    
    //Sac de compétition
    /*
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sac_id", nullable = false)
    private SacCompetition sacCompetition;
    public SacCompetition getSacCompetition() {return sacCompetition;}
    public void setSacCompetition(SacCompetition sacCompetition) {this.sacCompetition = sacCompetition;}
    */
    
    //Liste des Compétitions de la saison
    @ElementCollection
    @CollectionTable(name = "competitions", joinColumns = @JoinColumn(name = "saison_id"))
    @OneToMany(fetch = FetchType.LAZY)
    private List<Competition> listCompetitions = new ArrayList<Competition>();
    public List<Competition> getListCompetitions() {return listCompetitions;}
    public void setListCompetitions(List<Competition> listCompetitions) {this.listCompetitions = listCompetitions;}
    
    //Liste des Commandes de la saison
    @ElementCollection
    @CollectionTable(name = "commandes", joinColumns = @JoinColumn(name = "saison_id"))
    @OneToMany(fetch = FetchType.LAZY)
    private List<Commande> listCommandes = new ArrayList<Commande>();
    public List<Commande> getListCommandes() {return listCommandes;}
    public void setListCommandes(List<Commande> listCommandes) {this.listCommandes = listCommandes;}
    
    
    //Constructeur
    
    public Saison(String nom, double budgetPrevisionnelle) {
        this.nom = nom;
        this.budgetPrevisionnelle = budgetPrevisionnelle;
    }

    
    
    /*
        --- Methodes ---
    */
    
    //Montant total de toute les commandes faites sur tous les type de volant dans la saison :
    /*
    public double getCoutCommandeClub(){
        double coutCommande = 0.0;
        for(TypeVolant typeVolant : this.listTypeTube.values()){
            coutCommande = coutCommande + typeVolant.getCoutCommande();
        }
        return coutCommande;
    }
    
    
    public double getCoutCommandeMembre(){
        double coutCommande = 0.0;
        for(Commande commande : this.listCommandes){
            coutCommande = coutCommande + commande.getCoutCommandeMembre();
        }
        return coutCommande;
    }
    */

    
}

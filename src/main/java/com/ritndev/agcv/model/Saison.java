package com.ritndev.agcv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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
    @OneToMany(targetEntity=Commande.class, mappedBy="idSaison")
    @Getter @Setter private List<Commande> commandes = new ArrayList<>();
    
    //Liste des competitions de la saison
    @OneToMany(targetEntity=Competition.class, mappedBy="idSaison")
    @Getter @Setter private List<Competition> competitions = new ArrayList<>();
    
    //Les types volants de la saison
    @OneToMany(targetEntity=TypeVolant.class, mappedBy="idSaison")
    @Getter @Setter private List<TypeVolant> typeVolants = new ArrayList<>();
    
    
    
    
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
    
    
    //Renvoie le budget au format String
    public String getBudgetString(){
        return String.valueOf(budget);
    }
    
}

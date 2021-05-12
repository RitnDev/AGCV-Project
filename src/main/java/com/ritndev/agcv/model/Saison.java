package com.ritndev.agcv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    @GeneratedValue
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
        return String.valueOf(anneeDebut) + " - " + String.valueOf(anneeFin);
    }
    
    
    //Renvoie le budget au format String
    public String getBudgetString(){
        return String.valueOf(budget);
    }
    
}

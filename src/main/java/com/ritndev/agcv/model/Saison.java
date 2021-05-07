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
    @Getter @Setter
    private long id;
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    @Getter @Setter
    private Timestamp horodatage;
    
    //Nom de la saison (pour affichage dans les vues)
    @Column(name = "nom", nullable = false)
    @Getter @Setter
    private String nom;
    
    //Budget prévisionnel de cette saison
    @Column(name = "budget", nullable = false)
    @Getter @Setter
    private double budget;
    
    
    //Constructeur
    public Saison() {}

    public Saison(String nom, double budget) {
        this.nom = nom;
        this.budget = budget;
    }
    
}

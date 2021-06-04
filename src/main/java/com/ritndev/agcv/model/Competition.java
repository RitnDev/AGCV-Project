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
@Table(name = "COMPETITION")
public class Competition implements Serializable {
    
    //ID
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    @Getter @Setter private long id;
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    @Getter @Setter private Timestamp horodatage;
    
    //id de la saison de cette competition
    @Column(name = "idSaison", nullable = false)
    @Getter @Setter private long idSaison;
    
    //Nombre de tubes utilisés lors de cette competition
    @Column(name = "nbTubesUtilises", nullable = false)
    @Getter @Setter private int nbTubesUtilises;
    
    //Nom de la competition
    @Column(name = "nom", nullable = false)
    @Getter @Setter private String nom;
    
    
    
    //Constructeur
    public Competition() {}

    public Competition(long idSaison, int nbTubesUtilises, String nom) {
        this.idSaison = idSaison;
        this.nbTubesUtilises = nbTubesUtilises;
        this.nom = nom;
    }
    
    
    
    
}

package com.ritndev.agcv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter @Setter private long id;
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    @Getter @Setter private Timestamp horodatage;
    
    //id de la saison de cette competition
    @OneToOne
    @JoinColumn(name = "idSaison", nullable = false)
    @Getter @Setter private Saison idSaison;
    
    //Nombre de tubes utilisés lors de cette competition
    @Column(name = "nbTubesUtilises", nullable = false)
    @Getter @Setter private int nbTubesUtilises;
    
    //Nom de la competition
    @Column(name = "nom", nullable = false)
    @Getter @Setter private String nom;

    //id du stock de competition
    @OneToOne
    @JoinColumn(name = "idStock", nullable = false)
    @Getter @Setter private MainData idStock;
    
    
    
    //Constructeur
    public Competition() {}

    public Competition(Saison idSaison, MainData idStock, int nbTubesUtilises, String nom) {
        this.idSaison = idSaison;
        this.idStock = idStock;
        this.nbTubesUtilises = nbTubesUtilises;
        this.nom = nom;
    }
    
    /*
        Méthodes
    */
    
    public String getDateCompetition() {
        return new SimpleDateFormat("dd/MM/yyyy").format(horodatage);
    }
    
    
}

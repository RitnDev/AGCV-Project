package com.ritndev.agcv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Ritn
 */
@Entity
@Table(name = "DATA")
public class MainData implements Serializable {
    
    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter @Setter private long id;
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    @Getter @Setter private Timestamp horodatage;
    
    //ID de la Saison en cours
    @OneToOne
    @JoinColumn(name="idSaison", nullable = false)
    @Getter @Setter private Saison idSaison;

    //ID du sstock de competition actuel
    @OneToOne
    @JoinColumn(name = "idStockCompet", nullable = false)
    @Getter @Setter private StockCompetition idStockCompet;
    
    //Valeur du budget previsionnel par défaut
    @Column(name = "budgetDefault", nullable = false)
    @Getter @Setter private String budgetDefault;
    
    //TypeTube Plastique actif
    @OneToOne
    @JoinColumn(name = "idTTPlastique", nullable = false)
    @Getter @Setter private TypeTube idTTPlastique;
    
    //TypeTube Entrainement actif
    @OneToOne
    @JoinColumn(name = "idTTEntrainement", nullable = false)
    @Getter @Setter private TypeTube idTTEntrainement;
    
    //TypeTube Competition actif
    @OneToOne
    @JoinColumn(name = "idTTCompetition", nullable = false)
    @Getter @Setter private TypeTube idTTCompetition;
    
    //ID du sstock de competition actuel
    @Column(name = "actif", nullable = false)
    @Getter @Setter private boolean actif = true;
       
    
    
    //Constructeur
    public MainData() {}
    
    public MainData(long id) {
        this.id = id;
    }

    public MainData(Saison idSaison, StockCompetition idStockCompet, boolean actif) {
        this.idSaison = idSaison;
        this.idStockCompet = idStockCompet;
        this.budgetDefault = "1000.00";
        this.actif = actif;
    }
    
    
    /*
        Methodes
    */
   
    public TypeTube getTypeTubeName(String nom){
        switch(nom){
            case "Plastique" -> {
                return idTTPlastique;
            }
            case "Entrainement" -> {
                return idTTPlastique;
            }
            case "Compétition" -> {
                return idTTPlastique;
            }
        }
        return null;
    }
    
    
}

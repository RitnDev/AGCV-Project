package com.ritndev.agcv.model;

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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
    
    //Valeur du budget previsionnel par défaut
    @Column(name = "budgetDefault", nullable = false)
    @Getter @Setter private double budgetDefault;
    
    //Valeur du seuil bas avant avertissement de restockage (commande club)
    @Column(name = "seuilBas", nullable = false)
    @Getter @Setter private int seuilBas;
    
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
    
    //Liste des competitions de la saison
    @OneToMany(targetEntity=Competition.class, mappedBy="idStock", cascade=CascadeType.REMOVE)
    @Getter @Setter private List<Competition> competitions = new ArrayList<>();
    
    //Liste des competitions de la saison
    @OneToMany(targetEntity=Restock.class, mappedBy="idStock", cascade=CascadeType.REMOVE)
    @Getter @Setter private List<Restock> restocks = new ArrayList<>();
    
    
    
    
    //Constructeur
    public MainData() {}
    
    public MainData(long id) {
        this.id = id;
    }

    public MainData(Saison idSaison, boolean actif) {
        this.idSaison = idSaison;
        this.budgetDefault = 3000.00;
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
    
    //Stock competition
    public int showStock() {
        int moins = 0;
        int plus = 0;
        for(Competition c : competitions){
            moins = moins + c.getNbTubesUtilises();
        }
        for(Restock rs : restocks){
            plus = plus + rs.getValeur();
        }
        return 0 - moins + plus ;
    }
    
}

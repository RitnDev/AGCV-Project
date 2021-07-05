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
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Ritn
 */
@Entity
@Table(name = "TYPE_VOLANT")
public class TypeVolant implements Serializable {
    
    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter @Setter
    private long id;
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    @Getter @Setter
    private Timestamp horodatage;
    
    //id de la saison de cette consomation de tube
    @OneToOne @JoinColumn(name = "idSaison", nullable = false)
    @Getter @Setter private Saison idSaison;
    
    //id du type de tube de cette consomation de tube
    @OneToOne @JoinColumn(name = "idTypeTube", nullable = false)
    @Getter @Setter private TypeTube idTypeTube;
    
    //Nombre de tube avant consommation de volant (stock de départ)
    @Column(name = "stock", nullable = false)
    @Getter @Setter
    private int stock;
    
    
    
    //Les types volants de la saison
    @OneToMany(targetEntity=ConsoMois.class, mappedBy="idTypeVolant", cascade=CascadeType.REMOVE)
    @Getter @Setter private List<ConsoMois> consommationsMois = new ArrayList<>();
    
    
    
    
    //Constructeur
    public TypeVolant() {}

    public TypeVolant(Saison idSaison, TypeTube idTypeTube, int stock) {
        this.idSaison = idSaison;
        this.idTypeTube = idTypeTube;
        this.stock = stock;
    }

    
    
    @Override
    public String toString() {
        return idTypeTube.getNom();
    }
   
    
    
    /*
        Méthodes
    */
    
    //Nom du type tube associé au type volant
    public String getNomTypeTube(){
        return idTypeTube.getNom();
    }
    
    //Renvoie le consoMois correspondant au nomMois
    public ConsoMois getConsoMoisName(String nomMois){
        for(ConsoMois cm : consommationsMois){
            if(cm.toString().equals(nomMois)){
                return cm;
            }
        }
        return null;
    }
    
    //Total du nb de tube utilisé sur une saison pour le type volant
    public int getTotalNbTubesUtilises() {
        int total = 0;
        for(ConsoMois cm : consommationsMois){
            total = total + cm.getNbTubeUtilise();
        }
        return total;
    }
    
    //Total du nb de tube commandé sur une saison pour le type volant
    public int getTotalNbTubesCommandes() {
        int total = 0;
        for(ConsoMois cm : consommationsMois){
            total = total + cm.getNbTubeCommande();
        }
        return total;
    }
    
    //Total du coût des tubes utilisés sur une saison pour le type volant
    public double getTotalCoutUtilises() {
        double total = 0.0;
        for(ConsoMois cm : consommationsMois){
            total = total + cm.getCoutUtilises();
        }
        return total;
    }
    
    //Total du coût des tubes commandés sur une saison pour le type volant
    public double getTotalCoutCommandes() {
        double total = 0.0;
        for(ConsoMois cm : consommationsMois){
            total = total + cm.getCoutCommandes();
        }
        return total;
    }
    
    public int getStockTotal() {
        int total = stock;
        total = total + getTotalNbTubesCommandes() - getTotalNbTubesUtilises();
        return total;
    }
    
}

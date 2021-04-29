package com.ritndev.agcv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Ritn
 */
@Entity
@Table(name="CONSO_MOIS",
        uniqueConstraints = {
                @UniqueConstraint(name = "TYPE_VOLANT_UK", columnNames = {"typevolant_id"})
        })
public class ConsoMois implements Serializable {
    
    //ID
    @Id
    @GeneratedValue
    @Column(name = "conso_id", nullable = false)
    private long id;
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    private Timestamp horodatage;
    public Timestamp getHorodatage() {return horodatage;}
    public void setHorodatage(Timestamp horodatage) {this.horodatage = horodatage;}
    
    @Column(name = "nom_mois", nullable = false)
    private String nomMois;
    public String getNomMois() {return nomMois;};
    public void setNomMois(String nomMois) {this.nomMois = nomMois;};
    
    //Prix du tube pour ce mois ci
    @Column(name = "prix_tube", nullable = false)
    private double prixTube;
    public double getPrixTube() {return prixTube;}
    public void setPrixTube(double prixTube) {this.prixTube = prixTube;}
    
    //Nombre de tubes utilisés ce mois ci
    @Column(name = "tube_utilise", nullable = false)
    private int tubeUtilise;
    public int getTubeUtilise() {return tubeUtilise;}
    public void setTubeUtilise(int tubeUtilise) {this.tubeUtilise = tubeUtilise;}
    
    //Tube commandé par le club ce mois ci
    @Column(name = "tube_commande", nullable = false)
    private int tubeCommande;
    public int getTubeCommande() {return tubeCommande;}
    public void setTubeCommande(int tubeCommande) {this.tubeCommande = tubeCommande;}
    
        
    /*
        --- Méthodes ---
    */
    
    
    //Renvoie le cout des tubes commandés pendant le mois
    public double getCoutTubeCommande(){
        return this.tubeCommande * this.prixTube;
    }
    
    
    //Renvoie le cout des tubes commandés pendant le mois
    public double getCoutTubeUtilise(){
        return this.tubeUtilise * this.prixTube;
    }
    
    
}

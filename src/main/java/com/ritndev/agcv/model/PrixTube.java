package com.ritndev.agcv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Ritn
 */
@Entity
@Table(name = "PRIX_TUBES")
public class PrixTube implements Serializable {
    
    //ID
    @Id
    @GeneratedValue
    @Column(name = "prixtube_id", nullable = false)
    private long id;
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    private Timestamp horodatage;
    public Timestamp getHorodatage() {return horodatage;}
    public void setHorodatage(Timestamp horodatage) {this.horodatage = horodatage;}
    
    //Prix du tube (total)
    @Column(name = "prix", nullable = false)
    private double prix;
    public double getPrix() {return prix;}
    public void setPrix(double prix) {this.prix = prix;}
    
    //Prix du tube pour les membres
    @Column(name = "prix_membre", nullable = false)
    private double prixMembre;
    public double getPrixMembre() {return prixMembre;}
    public void setPrixMembre(double prixMembre) {this.prixMembre = prixMembre;}
    
    //Marque du tube
    @Column(name = "marque")
    private String marque;
    public String getMarque() {return marque;}
    public void setMarque(String marque) {this.marque = marque;}
    
    //type de tube utilisé
    @Column(name = "type_tube", nullable = false)
    private String typeTube;
    public String getTypeTube() {return typeTube;}
    public void setTypeTube(String typeTube) {this.typeTube = typeTube;}
     
    
}

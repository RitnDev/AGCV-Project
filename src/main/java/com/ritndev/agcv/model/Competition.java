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
@Table(name = "COMPETITIONS",
        uniqueConstraints = {
                @UniqueConstraint(name = "SAISON_ID_UK", columnNames = {"saison_id"})
        })
public class Competition implements Serializable {
    
    //ID
    @Id
    @GeneratedValue
    @Column(name = "compet_id", nullable = false)
    private long id;
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    private Timestamp horodatage;
    public Timestamp getHorodatage() {return horodatage;}
    public void setHorodatage(Timestamp horodatage) {this.horodatage = horodatage;}
    
    //Nom de la compétition
    @Column(name = "nom", nullable = false)
    private String nom;
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    
    //Tube utilisés lors de la compétition
    @Column(name = "tube_utilise", nullable = false)
    private int tubeUtilise;
    public int getTubeUtilise() {return tubeUtilise;}
    public void setTubeUtilise(int tubeUtilise) {this.tubeUtilise = tubeUtilise;}

       
}

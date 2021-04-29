package com.ritndev.agcv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
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
@Table(name = "MEMBRES")
public class Membre implements Serializable {
    
    //ID
    @Id
    @GeneratedValue
    @Column(name = "membre_id", nullable = false)
    private long id;
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    private Timestamp horodatage;
    public Timestamp getHorodatage() {return horodatage;}
    public void setHorodatage(Timestamp horodatage) {this.horodatage = horodatage;}
    
    //Prenom du membre
    @Column(name = "prenom", length = 45, nullable = false)
    private String prenom;
    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    
    //Nom du membre
    @Column(name = "nom", length = 36, nullable = false)
    private String nom;
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    
    
    //Constructeur   
    public Membre(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }
    
    public Membre() {}
    
    
    
    @Override
    public String toString() {
        return prenom + " " + nom;
    }
   
    @Override
    public boolean equals(Object obj) {
        if (obj == null){return false;}
        if (this == null){return false;}
        
        String obj1 = this.toString();
        String obj2 = obj.toString();

        return obj1.equals(obj2);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.prenom);
        hash = 37 * hash + Objects.hashCode(this.nom);
        return hash;
    }
       
}

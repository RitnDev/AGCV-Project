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
@Entity(name = "type_tube")
@Table(name = "TYPE_TUBE")
public class TypeTube implements Serializable {
    
    //ID
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    @Getter @Setter
    private long id;
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    @Getter @Setter private Timestamp horodatage;
      
    //Nom du type de tube
    @Column(name = "nom", nullable = false)
    @Getter @Setter
    private String nom;
    
    //Est-ce que ce type de tube est encore utilisé ?
    @Column(name = "actif", nullable = false)
    @Getter @Setter
    private boolean actif;
    
    //Est-ce que ce type de tube est commandable pour les membres ?
    @Column(name = "commande", nullable = false)
    @Getter @Setter
    private boolean commande;
    
    
    //Constructeur
    public TypeTube() {}

    public TypeTube(String nom, boolean actif, boolean commande) {
        this.nom = nom;
        this.actif = actif;
        this.commande = commande;
    }
       
    
    public TypeTube(long id, String nom, boolean actif, boolean commande) {
        this.id = id;
        this.nom = nom;
        this.actif = actif;
        this.commande = commande;
    }

    
    
    @Override
    public String toString() {
        return nom;
    }
    
    
    
}

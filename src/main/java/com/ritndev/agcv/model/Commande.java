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
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author Ritn
 */
@Entity
@Table(name = "COMMANDE")
public class Commande implements Serializable {
    
    //ID
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    @Getter @Setter
    private long id;
    
    //Horodatage (mise à jour)
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    @Getter @Setter
    private Timestamp horodatage;
    
    //Date de creation
    @CreationTimestamp
    @Column(name = "dateCreation", nullable = false)
    @Getter @Setter
    private Timestamp dateCrea;
    
    //id de la saison de cette commande
    @Column(name = "idSaison", nullable = false)
    @Getter @Setter
    private long idSaison;
    
    //id du prix tube correspondant à cette commande
    @Column(name = "idPrixTube", nullable = false)
    @Getter @Setter
    private long idPrixTube;
    
    //id du membre qui commande
    @Column(name = "idMembre", nullable = false)
    @Getter @Setter
    private long idMembre;
    
    //Nombre de tubes commandés lors de cette commande
    @Column(name = "nbTubeCommande", nullable = false)
    @Getter @Setter
    private int nbTubeCommande;
    
    //la commande est-elle réglée ?
    @Column(name = "regler", nullable = false)
    @Getter @Setter
    private boolean regler;
    
    
    
    //Constructeur
    public Commande() {}
    
        
}

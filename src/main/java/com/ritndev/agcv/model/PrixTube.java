package com.ritndev.agcv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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
@Entity(name = "prix_tube")
@Table(name = "PRIX_TUBE")
public class PrixTube implements Serializable {
    
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
    
    //id du type de tube de ce prix tube
    @OneToOne
    @JoinColumn(name = "idTypeTube", nullable = false)
    @Getter @Setter
    private TypeTube idTypeTube;
    
    //Marque du tube
    @Column(name = "marque")
    @Getter @Setter
    private String marque;
    
    //Prix d'achat
    @Column(name = "prix", nullable = false)
    @Getter @Setter
    private double prix;
    
    //prix pour les membres
    @Column(name = "prixMembre", nullable = false)
    @Getter @Setter
    private double prixMembre;
    
    //Si ce prix est toujours utilisé pour les commandes ?
    @Column(name = "actif", nullable = false)
    @Getter @Setter
    private boolean actif;
    
    
    //Les types volants de la saison
    @OneToMany(targetEntity=ConsoMois.class, mappedBy="idPrixTube")
    @Getter @Setter private List<ConsoMois> consommationsMois = new ArrayList<>();
    
    
    
    
    //Constructeur
    public PrixTube() {}

    public PrixTube(String marque, double prix, double prixMembre, TypeTube idTypeTube, boolean actif) {
        this.idTypeTube = idTypeTube;
        this.marque = marque;
        this.prix = prix;
        this.prixMembre = prixMembre;
        this.actif = actif;
    }
    
    
    /*
        Methodes
    */
    
    //Renvoie le prix au format String
    public String getPrixString(){
        return String.valueOf(prix);
    }
    
    //Renvoie le prix au format String
    public String getPrixMembreString(){
        return String.valueOf(prixMembre);
    }
    
    //Pour les commentaire de tableau
    public String getPrixComment(){
        return prix + " | " + prixMembre;
    }
       
    
    @Override
    public String toString() {
        if (!marque.equals("")){
            return idTypeTube.toString() + " (" + marque +")";
        }else{
            return idTypeTube.toString();
        }
    }
    
    
    
}

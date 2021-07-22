package com.ritndev.agcv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Ritn
 */
@Entity
@Table(name = "MEMBRE")
public class Membre implements Serializable {
    
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
    
    //Prenom du membre
    @Column(name = "prenom", nullable = false)
    @Getter @Setter
    private String prenom;
    
    //Nom du membre
    @Column(name = "nom", nullable = false)
    @Getter @Setter
    private String nom;
    
    //Si ce membre est toujours utilisé pour les commandes ?
    @Column(name = "actif", nullable = false)
    @Getter @Setter
    private boolean actif;
    
    //Liste des commande du membre
    @OneToMany(targetEntity=Commande.class, mappedBy="idMembre")
    @Getter @Setter private List<Commande> commandes = new ArrayList<>();
    
    
    
    //Constructeur
    public Membre() {}

    public Membre(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
        this.actif = true;
    }

    
    //toString
    @Override public String toString() {return prenom + " " + nom;}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.prenom);
        hash = 31 * hash + Objects.hashCode(this.nom);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){return false;}
        if (this == null){return false;}
        
        String obj1 = this.toString();
        String obj2 = obj.toString();

        return obj1.equals(obj2);
    }

    
    
}

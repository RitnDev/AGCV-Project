package com.ritndev.agcv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "RESTOCK")
public class Restock implements Serializable {
    
    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter @Setter private long id;
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    @Getter @Setter private Timestamp horodatage;
    
    //Valeur de restockage
    @Column(name = "valeur", nullable = false)
    @Getter @Setter private int valeur;

    //id du stock de competition
    @OneToOne
    @JoinColumn(name = "idSaison", nullable = false)
    @Getter @Setter private Saison idSaison;
    
    //id du stock de competition
    @OneToOne
    @JoinColumn(name = "idStock", nullable = false)
    @Getter @Setter private MainData idStock;
    
    //id du conso-mois associé
    @OneToOne
    @JoinColumn(name = "idConsoMois", nullable = false)
    @Getter @Setter private ConsoMois idConsoMois;
    
    
    //Constructeur
    public Restock() {}

    public Restock(int valeur, Saison idSaison, MainData idStock, ConsoMois idConsoMois) {
        this.valeur = valeur;
        this.idStock = idStock;
        this.idSaison = idSaison;
        this.idConsoMois = idConsoMois;
    }
    
    
   
    
}

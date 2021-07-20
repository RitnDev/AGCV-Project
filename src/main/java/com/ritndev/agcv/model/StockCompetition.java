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
@Table(name = "STOCK")
public class StockCompetition implements Serializable {
    
    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter @Setter private long id;
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    @Getter @Setter private Timestamp horodatage;
    
    //Stock pour les competition actuel
    @Column(name = "stock", nullable = false)
    @Getter @Setter private int stock;
    
    //Liste des competitions de la saison
    @OneToMany(targetEntity=Competition.class, mappedBy="idStock", cascade=CascadeType.REMOVE)
    @Getter @Setter private List<Competition> competitions = new ArrayList<>();
    
    
    //Constructeur
    public StockCompetition() {
        this.stock = 0;
    }

    public StockCompetition(long id, int stock) {
        this.id = id;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "StockCompetition{" + "id=" + id + '}';
    }
   
    
    
    /*
        Methodes
    */
    
    public int showStock() {
        int value = 0;
        for(Competition c : competitions){
            value = value + c.getNbTubesUtilises();
        }
        return stock - value;
    }
    
}

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
@Entity
@Table(name = "STOCK")
public class StockCompetition implements Serializable {
    
    //ID
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    @Getter @Setter private long id;
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    @Getter @Setter private Timestamp horodatage;
    
    //Stock pour les competition actuel
    @Column(name = "stock", nullable = false)
    @Getter @Setter private int stock;
        
    
    //Constructeur
    public StockCompetition() {
        this.stock = 0;
    }

    public StockCompetition(long id, int stock) {
        this.id = id;
        this.stock = stock;
    }
   
}

package com.ritndev.agcv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Ritn
 */
@Entity
@Table(name = "DATA")
public class MainData implements Serializable {
    
    //ID
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    @Getter @Setter
    private long id;
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    @Getter @Setter
    private Timestamp horodatage;
    
    //ID de la Saison en cours
    @Column(name = "idSaison", nullable = false)
    @Getter @Setter
    private long idSaison;

    //ID du sstock de competition actuel
    @Column(name = "idStockCompet", nullable = false)
    @Getter @Setter
    private long idStockCompet;
    
    //ID du sstock de competition actuel
    @Column(name = "actif", nullable = false)
    @Getter @Setter
    private boolean actif = true;
    
    
    
    //Constructeur
    public MainData() {}
    
    public MainData(long id) {
        this.id = id;
    }

    public MainData(long idSaison, long idStockCompet, boolean actif) {
        this.idSaison = idSaison;
        this.idStockCompet = idStockCompet;
        this.actif = actif;
    }
    
    
    
    
}

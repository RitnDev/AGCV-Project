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
@Table(name = "CONSO_TUBE")
public class ConsoTube implements Serializable {
    
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
    
    //id de la saison de cette consomation de tube
    @Column(name = "idSaison", nullable = false)
    @Getter @Setter
    private long idSaison;
    
    //id du type de tube de cette consomation de tube
    @Column(name = "idTypeTube", nullable = false)
    @Getter @Setter
    private long idTypeTube;
    
    //Nombre de tube avant consommation de volant (stock de départ)
    @Column(name = "initTube", nullable = false)
    @Getter @Setter
    private int initTube;
    
    
    
    //Constructeur
    public ConsoTube() {}
    
    
}

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
@Entity
@Table(name = "TYPE_VOLANT")
public class TypeVolant implements Serializable {
    
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
    
    //id de la saison de cette consomation de tube
    @OneToOne @JoinColumn(name = "idSaison", nullable = false)
    @Getter @Setter private Saison idSaison;
    
    //id du type de tube de cette consomation de tube
    @OneToOne @JoinColumn(name = "idTypeTube", nullable = false)
    @Getter @Setter private TypeTube idTypeTube;
    
    //Nombre de tube avant consommation de volant (stock de départ)
    @Column(name = "initTube", nullable = false)
    @Getter @Setter
    private int initTube;
    
    
    
    //Les types volants de la saison
    @OneToMany(targetEntity=ConsoMois.class, mappedBy="idTypeVolant")
    @Getter @Setter private List<ConsoMois> consommationsMois = new ArrayList<>();
    
    
    
    
    //Constructeur
    public TypeVolant() {}

    public TypeVolant(Saison idSaison, TypeTube idTypeTube, int initTube) {
        this.idSaison = idSaison;
        this.idTypeTube = idTypeTube;
        this.initTube = initTube;
    }
   
}

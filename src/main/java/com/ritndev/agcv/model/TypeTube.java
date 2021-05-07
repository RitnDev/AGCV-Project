package com.ritndev.agcv.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Ritn
 */
@Entity
@Table(name = "TYPE_TUBE")
public class TypeTube implements Serializable {
    
    //ID
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    @Getter @Setter
    private long id;
      
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
    
    
}

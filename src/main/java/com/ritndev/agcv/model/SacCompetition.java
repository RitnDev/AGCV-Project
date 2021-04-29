package com.ritndev.agcv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Ritn
 */
@Entity
@Table(name = "SAC_COMPETITION")
public class SacCompetition implements Serializable {
    
    //ID
    @Id
    @GeneratedValue
    @Column(name = "sac_id", nullable = false)
    private long id;
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    private Timestamp horodatage;
    public Timestamp getHorodatage() {return horodatage;}
    public void setHorodatage(Timestamp horodatage) {this.horodatage = horodatage;}
    
    //Nombre de tubes
    @Column(name = "nb_tube", nullable = false)
    private int nbTube;
    public int getNbTube() {return nbTube;}
    public void setNbTube(int nbTube) {this.nbTube = nbTube;}

        
    /*
        ---  Methodes  ---
    */
    
    // Ajoute des tubes en stock :
    public boolean addTube(int nbTube){
        this.nbTube = this.nbTube + nbTube;
        return true;
    }
    
    //Retire des tubes en stock :
    public boolean removeTube(int nbTube){
        int memTube = this.nbTube;
        if((memTube - nbTube)<0)
        {   return false;}
        else
        {
            this.nbTube = memTube - nbTube;
            return true;
        }
    } 
    
}

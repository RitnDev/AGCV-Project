package com.ritndev.agcv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.UpdateTimestamp;


/**
 *
 * @author Ritn
 */
@Entity
@Table(name = "TYPE_VOLANT",
        uniqueConstraints = {
                @UniqueConstraint(name = "SAISON_ID_UK", columnNames = {"saison_id"})
        })
public class TypeVolant implements Serializable {
    
    //ID
    @Id
    @GeneratedValue
    @Column(name = "typevolant_id", nullable = false)
    private long id;
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    private Timestamp horodatage;
    public Timestamp getHorodatage() {return horodatage;}
    public void setHorodatage(Timestamp horodatage) {this.horodatage = horodatage;}
    
    //Stock initial en début de saison
    @Column(name = "stock", nullable = false)
    private int stockTubeInit;
    public int getStockTubeInit() {return stockTubeInit;}
    public void setStockTubeInit(int stockTubeInit) {this.stockTubeInit = stockTubeInit;}
    
    //type de tube utilisé
    @Column(name = "type_tube", nullable = false)
    private String typeTube;
    public String getTypeTube() {return typeTube;}
    public void setTypeTube(String typeTube) {this.typeTube = typeTube;}
        
    @ElementCollection
    @CollectionTable(name = "conso_mois", joinColumns = @JoinColumn(name = "typevolant_id"))
    @OneToMany(fetch = FetchType.LAZY)
    private List<ConsoMois> listMois = new ArrayList<ConsoMois>();
    public List<ConsoMois> getListMois() {return listMois;}
    public void setListMois(List<ConsoMois> listMois) {this.listMois = listMois;}

    
    /*
        --- Methodes ---
    */
    
    //Restockage de tube pour un mois particulier :
    /*
    public void restockTube(int nbTube, String nomMois){
        this.listMois.get(nomMois).setTubeCommande(this.listMois.get(nomMois).getTubeCommande() + nbTube);
    }
    */
    
    //Cout de toutes les commandes sur tous les mois :
    /*
    public double getCoutCommande(){
        double coutCommande = 0.0;
        for(ConsoMois mois : this.listMois.values()){
            coutCommande = coutCommande + mois.getCoutTubeCommande();
        }
        return coutCommande;
    }
    */
    
    //Cout de toutes les commandes sur tous les mois :
    /*
    public double getCoutTubeUtilise(){
        double coutUtilise = 0.0;
        for(ConsoMois mois : this.listMois.values()){
            coutUtilise = coutUtilise + mois.getCoutTubeUtilise();
        }
        return coutUtilise;
    }
    */
    
    //Nombre total de tubes utilises sur tous les mois.
    /*
    public int getTotalTubeUtilise(){
        int totalTubeUtilise = 0;
        for(ConsoMois mois : this.listMois.values()){
            totalTubeUtilise = totalTubeUtilise + mois.getTubeUtilise();
        }
        return totalTubeUtilise;
    }
    */

    //Nombre total de tubes utilises sur tous les mois.
    /*
    public int getTotalTubeCommande(){
        int totalTubeCommande = 0;
        for(ConsoMois mois : this.listMois.values()){
            totalTubeCommande = totalTubeCommande + mois.getTubeCommande();
        }
        return totalTubeCommande;
    }
    */
    
    //Nombre total de tubes restant en stock :
    /*
    public int getTotalTubeRestant(){
        return getTotalTubeCommande() - getTotalTubeUtilise() + stockTubeInit;
    }
    */
}

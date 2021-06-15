package com.ritndev.agcv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author Ritn
 */
@Entity
@Table(name = "COMMANDE")
public class Commande implements Serializable {
    
    //ID
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    @Getter @Setter
    private long id;
    
    //Horodatage (mise à jour)
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    @Getter @Setter
    private Timestamp horodatage;
    
    //Date de creation
    @CreationTimestamp
    @Column(name = "dateCreation", nullable = false)
    @Getter @Setter
    private Timestamp dateCrea;
    
    //id de la saison de cette commande
    @Column(name = "idSaison", nullable = false)
    @Getter @Setter
    private long idSaison;
    
    //id du prix tube correspondant à cette commande
    @Column(name = "idPrixTube", nullable = false)
    @Getter @Setter
    private long idPrixTube;
    
    //id du membre qui commande
    @Column(name = "idMembre", nullable = false)
    @Getter @Setter
    private long idMembre;
    
    //Nombre de tubes commandés lors de cette commande
    @Column(name = "nbTubeCommande", nullable = false)
    @Getter @Setter
    private int nbTubeCommande;
    
    //la commande est-elle réglée ?
    @Column(name = "regler", nullable = false)
    @Setter
    private boolean regler;
    public boolean isRegler() {return regler;}
    
    
    
    //Constructeur
    public Commande() {}

    public Commande(long idSaison, long idPrixTube, long idMembre, int nbTubeCommande, boolean regler) {
        this.idSaison = idSaison;
        this.idPrixTube = idPrixTube;
        this.idMembre = idMembre;
        this.nbTubeCommande = nbTubeCommande;
        this.regler = regler;
    }
 
    
    /*
        Methodes
    */
    
    public String getRegler() {
        String strResult = "Non";
        if(regler) strResult = "Oui";
        return strResult;
    }
    
    public String getSaisonName(List<Saison> saisons) {
        String strResult = "";
        for (Saison s : saisons){
            if(s.getId()==idSaison){
                strResult = s.toString();
            }
        }
        return strResult;
    }
    
    public String getMembreName(List<Membre> membres) {
        String strResult = "";
        for (Membre m : membres){
            if(m.getId()==idMembre){
                strResult = m.toString();
            }
        }
        return strResult;
    }
    
    public String getPrixTube(List<PrixTube> prixTubes) {
        String strResult = "";
        for (PrixTube pt : prixTubes){
            if(pt.getId()==idPrixTube){
                strResult = pt.toString();
            }
        }
        return strResult;
    }
    
    public String getPrixTubeClub(List<PrixTube> prixTubes) {
        String strResult = "";
        for (PrixTube pt : prixTubes){
            if(pt.getId()==idPrixTube){
                strResult = pt.getPrixString();
            }
        }
        return strResult;
    }
    public String getPrixTubeMembre(List<PrixTube> prixTubes) {
        String strResult = "";
        for (PrixTube pt : prixTubes){
            if(pt.getId()==idPrixTube){
                strResult = pt.getPrixMembreString();
            }
        }
        return strResult;
    }
    
    public double getPrixCommande(List<PrixTube> prixTubes) {
        double dbResult = nbTubeCommande;
        for (PrixTube pt : prixTubes){
            if(pt.getId()==idPrixTube){
                dbResult = dbResult * pt.getPrixMembre();
            }
        }
        return dbResult;
    }
    
    
}

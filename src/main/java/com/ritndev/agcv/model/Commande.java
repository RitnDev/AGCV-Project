package com.ritndev.agcv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Ritn
 */
@Entity
@Table(name = "COMMANDES", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "PRIX_TUBE_UK", columnNames = {"prixtube_id"}),
                @UniqueConstraint(name = "MEMBRE_UK", columnNames = {"membre_id"}),
                @UniqueConstraint(name = "SAISON_ID_UK", columnNames = {"saison_id"})
        })
public class Commande implements Serializable {
    
    //ID
    @Id
    @GeneratedValue
    @Column(name = "commande_id", nullable = false)
    private long id;
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    private Timestamp horodatage;
    public Timestamp getHorodatage() {return horodatage;}
    public void setHorodatage(Timestamp horodatage) {this.horodatage = horodatage;}
    
    //Prix du tube commandé
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prixtube_id", nullable = false)
    private PrixTube prixTube;
    public PrixTube getPrixTube() {return prixTube;}
    public void setPrixTube(PrixTube prixTube) {this.prixTube = prixTube;}
    
    //Membre qui a commandé des tubes
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membre_id", nullable = false)
    private Membre membre;
    public Membre getMembre() {return membre;}
    public void setMembre(Membre membre) {this.membre = membre;}
    
    //Nombre de tubes commandés
    @JoinColumn(name = "nb_tube_commande", nullable = false)
    private int nbTubeCommande;
    public int getNbTubeCommande() {return nbTubeCommande;}
    public void setNbTubeCommande(int nbTubeCommande) {this.nbTubeCommande = nbTubeCommande;}
    
    //Est-ce que la commande a été réglée ?
    @JoinColumn(name = "regler", nullable = false)
    private boolean regler;
    public boolean isRegler() {return regler;}
    public void setRegler(boolean regler) {this.regler = regler;}

    
     
    /*
        --- Methodes ---
    */
    
    
    public double getCoutCommandeMembre(){
        return this.prixTube.getPrixMembre() * this.nbTubeCommande;
    }
    
    public double getCoutCommandeClub(){
        return this.prixTube.getPrix() * this.nbTubeCommande;
    }
    
}

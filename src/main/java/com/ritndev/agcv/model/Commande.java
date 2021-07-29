package com.ritndev.agcv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    
    //id du membre qui commande
    @OneToOne
    @JoinColumn(name = "idMembre", nullable = false)
    @Getter @Setter
    private Membre idMembre;
    
    //Nombre de tubes commandés lors de cette commande
    @Column(name = "nbTubeCommande", nullable = false)
    @Getter @Setter
    private int nbTubeCommande;
    
    //la commande est-elle réglée ?
    @Column(name = "regler", nullable = false)
    @Setter
    private boolean regler;
    public boolean isRegler() {return regler;}
    
    
    
    //id de la saison de cette commande
    @OneToOne
    @JoinColumn(name = "idSaison", nullable = false)
    @Getter @Setter private Saison idSaison;
    
    //id du mois de consommation de cette commande
    @OneToOne
    @JoinColumn(name = "idConsoMois", nullable = false)
    @Getter @Setter private ConsoMois idConsoMois;
    
    //id du prix tube correspondant à cette commande
    @OneToOne
    @JoinColumn(name = "idPrixTube", nullable = false)
    @Getter @Setter private PrixTube idPrixTube;
    
    
    
    
    //Constructeur
    public Commande() {}

    
    public Commande(Membre idMembre, int nbTubeCommande, boolean regler, Saison idSaison, ConsoMois idConsoMois, PrixTube idPrixTube) {
        this.idMembre = idMembre;
        this.nbTubeCommande = nbTubeCommande;
        this.regler = regler;
        this.idSaison = idSaison;
        this.idConsoMois = idConsoMois;
        this.idPrixTube = idPrixTube;
    }

    
    /*
        Methodes
    */
    
    public String getRegler() {
        String strResult = "Non";
        if(regler) strResult = "Oui";
        return strResult;
    }
    
    public String getDateCommande() {
        return new SimpleDateFormat("dd/MM/yyyy").format(horodatage);
    }
    
    //Prix de la commande pour le membre
    public double getPrixCommandeMembre() {
        return nbTubeCommande * idPrixTube.getPrixMembre();
    }
    public String getStringPrixCommandeMembre() {
        return String.format("%.2f", getPrixCommandeMembre());
    }
    
    //Prix de la commande pour le club
    public double getPrixCommandeClub() {
        return nbTubeCommande * idPrixTube.getPrix();
    }
    public String getStringPrixCommandeClub() {
        return String.format("%.2f", getPrixCommandeClub());
    }
    
    //Montant du volant payer par le club
    public double getDeductionClub() {
        return getPrixCommandeClub() - getPrixCommandeMembre();
    }
    
    //Reste de la commande a payé
    public double getMontantRestant() {
        if (regler) {
            return 0.0;
        }else{
            return getPrixCommandeMembre();
        }
    }
    public String getStringMontantRestant() {
        return String.format("%.2f", getMontantRestant());
    }
    
    //renvoie la class html en fonction si la commande est réglée ou non
    public String getClassRegler() {
        String strClass = "td-regler";
        if(!regler) strClass = "td-non-regler";
        return strClass;
    }
    
    
}

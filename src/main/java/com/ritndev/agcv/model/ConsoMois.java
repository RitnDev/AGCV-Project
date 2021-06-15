package com.ritndev.agcv.model;

import com.ritndev.agcv.model.enumeration.NomMois;
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

/**
 *
 * @author Ritn
 */
@Entity
@Table(name = "CONSO_MOIS")
public class ConsoMois implements Serializable {
    
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
    
    //Nom du mois de cette consommation
    @Column(name = "nom", nullable = false)
    @Getter @Setter
    private String nom;
    
    //id du prix tube utilises lors de ce mois-ci
    @Column(name = "idPrixTube", nullable = false)
    @Getter @Setter
    private long idPrixTube;
    
    //id de la reference à la conse de tube (type de tube utilise)
    @Column(name = "idTypeVolant", nullable = false)
    @Getter @Setter
    private long idTypeVolant;
    
    //Nombre de tubes utilises ce mois-ci
    @Column(name = "nbTubeUtilise", nullable = false)
    @Getter @Setter
    private int nbTubeUtilise;
    
    //Nombre de tubes commandé ce mois-ci
    @Column(name = "nbTubeCommande", nullable = false)
    @Getter @Setter
    private int nbTubeCommande;
    
    
    
    //Constructeur
    public ConsoMois() {}

    public ConsoMois(String nom, long idPrixTube, long idTypeVolant, int nbTubeUtilise, int nbTubeCommande) {
        this.nom = nom;
        this.idPrixTube = idPrixTube;
        this.idTypeVolant = idTypeVolant;
        this.nbTubeUtilise = nbTubeUtilise;
        this.nbTubeCommande = nbTubeCommande;
    }
    
    
    
    /*
        Methodes
    */
    
    //Renvoie le nom du type de tube
    public String getTypeVolantName(List<TypeVolant> typeVolants, List<TypeTube> typeTubes) {
        String strResult = "";
        for (TypeVolant tv : typeVolants){
            if(tv.getId()==idTypeVolant){
                strResult = tv.getTypeTubeName(typeTubes);
            }
        }
        return strResult;
    }
    
    //Renvoie le nom du type de tube
    public String getPrixTubePrix(List<PrixTube> prixTubes) {
        String strResult = "";
        for (PrixTube pt : prixTubes){
            if(pt.getId()==idPrixTube){
                strResult = pt.getPrixString();
            }
        }
        return strResult;
    }
    
    
}

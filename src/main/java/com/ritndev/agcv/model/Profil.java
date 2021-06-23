package com.ritndev.agcv.model;

import java.sql.Timestamp;
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

/**
 *
 * @author Ritn
 * Class test du OneToOne
 */
@Entity
@Table(name = "PROFIL")
public class Profil {
    
    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter @Setter private long id;
    
    //Horodatage
    @UpdateTimestamp
    @Column(name = "horodatage", nullable = false)
    @Getter @Setter private Timestamp horodatage;
    
    //Pseudo
    @Column(name = "pseudo", nullable = false)
    @Getter @Setter private String pseudo;
    
    //Membre
    @OneToOne 
    @JoinColumn(name="idMembre", nullable=false )
    //@Column(name = "idMembre")
    @Getter @Setter private Membre idMembre;

    public Profil() {
    }

    public Profil(String pseudo, Membre idMembre) {
        this.pseudo = pseudo;
        this.idMembre = idMembre;
    }
    
        
}

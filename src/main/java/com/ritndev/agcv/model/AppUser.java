package com.ritndev.agcv.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
 
@Entity
@Table(name = "APP_USER")
public class AppUser implements Serializable {
 
    private static final String ID_USER_ACTIF_ON = "user-actif-on";
    private static final String ID_USER_ACTIF_OFF = "user-actif-off";
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", nullable = false)
    @Getter @Setter private Long userId;
 
    @Column(name = "USER_NAME", length = 36, nullable = false)
    @Getter @Setter private String userName;
 
    @Column(name = "ENCRYPTED_PASSWORD", length = 128, nullable = false)
    @Getter @Setter private String encrytedPassword;
 
    @Column(name = "ENABLED", length = 1, nullable = false)
    @Getter @Setter private boolean enabled;

    
    //Constructeur
    public AppUser(){}
    
    public AppUser(String userName, String encrytedPassword) {
        this.userName = userName;
        this.encrytedPassword = encrytedPassword;
        this.enabled = true;
    }
 
    /*
        Méthodes
    */
    
    public String getActif() {
        String strResult = "Non";
        if(enabled) strResult = "Oui";
        return strResult;
    }
 
    //renvoie la class html en fonction si l'utilisateur est actif ou non
    public String getIdH() {
        String strClass = ID_USER_ACTIF_ON;
        if(!enabled) strClass = ID_USER_ACTIF_OFF;
        return strClass;
    }
    
}
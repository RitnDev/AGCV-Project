package com.ritndev.agcv.form;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormUser {
    
    @Getter @Setter private Long id;
    @Getter @Setter private String identifiant;
    @Getter @Setter private String mdp;
    @Getter @Setter private String newMdp;
    @Getter @Setter private String confirmMdp;
    @Getter @Setter private Long roleId;
    @Getter @Setter private boolean actif;
    
    //Constructeur
    public FormUser() {}

    public FormUser(Long id, String identifiant, Long roleId, boolean actif) {
        this.id = id;
        this.identifiant = identifiant;
        this.roleId = roleId;
        this.actif = actif;
    }

    public FormUser(Long id, String identifiant) {
        this.id = id;
        this.identifiant = identifiant;
    }
    
    
    
       
}

package com.ritndev.agcv.form;

import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Ritn
 */
public class FormProfil {
    
    @Getter @Setter private long id;
    
    //Pseudo
    @Getter @Setter private String pseudo;
    
    //Membre
    @Getter @Setter private long idMembre;

    
    
    
    
    public FormProfil() {
    }
    
    
}

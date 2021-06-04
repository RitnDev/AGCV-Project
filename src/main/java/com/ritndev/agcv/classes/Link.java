package com.ritndev.agcv.classes;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class Link {
    
    @Getter @Setter private String texte;
    @Getter @Setter private String href;
    @Getter @Setter private String hid;
    @Getter @Setter private String hclass;
    @Getter @Setter private boolean actif = true;
    
    
    //Constructeur
    public Link(String texte, String href) {
        this.texte = texte;
        this.href = href;
    }
    
   
    public Link(String texte, String href, String hid) {
        this.texte = texte;
        this.href = href;
        this.hid = hid;
    }
    
    public Link(String texte, String href, boolean actif) {
        this.texte = texte;
        this.href = href;
        this.actif = actif;
    }
    
    public Link(String hclass, String texte, String href, boolean actif) {
        this.hclass = hclass;
        this.texte = texte;
        this.href = href;
        this.actif = actif;
    }
    

    
    
    //-- toString
    @Override
    public String toString() {
        return texte;
    }
    
    
}

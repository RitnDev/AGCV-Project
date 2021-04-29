package com.ritndev.agcv.classes;

/**
 *
 * @author Ritn
 */
public class Link {
    
    private String texte;
    private String href;
    private String id;
    
    
    //Constructeur
    public Link(String texte, String href) {
        this.texte = texte;
        this.href = href;
    }
    
    public Link(String texte, String href, String id) {
        this.texte = texte;
        this.href = href;
        this.id = id;
    }

    
    // Accesseurs
    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    

    
    //-- toString
    @Override
    public String toString() {
        return texte;
    }
    
    
}

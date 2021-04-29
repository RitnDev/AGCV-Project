package com.ritndev.agcv.model.enumeration;

/**
 *
 * @author Ritn
 */
public enum TypeTube {
    ENTRAINEMENT {
        @Override  
        public String toString() {return "Entrainement";}; 
    },
    COMPETITION {
        @Override  
        public String toString() {return "Compétition";}; 
    },
    PLASTIQUE {
        @Override  
        public String toString() {return "Plastique";}; 
    }
}

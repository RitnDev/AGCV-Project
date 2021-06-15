package com.ritndev.agcv.model.enumeration;


/**
 *
 * @author Ritn
 */
public enum NomTypeTube {
    PLASTIQUE {
        @Override  
        public String toString() {return "Plastique";}; 
    },
    ENTRAINEMENT {
        @Override  
        public String toString() {return "Entrainement";}; 
    },
    COMPETITION {
        @Override  
        public String toString() {return "Compétition";}; 
    }   
}

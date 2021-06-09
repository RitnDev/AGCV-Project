package com.ritndev.agcv.classes;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ritn
 */
public enum ActionsTypes {
    
    //ENUM
    EDIT_MEMBRE(1){
        @Override  
        public String toString() {return "membre";}; 
    },
    EDIT_DATA(2){
        @Override  
        public String toString() {return "data";}; 
    },
    EDIT_SAISON(3){
        @Override  
        public String toString() {return "saison";}; 
    },
    EDIT_COMMANDE(4){
        @Override  
        public String toString() {return "commande";}; 
    },
    EDIT_COMPETITION(5){
        @Override  
        public String toString() {return "compet";}; 
    },
    EDIT_PRIXTUBE(6){
        @Override  
        public String toString() {return "prixtube";}; 
    },
    EDIT_CONSOMOIS(7){
        @Override  
        public String toString() {return "consomois";}; 
    },
    EDIT_STOCK(8){
        @Override  
        public String toString() {return "stock";}; 
    },
    EDIT_TYPETUBE(9){
        @Override  
        public String toString() {return "typetube";}; 
    },
    EDIT_USER(10){
        @Override  
        public String toString() {return "user";}; 
    },
    EDIT_MDP(11){
        @Override  
        public String toString() {return "mdp";}; 
    };
    
    
    
    // return value int
    private int value;
    private static final Map map = new HashMap<>();

    private ActionsTypes(int value) {
        this.value = value;
    }

    static {
        for (ActionsTypes actionsType : ActionsTypes.values()) {
            map.put(actionsType.value, actionsType);
        }
    }

    public static ActionsTypes valueOf(int actionsTypes) {
        return (ActionsTypes) map.get(actionsTypes);
    }

    public int getValue() {
        return value;
    }
}

package com.ritndev.agcv.classes;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ritn
 */
public enum NomService {
    
    //ENUM
    USER(1){
        @Override  
        public String toString() {return "userService";}; 
    },
    DATA(2){
        @Override  
        public String toString() {return "dataService";}; 
    },
    MEMBRE(3){
        @Override  
        public String toString() {return "membreService";}; 
    },
    COMMANDE(4){
        @Override  
        public String toString() {return "commandeService";}; 
    },
    COMPETITION(5){
        @Override  
        public String toString() {return "competitionService";}; 
    },
    PRIXTUBE(6){
        @Override  
        public String toString() {return "prixTubeService";}; 
    },
    CONSOMOIS(7){
        @Override  
        public String toString() {return "consoMoisService";}; 
    },
    STOCK(8){
        @Override  
        public String toString() {return "stockService";}; 
    },
    TYPETUBE(9){
        @Override  
        public String toString() {return "typeTubeService";}; 
    },
    SAISON(10){
        @Override  
        public String toString() {return "saisonService";}; 
    },
    TYPEVOLANT(11){
        @Override  
        public String toString() {return "typeVolantService";}; 
    };
    
    
    
    // return value int
    private int value;
    private static final Map map = new HashMap<>();

    private NomService(int value) {
        this.value = value;
    }

    static {
        for (NomService actionsType : NomService.values()) {
            map.put(actionsType.value, actionsType);
        }
    }

    public static NomService valueOf(int actionsTypes) {
        return (NomService) map.get(actionsTypes);
    }

    public int getValue() {
        return value;
    }
}

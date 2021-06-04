package com.ritndev.agcv.classes;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ritn
 */
public enum ActionsTypes {
    
    //ENUM
    EDIT_MEMBRE(1),
    EDIT_DATA(2),
    EDIT_SAISON(3),
    EDIT_COMMANDE(4),
    EDIT_COMPETITION(5),
    EDIT_PRIXTUBE(6),
    EDIT_CONSOMOIS(7),
    EDIT_STOCK(8),
    EDIT_TYPETUBE(9),
    EDIT_USER(10),
    EDIT_MDP(11);
    
    
    
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritndev.agcv.form;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ritn
 */
public enum ActionsTypes {
    
    //ENUM
    
    EDIT_MEMBRE(1);
    
    
    
    
    
    
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

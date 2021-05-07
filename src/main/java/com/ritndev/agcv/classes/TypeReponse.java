package com.ritndev.agcv.classes;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ritn
 */
public enum TypeReponse {
    
    //ENUM
    OTHER(0),
    INFO(1),
    ADD(2),
    REMOVE(3),
    EDIT(4),
    SUCCESS(4),
    ERROR(5);
    
    
    // return value int
    private int value;
    private static final Map map = new HashMap<>();

    private TypeReponse(int value) {
        this.value = value;
    }

    static {
        for (TypeReponse typeReponse : TypeReponse.values()) {
            map.put(typeReponse.value, typeReponse);
        }
    }

    public static TypeReponse valueOf(int typeReponse) {
        return (TypeReponse) map.get(typeReponse);
    }

    public int getValue() {
        return value;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritndev.agcv.utils;

import java.util.Calendar;

/**
 *
 * @author Ritn
 */
public class MoisUtils {
    
    //Retourne le nom du mois en String afin de comparer avec un NomMois
    //Attention numMois commence à 0 et fini à 11
    public static String getMois(int numMois) {
        switch (numMois) {
            default : return "Janvier";
            case 1 : return "Février";
            case 2 : return "Mars";
            case 3 : return "Avril";
            case 4 : return "Mai";
            case 5 : return "Juin";
            case 6 : return "Juillet";
            case 7 : return "Août";
            case 8 : return "Septembre";
            case 9 : return "Octobre";
            case 10 : return "Novembre";
            case 11 : return "Décembre";
            
        }
    }
    
    //Renvoie le mois actuelle
    public static String moisCourant() {
        return getMois(Calendar.getInstance().get(Calendar.MONTH));
    }
        
}

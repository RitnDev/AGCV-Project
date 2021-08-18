package com.ritndev.AGCV.classes;

import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomString {
    
    
    
    
    
    
    public String randomStringSimple(int length) {
        //Initialisation de la chaine resultat.
        String result = "";
        
        if(length>0){
            result = RandomStringUtils.randomAlphabetic(length);
        }
        
        return result;
    }
    
    public String randomStringSimple(int min, int max) {
        //Initialisation de la chaine resultat.
        String result = "";
        
        if(min>0 && max>min){
            result = RandomStringUtils.randomAlphabetic(min,max);
        }
        
        return result;
    }
    
    public String randomStringEspace(int length) {
        //Initialisation de la chaine resultat.
        String result = "";
        //Initialisation de la chaine de caractere autorisé
        String setOfCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz-";
        //création de la chaine resultat selon la longueur en paramètre
        if(length>0){
            result = RandomStringUtils.random(length, setOfCharacters);
        }      
        return result;
    }
    
    public String randomStringNombre(int length) {
        //Initialisation de la chaine resultat.
        String result = "";
        //Initialisation de la chaine de caractere autorisé
        String setOfCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz0123456789-";
        //création de la chaine resultat selon la longueur en paramètre
        if(length>0){
            result = RandomStringUtils.random(length, setOfCharacters);
        }      
        return result;
    }

}
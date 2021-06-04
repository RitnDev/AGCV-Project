/*
Source : https://devstory.net/11705/creer-une-application-de-connexion-avec-spring-boot-spring-security-jpa
*/

package com.ritndev.agcv.utils;

import java.util.Scanner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

 
public class EncrytedPasswordUtils{
 
    // Encryte Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
    
       


    public static void main(String[] args) {
        
        System.out.println("Entre le mot de passe à crypter (BCrypt): ");  
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream  
        String password = sc.nextLine();    //reads string 
        String encrytedPassword = encrytePassword(password);
        System.out.println("Password: " + password);
        System.out.println("Encryted Password: " + encrytedPassword);
        
    }
    
}
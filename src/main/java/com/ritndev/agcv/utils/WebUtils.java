/*
Source : https://devstory.net/11705/creer-une-application-de-connexion-avec-spring-boot-spring-security-jpa
*/

package com.ritndev.agcv.utils;

import org.springframework.security.core.userdetails.User;
 
public class WebUtils {
 
    public static String toString(User user) {
        StringBuilder sb = new StringBuilder();
 
        sb.append(user.getUsername());

        return sb.toString();
    }
     
}
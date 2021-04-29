package com.ritndev.agcv.services;

// Aide : https://devstory.net/11705/creer-une-application-de-connexion-avec-spring-boot-spring-security-jpa

import java.util.ArrayList;
import java.util.List;
 
import com.ritndev.agcv.repository.AppUserRepository;
import com.ritndev.agcv.model.AppUser;
import com.ritndev.agcv.repository.AppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class customUserDetailsService implements UserDetailsService {
 
    @Autowired
    private AppUserRepository appUserRepository;
 
    @Autowired
    private AppRoleRepository appRoleRepository;
 
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = this.appUserRepository.findUserAccount(userName);
 
        if (appUser == null) {
            System.out.println("User non trouvé ! " + userName);
            throw new UsernameNotFoundException("User " + userName + " ne se trouve pas en base de données");
        }
 
        System.out.println("User trouvé : " + appUser);
 
        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = this.appRoleRepository.getRoleNames(appUser.getUserId());
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
 
        UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
                appUser.getEncrytedPassword(), grantList);
 
        return userDetails;
    }
 
}

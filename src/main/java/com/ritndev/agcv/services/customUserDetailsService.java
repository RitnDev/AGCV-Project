package com.ritndev.agcv.services;

// Aide : https://devstory.net/11705/creer-une-application-de-connexion-avec-spring-boot-spring-security-jpa

import com.ritndev.agcv.form.FormUser;
import com.ritndev.agcv.model.AppRole;
import java.util.ArrayList;
import java.util.List;
 
import com.ritndev.agcv.repository.AppUserRepository;
import com.ritndev.agcv.model.AppUser;
import com.ritndev.agcv.model.UserRole;
import com.ritndev.agcv.repository.AppRoleRepository;
import com.ritndev.agcv.repository.UserRoleRepository;
import com.ritndev.agcv.utils.EncrytedPasswordUtils;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class customUserDetailsService implements UserDetailsService, IUserService {
 
    @Autowired
    private AppUserRepository appUserRep;
    
    @Autowired
    private UserRoleRepository userRoleRep;
 
    @Autowired
    private AppRoleRepository appRoleRep;
 
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = appUserRep.findByUserName(userName);//this.appUserRepository.findUserAccount(userName);
 
        if (appUser == null || !appUser.isEnabled()) {
            System.out.println("User non trouvé ! " + userName);
            throw new UsernameNotFoundException("User " + userName + " ne se trouve pas en base de données");
        }
        
 
        System.out.println("User trouvé : " + appUser);
        System.out.println("UserName : " + appUser.getUserName());

        // [ROLE_USER, ROLE_ADMIN,..]
                
        String role = appRoleRep.findByRoleId(userRoleRep.findByUserId(appUser.getUserId()).getRoleId()).getRoleName();
        System.out.println("ROLE NAME trouvé : " + role);

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (role != null) {
            // ROLE_USER, ROLE_ADMIN,..
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            grantList.add(authority);
        }
 
        UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
                appUser.getEncrytedPassword(), grantList);
 
        return userDetails;
    }

    //Creation d'un nouvel utilisateur
    @Override public int saveUser(FormUser newUser) {
        int resultVal = 0; //Utilisateur non enregistré
        if (newUser != null
            && !newUser.getIdentifiant().equals("")
            && !newUser.getMdp().equals("")
            && !appUserRep.existsByUserName(newUser.getIdentifiant())) {
                AppUser au = appUserRep.save(new AppUser(newUser.getIdentifiant(),EncrytedPasswordUtils.encrytePassword(newUser.getMdp())));
                userRoleRep.save(new UserRole(au.getUserId(), newUser.getRoleId()));
                resultVal = 1; //Utilisateur enregistré avec son role
        }
        return resultVal;
    }
    
    //Liste des utilisateurs
    @Override public List<AppUser> listUser() {return appUserRep.findAll();}
    
    //Renvoie un utilisateur par son ID
    @Override public AppUser findByIdUser(Long id) {
        if (appUserRep.existsById(id)) {
            return appUserRep.getOne(id);
        }
        return null;
    }
    
    //Supprime un utilisateur par son ID
    @Override public int supprUser(Long id) {
        int resultVal = 0;
        if (appUserRep.existsById(id)) {
            if(!appUserRep.getOne(id).getUserName().equals("ritn")){
              appUserRep.deleteById(id);  
            }
        }
        return resultVal;
    }
    
    //Mise à jour d'un utilisateur ou de son role
    @Override public int updateUser(FormUser editUser) {
        int resultVal = 0; //erreur lors de la mise à jour
        if (appUserRep.existsById(editUser.getId())) {
            AppUser appUser = appUserRep.getOne(editUser.getId());
            
            //Identifiant different de "ritn" pour modification.
            if(!appUser.getUserName().equals("ritn")){
                appUser.setEnabled(editUser.isActif());
                appUserRep.save(appUser);
                resultVal = resultVal + 1; //mise à jour de l'utilisateur ok.
                UserRole userRole = userRoleRep.findByUserId(editUser.getId());
                if (!Objects.equals(userRole.getRoleId(), editUser.getRoleId())) {
                    userRole.setRoleId(editUser.getRoleId());
                    userRoleRep.save(userRole);
                    resultVal = resultVal + 1; //mise à jour du role utilisateur ok
                }
            }  
        }
        return resultVal;
    }
    
    
    //Mise à jour d'un utilisateur ou de son role
    @Override public int updateMdpUser(FormUser editUser) {
        int resultVal = 0; //erreur lors de la mise à jour
        if (appUserRep.existsById(editUser.getId())) {
            AppUser appUser = appUserRep.getOne(editUser.getId());
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (!editUser.getMdp().equals("")){
                if (encoder.matches(editUser.getMdp(), appUser.getEncrytedPassword())) {
                    if (editUser.getNewMdp().equals(editUser.getConfirmMdp())){
                        appUser.setEncrytedPassword(encoder.encode(editUser.getNewMdp()));
                        appUserRep.save(appUser);
                        resultVal = 3; // Mot de passe modifier avec succès
                    }else{
                        resultVal = 2; //Le nouveau mot de passe ne correspond pas avec la confirmation.
                    }
                }else{
                    resultVal = 1; //Mot de passe actuelle ne correspond pas
                }
            }
        }
        return resultVal;
    }
      
    
    
    
    //Listes des roles
    @Override public List<AppRole> listRole() {
        return appRoleRep.findAll();
    }

    //Renvoie le nom du rôle depuis un username (AppUser)
    @Override public String findRoleByUsername(String userName) {
        return appRoleRep.findByRoleId(userRoleRep.findByUserId(appUserRep.findByUserName(userName).getUserId()).getRoleId()).getRoleName();
    }

    //Renvoie le role_Id depuis un User_id
    @Override public Long findRoleIdByUserId(Long userId) {
        return userRoleRep.findByUserId(userId).getRoleId();
    }

    //Renvoie un AppUser à partir d'un Username
    @Override public AppUser findByUsernameUser(String Username) {
        return appUserRep.findByUserName(Username);
    }
    
 
}

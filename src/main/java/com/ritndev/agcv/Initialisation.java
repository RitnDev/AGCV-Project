package com.ritndev.agcv;

import com.ritndev.agcv.model.AppRole;
import com.ritndev.agcv.model.AppUser;
import com.ritndev.agcv.model.MainData;
import com.ritndev.agcv.model.PrixTube;
import com.ritndev.agcv.model.Saison;
import com.ritndev.agcv.model.TypeTube;
import com.ritndev.agcv.model.UserRole;
import com.ritndev.agcv.model.enumeration.NomTypeTube;
import com.ritndev.agcv.repository.AppRoleRepository;
import com.ritndev.agcv.repository.AppUserRepository;
import com.ritndev.agcv.repository.MainDataRepository;
import com.ritndev.agcv.repository.PrixTubeRepository;
import com.ritndev.agcv.repository.SaisonRepository;
import com.ritndev.agcv.repository.TypeTubeRepository;
import com.ritndev.agcv.repository.UserRoleRepository;
import com.ritndev.agcv.utils.EncrytedPasswordUtils;


/**
 *
 * @author Ritn
 */
public class Initialisation {
    
    private MainDataRepository mainDataRep;
    private SaisonRepository saisonRep;
    private TypeTubeRepository typeTubeRep;
    private PrixTubeRepository prixtubeRep;
    private AppRoleRepository roleRep;
    private AppUserRepository userRep;
    private UserRoleRepository userRoleRep;

    // Initialisation des Repositories 
    public void setMainDataRep (MainDataRepository mainDataRep) {this.mainDataRep = mainDataRep;}
    public void setTypeTubeRep (TypeTubeRepository typeTubeRep) {this.typeTubeRep = typeTubeRep;}
    public void setAppRoleMoisRep (AppRoleRepository roleRep) {this.roleRep = roleRep;}
    public void setAppUserRepository (AppUserRepository userRep) {this.userRep = userRep;}
    public void setUserRoleRepository (UserRoleRepository userRoleRep) {this.userRoleRep = userRoleRep;}
    public void setPrixTubeRep (PrixTubeRepository prixtubeRep) {this.prixtubeRep = prixtubeRep;}
    public void setSaisonRep (SaisonRepository saisonRep) {this.saisonRep = saisonRep;}

    
    
    //Constructeur
    public Initialisation() {}
     
    
    //Initialisation d'une saison
    public Saison initSaison() {
        //Saison par défaut
        Saison saisonInit = new Saison();
        //saisonInit.setId(1);
        saisonInit.setAnneeDebut(0);
        saisonInit.setAnneeFin(0);
        saisonInit.setBudget(0.0);
        saisonInit.setActuelle(true);

        return saisonRep.save(saisonInit);
    }
    
    //Initialisation d'un TypeTube
    public TypeTube initTypeTube(String nomTypeTube) {
        return typeTubeRep.save(new TypeTube(nomTypeTube, false));
    }
    
    //Initialisation d'un PrixTube
    public PrixTube initPrixTube(TypeTube tp) {
        PrixTube pt = new PrixTube("", 0.0, 0.0, tp, true);
        pt.setDefaut(true);
        return prixtubeRep.save(pt);
    }
    
    //Initialisation d'une Main-Data
    public MainData initMainData(Saison saisonInit, TypeTube ttEntrainement, TypeTube ttCompet, TypeTube ttPlastique) {
        MainData dataInit = new MainData();
        dataInit.setIdSaison(saisonInit);
        dataInit.setIdTTCompetition(ttCompet);
        dataInit.setIdTTEntrainement(ttEntrainement);
        dataInit.setIdTTPlastique(ttPlastique);
        dataInit.setBudgetDefault(3000.0);
        dataInit.setActif(true);

        return mainDataRep.save(dataInit);
    }
    
    //Initialisation d'un User
    public AppUser initUser(String username) {
        AppUser appUser = new AppUser(username, EncrytedPasswordUtils.encrytePassword(username));
        return userRep.save(appUser);
    }
    
    //Initialisation d'un Role
    public AppRole initRole(String rolename) {
        return roleRep.save(new AppRole(rolename));
    }
    
    //Initialisation d'un UserRole
    public UserRole initUserRole(AppUser appUser, AppRole role) {
        return userRoleRep.save(new UserRole(appUser.getUserId(), role.getRoleId()));
    }
    
    //Initialisation de la database au premier lancement.
    public void initDatabase() {
        
        //Si pas de Main-Data par défaut au lancement
            if (mainDataRep.findByActifTrue().isEmpty()) {
                    
                Saison saisonInit = initSaison();
                
                //TypeTube par défaut
                TypeTube typeTubeTrain = initTypeTube(NomTypeTube.ENTRAINEMENT.toString());
                TypeTube typeTubeCompet = initTypeTube(NomTypeTube.COMPETITION.toString());
                TypeTube typeTubePlast = initTypeTube(NomTypeTube.PLASTIQUE.toString());

                //PrixTube par défaut
                initPrixTube(typeTubeTrain);
                initPrixTube(typeTubeCompet);
                initPrixTube(typeTubePlast);


                //Creation de la Main Data
                initMainData(saisonInit, typeTubeTrain, typeTubeCompet, typeTubePlast);
            
            }
            
            if (userRoleRep.findAll().isEmpty()) {
                
                // Creation des utilisateurs par défaut
                AppUser user = initUser("user");
                AppUser admin = initUser("admin");
                AppUser su = initUser("su");

                // Creation des roles par défaut
                AppRole roleUser = initRole("ROLE_USER");
                AppRole roleAdmin = initRole("ROLE_ADMIN");
                AppRole roleSU = initRole("ROLE_SUPADMIN");
                      
                
                //Creation des liens AppUser et AppRole
                initUserRole(user, roleUser);
                initUserRole(admin, roleAdmin);
                initUserRole(su, roleSU);
                    
            }
        
    }
    
    
    
    
    
    
}

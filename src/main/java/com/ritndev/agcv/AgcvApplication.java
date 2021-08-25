package com.ritndev.AGCV;


import com.ritndev.agcv.Initialisation;
import com.ritndev.agcv.repository.MainDataRepository;
import com.ritndev.agcv.repository.SaisonRepository;
import com.ritndev.agcv.repository.TypeTubeRepository;
import com.ritndev.agcv.repository.AppRoleRepository;
import com.ritndev.agcv.repository.AppUserRepository;
import com.ritndev.agcv.repository.PrixTubeRepository;
import com.ritndev.agcv.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class AgcvApplication {

	@Autowired private MainDataRepository mainDataRep;
        @Autowired private SaisonRepository saisonRep;
        @Autowired private TypeTubeRepository typeTubeRep;
        @Autowired private PrixTubeRepository prixtubeRep;
        @Autowired private AppRoleRepository roleRep;
        @Autowired private AppUserRepository userRep;
        @Autowired private UserRoleRepository userRoleRep;
       
    
        //Démmarage de l'application
        public static void main(String[] args) {
            //Lancement de l'application et création du context :
            ApplicationContext ctx = SpringApplication.run(AgcvApplication.class, args);
            
            //Initialisation de l'application
            Initialisation init = ctx.getBean("initApp", Initialisation.class);
            init.initDatabase();
	}
             
        
        @Bean("initApp")
        public Initialisation initApp() {
            //Creation de l'initialisation
            Initialisation init = new Initialisation();
            //Init des repository necessaire
            init.setAppRoleMoisRep(roleRep);
            init.setAppUserRepository(userRep);
            init.setMainDataRep(mainDataRep);
            init.setPrixTubeRep(prixtubeRep);
            init.setSaisonRep(saisonRep);
            init.setTypeTubeRep(typeTubeRep);
            init.setUserRoleRepository(userRoleRep);
            
            return init;
        }
        
}

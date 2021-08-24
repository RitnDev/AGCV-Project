package com.ritndev.AGCV;


import com.ritndev.agcv.Initialisation;
import com.ritndev.agcv.repository.MainDataRepository;
import com.ritndev.agcv.repository.SaisonRepository;
import com.ritndev.agcv.repository.TypeTubeRepository;
import com.ritndev.agcv.repository.AppRoleRepository;
import com.ritndev.agcv.repository.AppUserRepository;
import com.ritndev.agcv.repository.PrixTubeRepository;
import com.ritndev.agcv.repository.UserRoleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
            SpringApplication.run(AgcvApplication.class, args);
	}
        
        
        @Bean
        InitializingBean sendDatabase() {
            return () -> {
                init();
            };
        }
        
        private void init() {
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
            //Init Database
            init.initDatabase();
        }
}

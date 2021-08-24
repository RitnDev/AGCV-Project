package com.ritndev.AGCV;

import com.ritndev.agcv.Initialisation;
import com.ritndev.agcv.model.Saison;
import com.ritndev.agcv.repository.AppRoleRepository;
import com.ritndev.agcv.repository.AppUserRepository;
import com.ritndev.agcv.repository.MainDataRepository;
import com.ritndev.agcv.repository.PrixTubeRepository;
import com.ritndev.agcv.repository.SaisonRepository;
import com.ritndev.agcv.repository.TypeTubeRepository;
import com.ritndev.agcv.repository.UserRoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


@SpringBootTest
class AgcvApplicationTests {
        
        @Autowired private MainDataRepository mainDataRep;
        @Autowired private SaisonRepository saisonRep;
        @Autowired private TypeTubeRepository typeTubeRep;
        @Autowired private PrixTubeRepository prixtubeRep;
        @Autowired private AppRoleRepository roleRep;
        @Autowired private AppUserRepository userRep;
        @Autowired private UserRoleRepository userRoleRep;
    
        
	@Test
	void contextLoads() {
            
            //Creation de l'initialisation
            Initialisation testInit = new Initialisation();
            //Init des repository necessaire
            testInit.setAppRoleMoisRep(roleRep);
            testInit.setAppUserRepository(userRep);
            testInit.setMainDataRep(mainDataRep);
            testInit.setPrixTubeRep(prixtubeRep);
            testInit.setSaisonRep(saisonRep);
            testInit.setTypeTubeRep(typeTubeRep);
            testInit.setUserRoleRepository(userRoleRep);
            
            
            Saison testSaison = testInit.initSaison();
            Assert.notNull(testSaison, "SaisonInit est null");
            Assert.isTrue(testSaison.isActuelle(), "SaisonInit.isActuelle() doit être à : true");
            Assert.isTrue(testSaison.getAnneeDebut() == 0, "SaisonInit.getAnneeDebut() doit égale à : 0");
            Assert.isTrue(testSaison.getAnneeFin() == 0, "SaisonInit.getAnneeFin() doit égale à : 0");
            
        }
           

}

package com.ritndev.AGCV;

import com.ritndev.agcv.Initialisation;
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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
            
            
            //Test de initSaison
            Saison testSaison = testInit.initSaison();
            Assert.isTrue(testInitSaison(testSaison), "testInitSaison() doit renvoyé : True");
            
            
            //Test des NomTypeTube
            Assert.isTrue(NomTypeTube.ENTRAINEMENT.toString().equals("Entrainement"), 
                    "Le NomTypeTube.ENTRAINEMENT.toString() ne renvoie pas 'Entrainement'");
            Assert.isTrue(NomTypeTube.COMPETITION.toString().equals("Compétition"), 
                    "Le NomTypeTube.COMPETITION.toString() ne renvoie pas 'Compétition'");
            Assert.isTrue(NomTypeTube.PLASTIQUE.toString().equals("Plastique"), 
                    "Le NomTypeTube.PLASTIQUE.toString() ne renvoie pas 'Plastique'");
            
            
            //Test des TypeTube
            TypeTube testTypeTubeTrain = testInit.initTypeTube(NomTypeTube.ENTRAINEMENT.toString());
            Assert.isTrue(testInitTypeTube(testTypeTubeTrain, NomTypeTube.ENTRAINEMENT.toString()), 
                    "testInitTypeTube() doit renvoyé : True");
            TypeTube testTypeTubeCompet = testInit.initTypeTube(NomTypeTube.COMPETITION.toString());
            Assert.isTrue(testInitTypeTube(testTypeTubeCompet, NomTypeTube.COMPETITION.toString()), 
                    "testInitTypeTube() doit renvoyé : True");
            TypeTube testTypeTubePlast = testInit.initTypeTube(NomTypeTube.PLASTIQUE.toString());
            Assert.isTrue(testInitTypeTube(testTypeTubePlast, NomTypeTube.PLASTIQUE.toString()), 
                    "testInitTypeTube() doit renvoyé : True");
            
            
            //Test des PrixTube
            PrixTube testPrixTubeTrain = testInit.initPrixTube(testTypeTubeTrain);
            Assert.isTrue(testInitPrixTube(testPrixTubeTrain, testTypeTubeTrain), 
                    "testInitPrixTube() doit renvoyé : True");
            PrixTube testPrixTubeCompet = testInit.initPrixTube(testTypeTubeCompet);
            Assert.isTrue(testInitPrixTube(testPrixTubeCompet, testTypeTubeCompet), 
                    "testInitPrixTube() doit renvoyé : True");
            PrixTube testPrixTubePlast = testInit.initPrixTube(testTypeTubePlast);
            Assert.isTrue(testInitPrixTube(testPrixTubePlast, testTypeTubePlast), 
                    "testInitPrixTube() doit renvoyé : True");
                        
            
            //Test MainData
            MainData testData = testInit.initMainData(testSaison, testTypeTubeTrain, testTypeTubeCompet, testTypeTubePlast);
            Assert.isTrue(testInitData(testData, testSaison, testTypeTubeTrain, testTypeTubeCompet, testTypeTubePlast), 
                    "testInitData() doit renvoyé : True");
                        
            
            //Test AppUser
            String user = "user";
            String admin = "admin";
            String su = "su";
            
            AppUser testUser = testInit.initUser(user);
            Assert.isTrue(testInitAppUser(testUser, user), 
                    "testInitAppUser() doit renvoyé : True");
            AppUser testAdmin = testInit.initUser(admin);
            Assert.isTrue(testInitAppUser(testAdmin, admin), 
                    "testInitAppUser() doit renvoyé : True");
            AppUser testSU = testInit.initUser(su);
            Assert.isTrue(testInitAppUser(testSU, su), 
                    "testInitAppUser() doit renvoyé : True");
            
            
            //Test AppRole
            String roleUser = "ROLE_USER";
            String roleAdmin = "ROLE_ADMIN";
            String roleSupAdmin = "ROLE_SUPADMIN";
            
            AppRole testRoleUser = testInit.initRole(roleUser);
            Assert.isTrue(testInitAppRole(testRoleUser, roleUser), 
                    "testInitAppRole() doit renvoyé : True");
            AppRole testRoleAdmin = testInit.initRole(roleAdmin);
            Assert.isTrue(testInitAppRole(testRoleAdmin, roleAdmin), 
                    "testInitAppRole() doit renvoyé : True");
            AppRole testRoleSupAdmin = testInit.initRole(roleSupAdmin);
            Assert.isTrue(testInitAppRole(testRoleSupAdmin, roleSupAdmin), 
                    "testInitAppRole() doit renvoyé : True");
            
            
            //Test UserRole
            UserRole testUserRoleUser = testInit.initUserRole(testUser, testRoleUser);
            Assert.isTrue(testInitUserRole(testUserRoleUser, testUser, testRoleUser), 
                    "testInitUserRole() doit renvoyé : True");
            UserRole testUserRoleAdmin = testInit.initUserRole(testAdmin, testRoleAdmin);
            Assert.isTrue(testInitUserRole(testUserRoleAdmin, testAdmin, testRoleAdmin), 
                    "testInitUserRole() doit renvoyé : True");
            UserRole testUserRoleSU = testInit.initUserRole(testSU, testRoleSupAdmin);
            Assert.isTrue(testInitUserRole(testUserRoleSU, testSU, testRoleSupAdmin), 
                    "testInitUserRole() doit renvoyé : True");
            
            
            //Suppression des données pour pouvoir tester le initDatabase() ensuite
            userRoleRep.deleteAll();
            userRep.deleteAll();
            roleRep.deleteAll();
            mainDataRep.deleteAll();
            prixtubeRep.deleteAll();
            typeTubeRep.deleteAll();
            saisonRep.deleteAll();
            
            
            testInit.initDatabase();
            Assert.isTrue(!mainDataRep.findByActifTrue().isEmpty(), 
                    "initDatabase() ne renvoie aucune MainData, l'initialisation va échoué !");
            
        }
        
        
        private boolean testInitSaison(Saison testSaison) {
            Assert.notNull(testSaison, "SaisonInit est null");
            Assert.isTrue(testSaison.isActuelle(), 
                    "SaisonInit.isActuelle() doit être à : true");
            Assert.isTrue(testSaison.getAnneeDebut() == 0, 
                    "SaisonInit.getAnneeDebut() doit égale à : 0");
            Assert.isTrue(testSaison.getAnneeFin() == 0, 
                    "SaisonInit.getAnneeFin() doit égale à : 0");
            Assert.isTrue(testSaison.getBudget()==0.0, 
                    "SaisonInit.getBudget() doit égale à : 0.0 ( -> " + testSaison.getBudget() + ")");
            Assert.isTrue(testSaison.getBudgetString().equals("0,00"), 
                    "SaisonInit.getBudgetString() doit égale à : '0,00' ( -> " + testSaison.getBudgetString() + ")");
            Assert.isTrue(testSaison.toString().equals(""), 
                    "SaisonInit.toString() doit égale à : ''");
            return true;
        }
        
        
        private boolean testInitTypeTube(TypeTube testTypeTube, String nomTypeTube) {
            
            Assert.notNull(testTypeTube, "testTypeTube (" + nomTypeTube + ") est null");
            Assert.isTrue(testTypeTube.getNom().equals(nomTypeTube), 
                    "Le nom du TypeTube doit renvoyé : '" + nomTypeTube + "'");
            Assert.isTrue(testTypeTube.getSeuilBas()==0 ,
                    "Le seuil bas du TypeTube (" + nomTypeTube + ") doit être égale à 0.");
            Assert.isTrue(testTypeTube.isCommande()==false ,
                    "Le TypeTube (" + nomTypeTube + ") ne doit pas être commandable : isCommande() == false");
            Assert.isTrue(testTypeTube.toString().equals(nomTypeTube) ,
                    "testTypeTube.toString() doit renvoyé : '" + nomTypeTube + "'");
            
            return true;
        }
        
        
        private boolean testInitPrixTube(PrixTube testPrixTube, TypeTube testTypeTube) {
            String devise = "€";
            String nomTypeTube = testTypeTube.getNom();
            
            Assert.notNull(testPrixTube, "testPrixTubeTrain est null");
            Assert.isTrue(testPrixTube.isDefaut(), 
                    "testPrixTube (" + nomTypeTube + ") n'est pas un PrixTube par défaut");
            Assert.isTrue(testPrixTube.getMarque().isEmpty(), 
                    "testPrixTube.getMarque() (" + nomTypeTube + ") n'est pas un champ vide");
            Assert.isTrue(testPrixTube.getPrix()==0.0, 
                    "testPrixTube.getPrix() (" + nomTypeTube + ") doit être égale à : 0.0");
            Assert.isTrue(testPrixTube.getPrixMembre()==0.0, 
                    "testPrixTube.getPrixMembre() (" + nomTypeTube + ") doit être égale à : 0.0");
            Assert.isTrue(testPrixTube.getIdTypeTube() == testTypeTube, 
                    "testPrixTube.getIdTypeTube() (" + nomTypeTube + ") doit être égale à : testTypeTube"); 
            Assert.isTrue(testPrixTube.isActif(), 
                    "testPrixTube.isActif() (" + nomTypeTube + ") doit être à : True");
            Assert.isTrue(testPrixTube.getActif().equals("Oui"), 
                    "testPrixTube.getActif() (" + nomTypeTube + ") doit renvoyé : 'Oui'");
            Assert.isTrue(testPrixTube.getIdH().equals("prixtube-actif-on"), 
                    "testPrixTube.getIdH() (" + nomTypeTube + ") doit renvoyé : 'prixtube-actif-on'");
            testPrixTube.setActif(false);
            Assert.isTrue(testPrixTube.getIdH().equals("prixtube-actif-off"), 
                    "testPrixTube.getIdH() (" + nomTypeTube + ") doit renvoyé : 'prixtube-actif-off'"); 
            testPrixTube.setActif(true);
            Assert.isTrue(testPrixTube.getPrixString().equals("0,00"), 
                    "testPrixTube.getPrixString() (" + nomTypeTube + ") doit renvoyé : '0,00'");
            Assert.isTrue(testPrixTube.getPrixMembreString().equals("0,00"), 
                    "testPrixTube.getPrixMembreString() (" + nomTypeTube + ") doit renvoyé : '0,00'");
            Assert.isTrue(testPrixTube.getPrixtubeDevise(devise).equals("0,00€"), 
                    "testPrixTube.getPrixtubeDevise() (" + nomTypeTube + ") doit renvoyé : '0,00€'");
            Assert.isTrue(testPrixTube.getPrixComment().equals("0,00€ | 0,00€"), 
                    "testPrixTube.getPrixComment() (" + nomTypeTube + ") doit renvoyé : '0,00€ | 0,00€'");
            Assert.isTrue(testPrixTube.toString().equals("[" + testTypeTube.toString() + "] (" + testPrixTube.getPrixString() + ")"),
                    "testPrixTube.toString() (" + nomTypeTube + ") doit renvoyé : '[" + testTypeTube.toString() + "] (" + testPrixTube.getPrixString() + ")'");
        
            return true;
        }
        
        
        private boolean testInitData(MainData testData, Saison testSaison, TypeTube testTypeTubeTrain, TypeTube testTypeTubeCompet, TypeTube testTypeTubePlast) {
            Assert.notNull(testData,"testData est null");
            Assert.isTrue(testData.getIdSaison().equals(testSaison), 
                    "testData.getIdSaison() doit renvoyé : testSaison");
            Assert.isTrue(testData.getIdTTEntrainement().equals(testTypeTubeTrain), 
                    "testData.getIdTTEntrainement() doit renvoyé : testTypeTubeTrain");
            Assert.isTrue(testData.getIdTTCompetition().equals(testTypeTubeCompet), 
                    "testData.getIdTTCompetition() doit renvoyé : testTypeTubeCompet");
            Assert.isTrue(testData.getIdTTPlastique().equals(testTypeTubePlast), 
                    "testData.getIdTTPlastique() doit renvoyé : testTypeTubePlast");
            Assert.isTrue(testData.getBudgetDefault()==3000.0, 
                    "testData.getBudgetDefault() doit être égale à : 3000.0");
            Assert.isTrue(testData.getTypeTubeName(NomTypeTube.ENTRAINEMENT.toString()).equals(testTypeTubeTrain),
                    "testData.getTypeTubeName(NomTypeTube.ENTRAINEMENT.toString()) doit renvoyé : testTypeTubeTrain");
            Assert.isTrue(testData.getTypeTubeName(NomTypeTube.COMPETITION.toString()).equals(testTypeTubeCompet),
                    "testData.getTypeTubeName(NomTypeTube.COMPETITION.toString()) doit renvoyé : testTypeTubeCompet");
            Assert.isTrue(testData.getTypeTubeName(NomTypeTube.PLASTIQUE.toString()).equals(testTypeTubePlast),
                    "testData.getTypeTubeName(NomTypeTube.PLASTIQUE.toString()) doit renvoyé : testTypeTubePlast");
            
            return true;
        }
           
        
        private boolean testInitAppUser(AppUser testAppUser, String Username) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            
            Assert.notNull(testAppUser, "testAppUser (" + Username + ") est null");
            Assert.isTrue(testAppUser.isEnabled(), 
                    "testAppUser.isEnabled() (" + Username + ") doit être à : True");
            Assert.isTrue(testAppUser.getUserName().equals(Username), 
                    "testAppUser.getUserName() (" + Username + ") doit renvoyé : " + Username);
            Assert.isTrue(encoder.matches(Username, testAppUser.getEncrytedPassword()), 
                    "testAppUser.getEncrytedPassword() (" + Username + ") doit renvoyé : " + Username + " (BCrypté)");
            Assert.isTrue(testAppUser.getActif().equals("Oui"), 
                    "testAppUser.getActif() (" + Username + ") doit renvoyé : 'Oui'");
            Assert.isTrue(testAppUser.getIdH().equals("user-actif-on"), 
                    "testAppUser.getIdH() (" + Username + ") doit renvoyé : 'user-actif-on'");
            testAppUser.setEnabled(false);
            Assert.isTrue(testAppUser.getIdH().equals("user-actif-off"), 
                    "testAppUser.getIdH() (" + Username + ") doit renvoyé : 'user-actif-off'");
            testAppUser.setEnabled(true);
                        
            return true;
        }
        
        
        private boolean testInitAppRole(AppRole testAppRole, String rolename) {
            Assert.notNull(testAppRole, "testAppRole (" + rolename + ") est null");
            Assert.isTrue(testAppRole.getRoleName().equals(rolename),
                    "testAppRole.getRoleName() (" + rolename + ") doit renvoyé : '" + rolename + "'");
            
            return true;
        }
        
        
        private boolean testInitUserRole(UserRole testUserRole, AppUser testAppUser, AppRole testAppRole) {
            Assert.notNull(testUserRole, "testUserRole (" + testAppUser.getUserName() + ") est null");
            Assert.isTrue(testUserRole.getRoleId().equals(testAppRole.getRoleId()), 
                    "testUserRole.getRoleId() (" + testAppUser.getUserName() + ") doit être égale à : testAppRole.getRoleId()");
            Assert.isTrue(testUserRole.getUserId().equals(testAppUser.getUserId()), 
                    "testUserRole.getUserId() (" + testAppUser.getUserName() + ") doit être égale à : testAppUser.getUserId()");
            
            return true;
        }
        
        
        
        

}

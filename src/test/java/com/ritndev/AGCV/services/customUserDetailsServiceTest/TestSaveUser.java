package com.ritndev.AGCV.services.customUserDetailsServiceTest;

import com.ritndev.agcv.InterfaceService.IUserService;
import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormUser;
import com.ritndev.agcv.model.AppUser;
import com.ritndev.agcv.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 *
 * @author Ritn
 */
@SpringBootTest
public class TestSaveUser {
    
    @Autowired private IUserService userService;
    @Autowired private AppUserRepository userRep;
    
    @Test
    public void returnReponse() {
        
        Reponse testReponse = new Reponse("user", "create", 2);
        String newUser = "test";
        
        FormUser testfUser = new FormUser();
        testfUser.setIdentifiant(newUser);
        testfUser.setMdp(newUser);
        testfUser.setActif(true);
        Assert.isTrue(testfUser.isActif(),
                "testfUser doit être actif");
                
        Reponse resultReponse = userService.saveUser(testfUser);
        Assert.isTrue(testReponse.getTypeReponse().equals(resultReponse.getTypeReponse()), 
                "L'utilisateur n'a pas été créé en BDD");
        
        testReponse = new Reponse("user", "create", 0);
        resultReponse = userService.saveUser(null);
        Assert.isTrue(testReponse.getTypeReponse().equals(resultReponse.getTypeReponse()), 
                "(test null) L'utilisateur n'est pas censé être créé en BDD");
        
        testfUser = new FormUser();
        testfUser.setIdentifiant("");
        testfUser.setMdp("");
        resultReponse = userService.saveUser(testfUser);
        Assert.isTrue(testReponse.getTypeReponse().equals(resultReponse.getTypeReponse()), 
                "(test empty) L'utilisateur n'est pas censé être créé en BDD");
        
        testfUser = new FormUser();
        testfUser.setIdentifiant(newUser);
        testfUser.setMdp("");
        resultReponse = userService.saveUser(testfUser);
        Assert.isTrue(testReponse.getTypeReponse().equals(resultReponse.getTypeReponse()), 
                "(test empty) L'utilisateur n'est pas censé être créé en BDD");
        
        testfUser = new FormUser();
        testfUser.setIdentifiant("user");
        testfUser.setMdp("user");
        resultReponse = userService.saveUser(testfUser);
        Assert.isTrue(testReponse.getTypeReponse().equals(resultReponse.getTypeReponse()), 
                "(test empty) L'utilisateur n'est pas censé être créé en BDD");
        
        //RAZ BDD
        AppUser testUser = userRep.findByUserName(newUser);
        userRep.delete(testUser);
        
    }
    
}

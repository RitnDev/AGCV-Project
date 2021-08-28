package com.ritndev.AGCV.services.customUserDetailsServiceTest;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;


/**
 *
 * @author Ritn
 */
@SpringBootTest
public class TestLoadUserByUsername {
    
    @Autowired private UserDetailsService customUserService;
    
    
    @Test
    public void throwsUsernameNotFoundException() {
        String userName = "userTestException";
        
        try {
            customUserService.loadUserByUsername(userName);
            fail("Attendre qu'une exception UsernameNotFoundException soit levée");
        } catch (UsernameNotFoundException exception) {
            Assert.isTrue(exception.getMessage().equals("User " + userName + " ne se trouve pas en base de données"),
                    "Un message d'exception doit être levé ici");
        }
    }
       
    
    
    @Test
    public void returnUserDetails() {
        String username = "user";
        
        UserDetails customUser = customUserService.loadUserByUsername(username);
        Assert.notNull(customUser, "customUser ne doit pas être null");
        Assert.isTrue(customUser.getUsername().equals(username),
                "Le customUser.getUsername() doit renvoyé : 'user'");
        
    }
    
}

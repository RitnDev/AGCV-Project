package com.ritndev.AGCV.services.customUserDetailsServiceTest;

import com.ritndev.agcv.InterfaceService.IUserService;
import com.ritndev.agcv.model.AppUser;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 *
 * @author Ritn
 */
@SpringBootTest
public class TestListUser {
    
    @Autowired private IUserService userService;
    
    @Test
    public void returnListUser() {
                
        List<AppUser> testListUser = userService.listUser();
        Assert.notNull(testListUser, "testListUser ne doit pas être null");
        Assert.isTrue(!testListUser.isEmpty(), "testListUser ne doit pas être vide");
        
        AppUser testUser = null;
        for(AppUser u : testListUser) {
            if(u.getUserName().equals("user")){
                testUser = u;
            }
        }
        Assert.notNull(testUser, "testUser ne doit pas être null");
        Assert.isTrue(testUser.getUserName().equals("user"), 
                "testUser.getUserName() doit renvoyé : 'user'");
        
    }
    
}

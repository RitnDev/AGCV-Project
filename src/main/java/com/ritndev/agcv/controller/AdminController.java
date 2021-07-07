package com.ritndev.agcv.controller;

import com.ritndev.agcv.InterfaceService.IUserService;
import com.ritndev.agcv.pages.PageAdmin;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Ritn
 */
@Controller
public class AdminController {
    
    @Autowired private IUserService userService;
    @Autowired private MessageSource messageSource;
    
    
    //--------------------   Page Admin   ---------------------------- 
    
    @GetMapping(value = { "/admin", "/newPrixTube"})
    public String admin(Model model, Principal principal){     
        
        PageAdmin pageAdmin = new PageAdmin(model, principal, messageSource);   
        boolean connect = userService.findRoleByUsername(pageAdmin.returnUser(principal)).equals("ROLE_SUPADMIN");
        
        return pageAdmin.getPage(connect);
    } 
    

    
}

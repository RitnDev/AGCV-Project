package com.ritndev.agcv.controller;


import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.pages.Page403;
import com.ritndev.agcv.utils.WebUtils;
import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *
 * @author Ritn
 */
@Controller
public class LoginController {
   
    
//--------------------  Page de login  ---------------------------
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        
        Link login = new Link("Page de connexion", "login");
        String page = login.toString();
        String returnPage = login.getHref();
        
        model.addAttribute("pageName", page);
        model.addAttribute("index", new Link("Page principale","index"));
        
        return returnPage;
    }
    
    
//---------------   Page non accessible (403)  ---------------------------- 
    
    @RequestMapping(value = {"/403", "/{id}"}, method = RequestMethod.GET)
    public String page403(Model model, Principal principal){
        Page403 page403 = new Page403();
        return page403.getPage(model, principal);
    }    
    
}

package com.ritndev.agcv.controller;


import com.ritndev.agcv.classes.Link;
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
    
    private final String title = "AGCV - ";
    
    //Links :
    private final Link index = new Link("Page principale","index");
    private final Link admin = new Link("Admin", "admin");
    private final Link logout = new Link("Page principale", "logout");
    private final Link login = new Link("Page de connexion", "login");
    
        
//--------------------  Page de login  ---------------------------
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        String page = login.toString();
        String returnPage = login.getHref();
        
        model.addAttribute("pageName", page);
        model.addAttribute("index", index);
        
        return returnPage;
    }
    
    
//---------------   Page non accessible (403)  ---------------------------- 
    
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String page403(Model model, Principal principal){
        String page = "Page non accessible";
        String returnPage = "403";
        
        Link[] links = new Link[] {
            logout
        };
        
        Link[] menus = new Link[] {};
        
        String message = "Vous n'avez pas acces à cette page.";
        
        // Add Attribute :
        model.addAttribute("pageTitle", title + page);
        model.addAttribute("pageName", page);
        model.addAttribute("buttonAdmin", false);
        model.addAttribute("adminPage", admin);
        model.addAttribute("menus", menus);    
        model.addAttribute("links", links);
        model.addAttribute("message", message);
                
        
        Boolean connect = false;
        String user = "";
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            user = WebUtils.toString(loginedUser);
            if(user.equals("")){}else{connect = true;}
        }
        model.addAttribute("log", "Connecté : " + user);
        model.addAttribute("connect", connect);
        
        
        return returnPage;
    }    
    
}

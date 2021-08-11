package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormUser;
import java.security.Principal;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageUsers extends Page {

    //Construction de la page SUPER-ADMIN :
    public PageUsers(Model model, Principal principal, MessageSource messageSource) {
        super("users", model, principal, messageSource);
        
        super.setAdminPage(true);
        super.replaceLinkAdminPage("users", "logout");
        super.addLinks(returnLink("users", "admin"));
        super.addLinks(returnLink("users", "superAdmin"));
        super.addLinks(returnLink("index"));
       
        Link newUser = new Link("Nouvel utilisateur", "#", "topnav-menu-user");
        
        Link[] menu = new Link[] {
            newUser
        };
        super.setMenu(menu);
        
    }

      
    
    public String getPage() {
        // Add Attribute :
        getPageGenerique();
       
        super.getModel().addAttribute("newUser", new FormUser());
        
        return returnPage();
    }
    
    
    //Formalise le nom du role
    public String formatRole(String role) {
        String result;
        switch(role){
            case "ROLE_ADMIN" -> result = "Admin";
            case "ROLE_SUPADMIN" -> result = "Super Admin";
            default -> result = "User";
        }
        return result;
    }
}

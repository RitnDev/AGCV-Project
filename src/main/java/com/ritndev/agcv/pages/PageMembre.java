package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormMembre;
import java.security.Principal;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageMembre extends Page {
    
    //Construction de la page ADMIN :
    public PageMembre(Model model, Principal principal, MessageSource messageSource) {
        super("membre", model, principal, messageSource); 
        
        super.setAdminPage(true);
        super.setSuperAdminPage(true);
        super.replaceLinkAdminPage("membre", "logout");
        super.addLinks(returnLink("index"));
        super.addLinks(returnLink("membre", "admin"));
        
        Link newMembre = new Link("Nouveau membre", "#", "topnav-menu-membre"); 
        
        Link[] menu = new Link[] {
            newMembre
        };
        
        super.setMenu(menu);          
    }
    
    
    
    //Renvoie la page
    public String getPage(boolean connect) {
        // Add Attribute :
        getPageGenerique();
        super.getModel().addAttribute("newMembre", new FormMembre());

        Link pageSupAdmin = new Link("superAdmin", "Super Admin", "/superAdmin", connect);
        super.addLinks(pageSupAdmin);
        
        return returnPage();
    }
    
    
}

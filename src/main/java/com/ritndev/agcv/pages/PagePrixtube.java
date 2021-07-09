package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormPrixTube;
import java.security.Principal;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PagePrixtube extends Page {
    
    
    //Construction de la page ADMIN :
    public PagePrixtube(Model model, Principal principal, MessageSource messageSource) {
        super("prixtube", model, principal, messageSource); 
        
        super.setAdminPage(true);
        super.setSuperAdminPage(true);
        super.replaceLinkAdminPage("prixtube", "logout");
        super.addLinks(returnLink("index"));
        super.addLinks(returnLink("prixtube", "admin"));
        
        Link newPrixTube = new Link("Nouveau prix de tube", "#", "topnav-menu-prixtube"); 
        
        Link[] menu = new Link[] {
            newPrixTube
        };
        super.setMenu(menu);    
    }
    
    
    
    //Renvoie la page
    public String getPage(boolean connect) {
       
        // Add Attribute :
        getPageGenerique();
        super.getModel().addAttribute("newPrixTube", new FormPrixTube());

        Link pageSupAdmin = new Link("superAdmin", "Super Admin", "/superAdmin", connect);
        super.addLinks(pageSupAdmin);
        
        return returnPage();
    }
    
}

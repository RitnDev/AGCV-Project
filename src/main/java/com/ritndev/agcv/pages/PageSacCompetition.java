package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormCompet;

import java.security.Principal;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageSacCompetition extends Page {
       
    
    public PageSacCompetition(Model model, Principal principal, MessageSource messageSource) {
        super("sacCompetition", model, principal, messageSource);
       
        super.setAdminPage(true);
        super.addLinks(returnLink("index"));

        
        Link competition = new Link("Nouvelle compétition", "#", "topnav-menu-compet"); 
        Link stock = new Link("Ajuster le stock", "#", "topnav-menu-stock");
        
        Link[] menu = new Link[] {
            competition,
            stock
        };
        super.setMenu(menu);      
    }
    
    
    public String getPage() {       
        // Add Attribute :
        getPageGenerique();
        super.getModel().addAttribute("newCompetition", new FormCompet());
        
        return returnPage();
    }
    
}

package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormCommande;
import java.security.Principal;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageCommandesMembres extends Page{
            
    public PageCommandesMembres(Model model, Principal principal, MessageSource messageSource) {
        super("commandesMembres", model, principal, messageSource);   
        
        super.setAdminPage(true);
        super.addLinks(returnLink("index"));
        
        
        Link newCommande = new Link("Nouvelle commande", "#", "topnav-menu-commande"); 
        Link[] menu = new Link[] {
            newCommande
        };
        super.setMenu(menu);
        
    }
    
    
    public String getPage() {
        // Add Attribute :
        getPageGenerique();
        super.getModel().addAttribute("newCommande", new FormCommande());
        
        return returnPage();
    }
    
}

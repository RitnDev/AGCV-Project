package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import java.security.Principal;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageSacCompetition extends Page {
    
    public PageSacCompetition() {
       
        super.setPage("Gestion du sac de compétition");
        super.setLinkPage(new Link("Gestion du sac de compétition", "sacCompetition", "topnav-menu"));
        super.setAdminPage(true);
        super.setLinkAdminPage(new Link("Admin", "admin"));
        
        
        Link index = new Link("Page principale","index");
        Link[] links = new Link[] {
            index,
        };
        super.setLinks(links);
                
    }
    
    
    public String getPage(Model model, Principal principal) {
                
        String message = "Ici se trouve la page : ";
        message = message + getPage();
        
        // Add Attribute :
        model = getPageGenerique(model, principal);
        model.addAttribute("message", message);
        
        return returnPage();
        
    }
    
}
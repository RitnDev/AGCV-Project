package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import java.security.Principal;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageHistoSaison extends Page{
    
    public PageHistoSaison() {
       
        super.setPage("Historiques des saisons précédentes");
        super.setLinkPage(new Link("Historiques des saisons précédentes", "histoSaison", "topnav-menu"));
        super.setAdminPage(true);
        super.setLinkAdminPage(new Link("Admin", "/admin"));
        
        
        Link index = new Link("Page principale","/index");
        super.addLinks(index);
                
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

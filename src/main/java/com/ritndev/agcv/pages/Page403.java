package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;

import java.security.Principal;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class Page403 extends Page {
    
    
    public Page403() {
               
        super.setPage("Page non accessible");
        super.setLinkPage(new Link("Page non accessible", "403"));
        super.setLinkAdminPage(new Link("Admin", "/admin"));
                
        Link logout = new Link("Page principale", "/logout");
        super.addLinks(logout);
                
    }
    
    
    public String getPage(Model model, Principal principal) {
                
        String message = "Vous n'avez pas acces à cette page.";
        
        // Add Attribute :
        model = getPageGenerique(model, principal);
        model.addAttribute("message", message);
        
        return returnPage();
        
    }
    
}

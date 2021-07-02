package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import java.security.Principal;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageActions extends Page{
    
    public PageActions(Model model, Principal principal) {
        super(model, principal);     
        
        super.setNomPage("");
        super.setLinkPage(new Link("","actions"));
        
        Link logout = new Link("Page principale", "/logout");
        super.addLinks(logout);
        
        super.setLinkAdminPage(new Link("Admin", "/admin"));
          
    }
    
    
    public String getPage() {
     
        // Add Attribute :
        getPageGenerique();
        super.setTitlePage("AGCV");
        
        return returnPage();
    }
    
}

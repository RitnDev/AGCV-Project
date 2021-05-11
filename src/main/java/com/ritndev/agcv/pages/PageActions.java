package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import java.security.Principal;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageActions extends Page{
    
    public PageActions() {
                
        super.setPage("");
        super.setLinkPage(new Link("","actions"));
        
        Link logout = new Link("Page principale", "logout");
        super.addLinks(logout);
        
        super.setLinkAdminPage(new Link("Admin", "admin"));
          
    }
    
    
    public String getPage(Model model, Principal principal) {
     
        // Add Attribute :
        model = getPageGenerique(model, principal);
        super.setTitlePage(model, "AGCV");
        
        return returnPage();
    }
    
}

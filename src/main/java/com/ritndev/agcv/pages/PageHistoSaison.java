package com.ritndev.agcv.pages;

import java.security.Principal;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageHistoSaison extends Page{
        
    public PageHistoSaison(Model model, Principal principal, MessageSource messageSource) {
        super("histoSaison", model, principal, messageSource);
        
        super.setAdminPage(true);
        super.addLinks(returnLink("index"));        
    }
    
    
    public String getPage() {        
        // Add Attribute :
        getPageGenerique();
        return returnPage();
        
    }
    
}

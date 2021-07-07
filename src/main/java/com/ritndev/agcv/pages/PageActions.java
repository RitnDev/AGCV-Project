package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import java.security.Principal;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageActions extends Page{
    
    public PageActions(Model model, Principal principal, MessageSource messageSource) {
        super("actions", model, principal, messageSource);     
       
        Link logout = super.returnLink("logout");
        super.addLinks(logout);          
    }
    
    
    public String getPage() {
        // Add Attribute :
        getPageGenerique();
        return returnPage();
    }
    
}

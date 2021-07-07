package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import java.security.Principal;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class Page403 extends Page {
   
    public Page403(Model model, Principal principal, MessageSource messageSource) {
        super("403", model, principal, messageSource);
        
        Link logout = super.returnLink("logout");
        super.addLinks(logout);
    }
    
    
    public String getPage() {
        // Add Attribute :
        getPageGenerique();
        super.getModel().addAttribute("message", getTexteRessource("message"));
        
        return returnPage();
    }
    
}

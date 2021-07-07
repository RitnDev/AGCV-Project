package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
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

        //super.setLinkPage(new Link("Historiques des saisons précédentes", "histoSaison", "topnav-menu"));
        super.setAdminPage(true);
        
        
        Link index = new Link("Page principale","/index");
        super.addLinks(index);
                
    }
    
    
    public String getPage() {
                
        String message = "Ici se trouve la page : ";
        message = message + getNomPage();
        
        // Add Attribute :
        getPageGenerique();
        super.getModel().addAttribute("message", message);
        
        return returnPage();
        
    }
    
}

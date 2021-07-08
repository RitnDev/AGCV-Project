package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormSaison;
import java.security.Principal;
import java.util.Calendar;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageSaison extends Page {
    
    //Construction de la page ADMIN :
    public PageSaison(Model model, Principal principal, MessageSource messageSource) {
        super("saison", model, principal, messageSource);
        
        super.setAdminPage(true);
        super.setSuperAdminPage(true);
        super.replaceLinkAdminPage("saison", "logout");
        super.addLinks(returnLink("index"));
        super.addLinks(returnLink("saison", "admin"));
                
        Link newSaison = new Link("Nouvelle saison", "#", "topnav-menu-saison");
        
        Link[] menu = new Link[] {
            newSaison
        };
        super.setMenu(menu);          
    }
    
    
    //Renvoie la page
    public String getPage(String budget, boolean connect) {
        
        // Add Attribute :
        getPageGenerique();
        //Charge l'année en cours + Budget par défaut "1000" + saison active par defaut.
        super.getModel().addAttribute("newSaison", new FormSaison(Calendar.getInstance().get(Calendar.YEAR),budget, true));

        Link pageSupAdmin = new Link("supAdmin", "Super Admin", "/superAdmin", connect);
        super.addLinks(pageSupAdmin);
        
        return returnPage();
    }
    
    
}

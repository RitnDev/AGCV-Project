package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import java.security.Principal;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageIndex extends Page{

    public PageIndex() {
        
        super.setPage("Consommation de tubes sur la saison");
        super.setLinkPage(new Link("Page principale","index"));
        super.setAdminPage(true);
        super.setLinkAdminPage(new Link("Admin", "admin"));
        
        
        Link commandesMembres = new Link("Commandes tubes des membres","commandesMembres");
        super.addLinks(commandesMembres);
       
        
        Link histoSaison = new Link("Historiques des saisons précédentes", "histoSaison", "topnav-menu");
        Link sacCompetition = new Link("Gestion du sac de compétition", "sacCompetition", "topnav-menu"); 
        
        Link[] menu = new Link[] {
            histoSaison,
            sacCompetition
        };
        super.setMenu(menu);
        
    }
    
    
    public String getPage(Model model, Principal principal) {
     
        String message = "Ici se trouve la page d'accueil, elle s'appelle également : ";
        message = message + getPage();
        
        // Add Attribute :
        model = getPageGenerique(model, principal);
        model.addAttribute("message", message);
        
        return returnPage();
        
    }
    
    
    
}

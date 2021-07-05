package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormCompet;
import com.ritndev.agcv.model.Saison;
import com.ritndev.agcv.model.StockCompetition;

import java.security.Principal;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageSacCompetition extends Page {
       
    
    public PageSacCompetition(Model model, Principal principal) {
        super(model, principal);
       
        super.setNomPage("Gestion du stock de compétition");
        super.setLinkPage(new Link("Gestion du stock de compétition", "sacCompetition", "topnav-menu"));
        super.setAdminPage(true);
        super.setLinkAdminPage(new Link("Admin", "/admin"));
        
        
        Link competition = new Link("Nouvelle compétition", "#", "topnav-menu-compet"); 
        Link stock = new Link("Ajuster le stock", "#", "topnav-menu-stock");
        
        Link[] menu = new Link[] {
            competition,
            stock
        };
        super.setMenu(menu);
        
        
        Link index = new Link("Page principale","/index");
        super.addLinks(index);
        
    }
    
    
    public String getPage() {
                
        String message = "Ici se trouve la page : ";
        message = message + getNomPage() + " ";
        message = message + "Ici sera affiché la liste des compétition de la saison. ";
        message = message + "Il sera possible de faire le restockage du stock de compétition.";
        
        
        int AdminConnect = 0;
        
        // Add Attribute :
        getPageGenerique();
        super.getModel().addAttribute("message", message);
        super.getModel().addAttribute("newCompetition", new FormCompet());
        
        
        return returnPage();
        
    }
    
}

package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormCommande;
import java.security.Principal;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageCommandesMembres extends Page{
            
    public PageCommandesMembres(Model model, Principal principal) {
        super(model, principal); 
       
        super.setNomPage("Commandes tubes des membres");
        super.setLinkPage(new Link("Commandes tubes des membres","commandesMembres"));
        super.setAdminPage(true);
        super.setLinkAdminPage(new Link("Admin", "/admin"));
        
        
        Link index = new Link("Page principale","/index");
        super.addLinks(index);
        
        
        Link newCommande = new Link("Nouvelle commande", "#", "topnav-menu-commande"); 
        Link[] menu = new Link[] {
            newCommande
        };
        super.setMenu(menu);
        
    }
    
    
    public String getPage() {
     
        String message = "Ici se trouve la page : ";
        message = message + getNomPage();
        
        // Add Attribute :
        getPageGenerique();
        super.getModel().addAttribute("message", message);
        super.getModel().addAttribute("newCommande", new FormCommande());
        super.getModel().addAttribute("Membres", getMembreService().listMembre());
        super.getModel().addAttribute("Commandes", getCommandeService().listCommande());
        super.getModel().addAttribute("prixTubeList", getPrixTubeService().listPrixTube());
        super.getModel().addAttribute("saisonList", getSaisonService().listSaison());
        
        return returnPage();
        
    }
    
}

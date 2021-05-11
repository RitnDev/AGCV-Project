package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormCommande;
import com.ritndev.agcv.model.Membre;
import com.ritndev.agcv.services.IagcvService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageCommandesMembres extends Page{
    
    public PageCommandesMembres() {
       
        super.setPage("Commandes tubes des membres");
        super.setLinkPage(new Link("Commandes tubes des membres","commandesMembres"));
        super.setAdminPage(true);
        super.setLinkAdminPage(new Link("Admin", "admin"));
        
        
        Link index = new Link("Page principale","index");
        super.addLinks(index);
        
        
        Link newCommande = new Link("Nouvelle commande", "#", "topnav-menu-commande"); 
        Link[] menu = new Link[] {
            newCommande
        };
        super.setMenu(menu);
        
    }
    
    
    public String getPage(Model model, Principal principal, IagcvService service) {
     
        // recupérer la listes des membre en BDD.
        List<String> listMembres = new ArrayList<>();
        for(Membre m : service.listMembre()) {
            listMembres.add(m.toString());
        }
                
        FormCommande nouvelleCommande = new FormCommande("", 1, false);
                
        String message = "Ici se trouve la page : ";
        message = message + getPage();
        
        // Add Attribute :
        model = getPageGenerique(model, principal);
        model.addAttribute("message", message);
        model.addAttribute("newCommande", nouvelleCommande);
        model.addAttribute("Membres", listMembres);
        
        return returnPage();
        
    }
    
}

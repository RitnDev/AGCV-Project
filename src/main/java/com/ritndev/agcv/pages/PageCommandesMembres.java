package com.ritndev.agcv.pages;

import com.ritndev.agcv.InterfaceService.IMainDataService;
import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormCommande;
import com.ritndev.agcv.model.Saison;
import com.ritndev.agcv.utils.MoisUtils;
import java.security.Principal;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageCommandesMembres extends Page{
            
    public PageCommandesMembres(Model model, Principal principal, MessageSource messageSource) {
        super("commandesMembres", model, principal, messageSource);   
        
        super.setAdminPage(true);
        super.addLinks(returnLink("index"));
        
        
        Link newCommande = new Link("Nouvelle commande", "#", "topnav-menu-commande"); 
        Link[] menu = new Link[] {
            newCommande
        };
        super.setMenu(menu);
        
    }
    
    
    public String getPage(IMainDataService dataService) {
        
        FormCommande newCommande = null;
        
        Saison saisonActuelle = dataService.returnMainData().getIdSaison();
        String strMois = MoisUtils.moisCourant();

        if(saisonActuelle.getConsoMois("Compétition", strMois) != null) {
            newCommande = new FormCommande(
                                strMois,
                                saisonActuelle.getConsoMois("Compétition", strMois).getIdPrixTube().getId(),
                                saisonActuelle.getId(), 
                                saisonActuelle.getConsoMois("Compétition", strMois).getId());
        }
        
        // Add Attribute :
        getPageGenerique();
        super.getModel().addAttribute("newCommande", newCommande);
        super.getModel().addAttribute("saison", saisonActuelle);
        
        return returnPage();
    }
    
}

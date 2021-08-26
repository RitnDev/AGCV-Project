package com.ritndev.agcv.pages;

import com.ritndev.agcv.InterfaceService.IMainDataService;
import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormCompet;
import com.ritndev.agcv.form.FormRestock;
import com.ritndev.agcv.model.ConsoMois;
import com.ritndev.agcv.model.Saison;
import com.ritndev.agcv.model.enumeration.NomTypeTube;
import com.ritndev.agcv.utils.MoisUtils;

import java.security.Principal;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageCompetition extends Page {
       
    
    public PageCompetition(Model model, Principal principal, MessageSource messageSource) {
        super("competition", model, principal, messageSource);
       
        super.setAdminPage(true);
        super.addLinks(returnLink("index"));

        
        Link competition = new Link("Nouvelle compétition", "#", "topnav-menu-compet"); 
        Link stock = new Link("Ajuster le stock", "#", "topnav-menu-stock");
        
        Link[] menu = new Link[] {
            competition,
            stock
        };
        super.setMenu(menu);      
    }
    
    
    public String getPage(IMainDataService dataService) {       
        
        FormRestock newStock = new FormRestock();
       
        String strMois = MoisUtils.moisCourant();
        Saison saisonActuelle = dataService.returnMainData().getIdSaison();
        ConsoMois consoMoisActu = saisonActuelle.getConsoMois(NomTypeTube.COMPETITION.toString(), strMois);
        if(consoMoisActu != null) {
            newStock = new FormRestock("0",
                                        saisonActuelle.getId(),
                                        dataService.returnMainData().getId(), 
                                        consoMoisActu.getId());
        }
              
        
        // Add Attribute :
        getPageGenerique();
        super.getModel().addAttribute("newCompetition", new FormCompet("1"));
        super.getModel().addAttribute("newStock", newStock);
        super.getModel().addAttribute("saison", saisonActuelle);
        super.getModel().addAttribute("stock", dataService.returnMainData().showStock());
                
        return returnPage();
    }
    
}

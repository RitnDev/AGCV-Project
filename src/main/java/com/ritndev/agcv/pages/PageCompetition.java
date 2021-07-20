package com.ritndev.agcv.pages;

import com.ritndev.agcv.InterfaceService.IMainDataService;
import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormCompet;
import com.ritndev.agcv.model.Saison;
import com.ritndev.agcv.model.StockCompetition;

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
        Link stock = new Link("Ajuster le stock", "/stockCompet", "topnav-menu-stock");
        
        Link[] menu = new Link[] {
            competition,
            stock
        };
        super.setMenu(menu);      
    }
    
    
    public String getPage(IMainDataService dataService) {       
        
        Saison saisonActuelle = dataService.returnMainData().getIdSaison();
        StockCompetition stockActuel = dataService.returnMainData().getIdStockCompet();

        // Add Attribute :
        getPageGenerique();
        super.getModel().addAttribute("newCompetition", new FormCompet());
        super.getModel().addAttribute("saison", saisonActuelle);
        super.getModel().addAttribute("stock", stockActuel);
                
        return returnPage();
    }
    
}

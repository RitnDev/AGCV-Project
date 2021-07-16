package com.ritndev.agcv.pages;

import com.ritndev.agcv.InterfaceService.IMainDataService;
import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormData;
import com.ritndev.agcv.form.FormTypeVolant;
import com.ritndev.agcv.model.Saison;
import com.ritndev.agcv.model.enumeration.NomTypeTube;

import java.security.Principal;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageAdmin extends Page {
    
    //Construction de la page ADMIN :
    public PageAdmin(Model model, Principal principal, MessageSource messageSource) {
        super("admin", model, principal, messageSource);
        
        super.setAdminPage(true);
        super.setSuperAdminPage(true);
        super.replaceLinkAdminPage("admin", "logout");
        super.addLinks(returnLink("index"));
        
        
        Link newSaison = new Link("Saisons", "/admin/saison", "topnav-menu-saison");
        Link newMembre = new Link("Membres", "/admin/membre", "topnav-menu-membre"); 
        Link newPrixTube = new Link("Prix des tubes", "/admin/prixtube", "topnav-menu-prixtube"); 
        
        Link[] menu = new Link[] {
            newSaison,
            newMembre,
            newPrixTube
        };
        super.setMenu(menu);
               
    }

   
    
    //Renvoie la page
    public String getPage(boolean connect, IMainDataService dataService) {
        
        Saison saisonActuelle = dataService.returnMainData().getIdSaison();
        
        FormTypeVolant ftvPlastique = saisonActuelle.getFormVolantName(NomTypeTube.PLASTIQUE.toString());
        FormTypeVolant ftvCompetition = saisonActuelle.getFormVolantName(NomTypeTube.COMPETITION.toString());
        FormTypeVolant ftvEntrainement = saisonActuelle.getFormVolantName(NomTypeTube.ENTRAINEMENT.toString());
        
        double budgetDefault = dataService.returnMainData().getBudgetDefault();
        int seuilBas = dataService.returnMainData().getSeuilBas();
        
        
        // Add Attribute :
        getPageGenerique();
        
        super.getModel().addAttribute("saison", saisonActuelle);
        super.getModel().addAttribute("editData", new FormData(budgetDefault, seuilBas));
        super.getModel().addAttribute("editTVPlastique", ftvPlastique);
        super.getModel().addAttribute("editTVCompetition", ftvCompetition);
        super.getModel().addAttribute("editTVEntrainement", ftvEntrainement);
        
        Link pageSupAdmin = new Link("superAdmin", "Super Admin", "/superAdmin", connect);
        super.addLinks(pageSupAdmin);
        
        return returnPage();
    }
}

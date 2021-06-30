package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.model.enumeration.NomMois;
import com.ritndev.agcv.model.enumeration.NomTypeTube;
import com.ritndev.agcv.services.IagcvService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
        super.setLinkAdminPage(new Link("Admin", "/admin"));
        
        
        Link commandesMembres = new Link("Commandes tubes des membres","/commandesMembres");
        Link histoSaison = new Link("Historiques des saisons précédentes", "/histoSaison");//, "topnav-menu");
        Link sacCompetition = new Link("Gestion du stock de compétition", "/sacCompetition");//, "topnav-menu"); 
        
        super.addLinks(commandesMembres);
        super.addLinks(sacCompetition);
        super.addLinks(histoSaison);
        
        
    }
    
    
    public String getPage(Model model, Principal principal, IagcvService service) {
     
        String message = "Ici se trouve la page d'accueil, elle s'appelle également : ";
        message = message + getPage();
        
        String tvEntrainement = NomTypeTube.ENTRAINEMENT.toString();
        String tvPlastique = NomTypeTube.PLASTIQUE.toString();
        String tvCompetition = NomTypeTube.COMPETITION.toString();
        
        
        
        // Add Attribute :
        model = getPageGenerique(model, principal);
        model.addAttribute("message", message);
        
        //Saison actuellement en cours
        model.addAttribute("saison", service.returnMainData().getIdSaison());
        model.addAttribute("plastique", tvPlastique);
        model.addAttribute("entrainement", tvEntrainement);
        model.addAttribute("competition", tvCompetition);
        model.addAttribute("nomMois", new ArrayList<>(Arrays.asList(NomMois.values())));
        
        return returnPage();
        
    }
    
    
    
}

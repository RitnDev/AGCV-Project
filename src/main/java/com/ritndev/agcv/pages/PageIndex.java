package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.model.enumeration.NomMois;
import com.ritndev.agcv.model.enumeration.NomTypeTube;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.ui.Model;
import java.security.Principal;



/**
 *
 * @author Ritn
 */
public class PageIndex extends Page {
  
    
    //Constructeur
    public PageIndex(Model model, Principal principal) {
        super(model, principal);
        
        super.setNomPage("Consommation de tubes sur la saison");
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
    
    //Renvoie la page
    public String getPage() {
     
        String message = "Ici se trouve la page d'accueil, elle s'appelle également : ";
        message = message + getNomPage();
        
        String tvEntrainement = NomTypeTube.ENTRAINEMENT.toString();
        String tvPlastique = NomTypeTube.PLASTIQUE.toString();
        String tvCompetition = NomTypeTube.COMPETITION.toString();
        
        
        
        // Add Attribute :
        getPageGenerique();
        super.getModel().addAttribute("message", message);
                
        //Saison actuellement en cours
        super.getModel().addAttribute("plastique", tvPlastique);
        super.getModel().addAttribute("entrainement", tvEntrainement);
        super.getModel().addAttribute("competition", tvCompetition);
        super.getModel().addAttribute("nomMois", new ArrayList<>(Arrays.asList(NomMois.values())));
        
        return returnPage();
        
    }
    
    
    
}

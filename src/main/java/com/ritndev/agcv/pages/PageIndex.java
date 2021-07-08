package com.ritndev.agcv.pages;

import com.ritndev.agcv.model.enumeration.NomMois;
import com.ritndev.agcv.model.enumeration.NomTypeTube;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.ui.Model;
import java.security.Principal;
import org.springframework.context.MessageSource;



/**
 *
 * @author Ritn
 */
public class PageIndex extends Page {
  
    
    //Constructeur
    public PageIndex(Model model, Principal principal, MessageSource messageSource) {
        super("index", model, principal, messageSource);
        
        super.setAdminPage(true);
        super.addLinks(returnLink("commandesMembres"));
        super.addLinks(returnLink("sacCompetition"));
        super.addLinks(returnLink("histoSaison"));
        
    }
    
    //Renvoie la page
    public String getPage() {
        
        String tvEntrainement = NomTypeTube.ENTRAINEMENT.toString();
        String tvPlastique = NomTypeTube.PLASTIQUE.toString();
        String tvCompetition = NomTypeTube.COMPETITION.toString();
        
        // Add Attribute :
        getPageGenerique();
                
        //Saison actuellement en cours
        super.getModel().addAttribute("plastique", tvPlastique);
        super.getModel().addAttribute("entrainement", tvEntrainement);
        super.getModel().addAttribute("competition", tvCompetition);
        super.getModel().addAttribute("nomMois", new ArrayList<>(Arrays.asList(NomMois.values())));
        
        return returnPage();
        
    }
    
    
    
}

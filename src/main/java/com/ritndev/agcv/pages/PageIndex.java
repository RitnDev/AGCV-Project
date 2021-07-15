package com.ritndev.agcv.pages;

import com.ritndev.agcv.model.enumeration.NomMois;
import com.ritndev.agcv.model.enumeration.NomTypeTube;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.ui.Model;
import java.security.Principal;
import java.util.List;
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
        
        List<String> listVolants = new ArrayList<>();
        
        listVolants.add(NomTypeTube.PLASTIQUE.toString());
        listVolants.add(NomTypeTube.COMPETITION.toString());
        listVolants.add(NomTypeTube.ENTRAINEMENT.toString());
        
        // Add Attribute :
        getPageGenerique();
                
        super.getModel().addAttribute("listVolants", listVolants);
        super.getModel().addAttribute("nomMois", new ArrayList<>(Arrays.asList(NomMois.values())));
        
        return returnPage();
        
    }
    
    
    
}

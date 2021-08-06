package com.ritndev.agcv.pages;

import com.ritndev.agcv.model.enumeration.NomMois;
import com.ritndev.agcv.model.enumeration.NomTypeTube;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageHistoSaison extends Page{
        
    public PageHistoSaison(Model model, Principal principal, MessageSource messageSource) {
        super("histo", model, principal, messageSource);
        
        super.setAdminPage(true);
        super.addLinks(returnLink("index"));        
    }
    
    
    public String getPage() {
        
        List<String> classStock = List.of(  "table-tv-stock",
                                            "table-tv-stock",
                                            "table-tv-stock");
                
               
        List<String> listVolants = new ArrayList<>();
        
        listVolants.add(NomTypeTube.PLASTIQUE.toString());
        listVolants.add(NomTypeTube.COMPETITION.toString());
        listVolants.add(NomTypeTube.ENTRAINEMENT.toString());
        
        
        // Add Attribute :
        getPageGenerique();

        super.getModel().addAttribute("classStock", classStock);
        super.getModel().addAttribute("listVolants", listVolants);
        super.getModel().addAttribute("nomMois", new ArrayList<>(Arrays.asList(NomMois.values())));
        
        return returnPage();
    }
    
}

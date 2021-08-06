package com.ritndev.agcv.controller;

import com.ritndev.agcv.InterfaceService.IMainDataService;
import com.ritndev.agcv.InterfaceService.ISaisonService;
import com.ritndev.agcv.form.FormSaison;
import com.ritndev.agcv.model.Saison;
import com.ritndev.agcv.pages.PageHistoSaison;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Ritn
 */
@Controller
public class HistoController {
    
    @Autowired private IMainDataService dataService;
    @Autowired private ISaisonService saisonService;
    
    @Autowired private MessageSource messageSource;
    
    
    //--------------   Page Historique des saisons précédentes   ------------------
    
    @GetMapping(value = "/histo")
    public String histoSaison(Model model, Principal principal){
        PageHistoSaison pageHistoSaison = new PageHistoSaison(model, principal, messageSource);
        
        Saison saisonActuelle = dataService.returnMainData().getIdSaison();
        Saison saisonPreced = saisonService.findByAnneeFinSaison(saisonActuelle.getAnneeDebut());
        
        if (saisonPreced != null){
            model.addAttribute("saison", saisonPreced);
            model.addAttribute("listSaisons", saisonService.listSaisonHisto());
            model.addAttribute("selectSaison", new FormSaison(saisonPreced.getId(), saisonPreced.getAnneeDebut()));
        }
        
        return pageHistoSaison.getPage(); 
    }
    
    
    @PostMapping(value = "/histo")
    public String postHistoSaison(@ModelAttribute FormSaison selectSaison, Model model, Principal principal){
        PageHistoSaison pageHistoSaison = new PageHistoSaison(model, principal, messageSource);

        Saison saisonHisto = saisonService.findByAnneeSaison(selectSaison.getAnnee_debut());
        
        model.addAttribute("saison", saisonHisto);
        model.addAttribute("listSaisons", saisonService.listSaisonHisto());
        model.addAttribute("selectSaison", new FormSaison(saisonHisto.getId(), saisonHisto.getAnneeDebut()));
        
        return pageHistoSaison.getPage();
    }
    
    
}

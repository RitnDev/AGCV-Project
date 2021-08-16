package com.ritndev.agcv.controller;

import com.ritndev.agcv.InterfaceService.IConsoMoisService;
import com.ritndev.agcv.InterfaceService.IMainDataService;
import com.ritndev.agcv.InterfaceService.IPrixTubeService;
import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.form.FormConsoMois;
import com.ritndev.agcv.model.ConsoMois;
import com.ritndev.agcv.pages.PageIndex;
import com.ritndev.agcv.pages.PageActions;
import java.security.Principal;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Ritn
 */

@Controller
public class ConsoMoisController {
    
    @Autowired private IConsoMoisService consoMoisService;
    @Autowired private IPrixTubeService prixTubeService;
    @Autowired private IMainDataService dataService;
    
    @Autowired private MessageSource messageSource;
        
    
//---------------------------------------------------------------------------------------------    
    
    //          -------------------  PRIX TUBE -------------------
    
    
    //Changement du prix tube sur un mois
    @PostMapping(value = "/mois/prix/{id}")
    public String getPrixMois(@PathVariable Long id, Model model, Principal principal) {
        
        //Recupération de la DATA à modifier :
        ConsoMois editConsoMois = consoMoisService.findByIdConsoMois(id);
        FormConsoMois formConsoMois = new FormConsoMois(id,
                                    editConsoMois.getNom(),
                                    editConsoMois.getIdPrixTube().getId(),
                                    editConsoMois.getIdTypeVolant().getNomTypeTube());
         
        model.addAttribute("editConsoMois", formConsoMois);
        model.addAttribute("listPrixTubes", prixTubeService.ListPrixTubeName(editConsoMois.getIdTypeVolant().getNomTypeTube()));
        model.addAttribute("numAction", ActionsTypes.EDIT_CONSOMOIS_PT.toString());
        model.addAttribute("devise", messageSource.getMessage("texte.global.devise", null, Locale.FRENCH));
        
        PageActions pageAction = new PageActions(model, principal, messageSource);
        return pageAction.returnPage();
    }
    
    
    //Modifier une Prix-Tube
    @PutMapping("/mois/prix/{id}")
    public String editConsoMoisPrix(@ModelAttribute FormConsoMois putConsoMois, Model model, Principal principal) {
        PageIndex pageIndex = new PageIndex(model, principal, messageSource);   
        pageIndex.addReponse(consoMoisService.updateConsoMoisPrixtube(putConsoMois));
        
        return pageIndex.getPage(dataService);
    } 
    
//---------------------------------------------------------------------------------------------    
    
    //          -------------------  NB TUBES UTILISES -------------------


    //Changement du nombres de tubes utilisés sur le mois
    @PostMapping(value = "/mois/nbu/{id}")
    public String changeNbTubeUtilises(@PathVariable Long id, Model model, Principal principal) {
        //Recupération de la DATA à modifier :
        ConsoMois editConsoMois = consoMoisService.findByIdConsoMois(id);
        FormConsoMois formConsoMois = new FormConsoMois(id,
                                    editConsoMois.getNom(),
                                    editConsoMois.getNbTubeUtilise());
         
        model.addAttribute("editConsoMois", formConsoMois);
        model.addAttribute("numAction", ActionsTypes.EDIT_CONSOMOIS_NBU.toString());
 
        PageActions pageAction = new PageActions(model, principal, messageSource);
        return pageAction.returnPage();
    }

    
    //Modifier nbTubeUtilises du consoMois
    @PutMapping("/mois/nbu/{id}")
    public String editConsoMoisNbUtilises(@ModelAttribute FormConsoMois putConsoMois, Model model, Principal principal) {
        PageIndex pageIndex = new PageIndex(model, principal, messageSource);   
        pageIndex.addReponse(consoMoisService.updateConsoMoisNbUtilises(putConsoMois));
        
        return pageIndex.getPage(dataService);
    } 
    

//---------------------------------------------------------------------------------------------    
    
    //          -------------------  NB TUBES UTILISES -------------------


    //Changement du nombres de tubes commandés sur le mois
    @PostMapping(value = "/mois/nbc/{id}")
    public String changeNbTubeCommandes(@PathVariable Long id, Model model, Principal principal) {
        //Recupération de la DATA à modifier :
        ConsoMois editConsoMois = consoMoisService.findByIdConsoMois(id);
        FormConsoMois formConsoMois = new FormConsoMois(id,
                                    editConsoMois.getNbTubeCommande(),
                                    editConsoMois.getNom());
         
        model.addAttribute("editConsoMois", formConsoMois);
        model.addAttribute("numAction", ActionsTypes.EDIT_CONSOMOIS_NBC.toString());
 
        PageActions pageAction = new PageActions(model, principal, messageSource);
        return pageAction.returnPage();
    }

    
    //Modifier nbTubeCommande du consoMois
    @PutMapping("/mois/nbc/{id}")
    public String editConsoMoisNbCommandes(@ModelAttribute FormConsoMois putConsoMois, Model model, Principal principal) {
        PageIndex pageIndex = new PageIndex(model, principal, messageSource);   
        pageIndex.addReponse(consoMoisService.updateConsoMoisNbCommandes(putConsoMois));
        
        return pageIndex.getPage(dataService);
    } 
    
     
    
    
}

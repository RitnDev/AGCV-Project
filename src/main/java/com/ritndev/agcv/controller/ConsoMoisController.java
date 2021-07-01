package com.ritndev.agcv.controller;

import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.form.FormConsoMois;
import com.ritndev.agcv.model.ConsoMois;
import com.ritndev.agcv.pages.PageIndex;
import com.ritndev.agcv.pages.PageActions;
import com.ritndev.agcv.services.IagcvService;
import java.security.Principal;
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
    
    @Autowired private IagcvService service;
    @Autowired private MessageSource messageSource;

/*      
    --------------------   Page Index   ----------------------
    
    @GetMapping(value = { "/", "/index"})
    public String index(Model model, Principal principal){
        PageIndex pageIndex = new PageIndex();
        return pageIndex.getPage(model, principal, service);
    }
*/ 
    
    
//---------------------------------------------------------------------------------------------    
    
    //          -------------------  PRIX TUBE -------------------
    
    
    //Changement du prix tube sur un mois
    @PostMapping(value = "/mois/prix/{id}")
    public String getPrixMois(@PathVariable Long id, Model model, Principal principal) {
        
        //Recupération de la DATA à modifier :
        ConsoMois editConsoMois = service.findByIdConsoMois(id);
        FormConsoMois formConsoMois = new FormConsoMois(id,
                                    editConsoMois.getNom(),
                                    editConsoMois.getIdPrixTube().getId());
         
        model.addAttribute("editConsoMois", formConsoMois);
        model.addAttribute("listPrixTubes", service.ListPrixTubeName(editConsoMois.getIdTypeVolant().getNomTypeTube()));
        model.addAttribute("numAction", ActionsTypes.EDIT_CONSOMOIS_PT.toString());

        
        PageActions pageAction = new PageActions();
        return pageAction.returnPage();
    }
    
    
    //Modifier une Prix-Tube
    @PutMapping("/mois/prix/{id}")
    public String editConsoMoisPrix(@ModelAttribute FormConsoMois putConsoMois, Model model, Principal principal) {
        int result = service.updateConsoMoisPrixtube(putConsoMois);
        
        PageIndex pageIndex = new PageIndex();   
        pageIndex.addReponse(messageSource, "prixtube", "edit", result);
                
        return pageIndex.getPage(model, principal, service);
    } 
    
//---------------------------------------------------------------------------------------------    
    
    //          -------------------  NB TUBES UTILISES -------------------


    //Changement du nombres de tubes utilisés sur le mois
    @PostMapping(value = "/mois/nbu/{id}")
    public String changeNbTubeUtilises(@PathVariable Long id, Model model, Principal principal) {
        
        //Recupération de la DATA à modifier :
        ConsoMois editConsoMois = service.findByIdConsoMois(id);
        FormConsoMois formConsoMois = new FormConsoMois(id,
                                    editConsoMois.getNom(),
                                    editConsoMois.getNbTubeUtilise());
         
        model.addAttribute("editConsoMois", formConsoMois);
        model.addAttribute("numAction", ActionsTypes.EDIT_CONSOMOIS_NBU.toString());
 
        PageActions pageAction = new PageActions();
        return pageAction.returnPage();
    }

    
    //Modifier nbTubeUtilises du consoMois
    @PutMapping("/mois/nbu/{id}")
    public String editConsoMoisNbUtilises(@ModelAttribute FormConsoMois putConsoMois, Model model, Principal principal) {
        int result = service.updateConsoMoisNbUtilises(putConsoMois);
        
        PageIndex pageIndex = new PageIndex();   
        pageIndex.addReponse(messageSource, "nbutilises", "edit", result);
                
        return pageIndex.getPage(model, principal, service);
    } 
    

//---------------------------------------------------------------------------------------------    
    
    //          -------------------  NB TUBES UTILISES -------------------


    //Changement du nombres de tubes commandés sur le mois
    @PostMapping(value = "/mois/nbc/{id}")
    public String changeNbTubeCommandes(@PathVariable Long id, Model model, Principal principal) {
        
        //Recupération de la DATA à modifier :
        ConsoMois editConsoMois = service.findByIdConsoMois(id);
        FormConsoMois formConsoMois = new FormConsoMois(id,
                                    editConsoMois.getNbTubeCommande(),
                                    editConsoMois.getNom());
         
        model.addAttribute("editConsoMois", formConsoMois);
        model.addAttribute("numAction", ActionsTypes.EDIT_CONSOMOIS_NBC.toString());
 
        PageActions pageAction = new PageActions();
        return pageAction.returnPage();
    }

    
    //Modifier nbTubeCommande du consoMois
    @PutMapping("/mois/nbc/{id}")
    public String editConsoMoisNbCommandes(@ModelAttribute FormConsoMois putConsoMois, Model model, Principal principal) {
        int result = service.updateConsoMoisNbCommandes(putConsoMois);
        
        PageIndex pageIndex = new PageIndex();   
        pageIndex.addReponse(messageSource, "nbcommandes", "edit", result);
                
        return pageIndex.getPage(model, principal, service);
    } 
    
     
    
    
}

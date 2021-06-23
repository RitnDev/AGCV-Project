package com.ritndev.agcv.controller;

import java.security.Principal;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.ritndev.agcv.pages.PageActions;
import com.ritndev.agcv.services.IUserService;
import com.ritndev.agcv.services.IagcvService;
import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.form.FormCompet;
import com.ritndev.agcv.model.Competition;
import com.ritndev.agcv.pages.PageSacCompetition;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.DeleteMapping;



/**
 *
 * @author Ritn
 */
@Controller
public class StockCompetController {
    
    @Autowired private IagcvService service;
    @Autowired private IUserService userService;
    @Autowired private MessageSource messageSource;
    
    
    //Création d'une nouvelle commande de membre
    @PostMapping("/newCompetition")
    public String newCompetition(@ModelAttribute FormCompet newCompetition, Model model, Principal principal) {
        int result = service.saveCompetition(newCompetition);
        
        PageSacCompetition pageSacCompetition = new PageSacCompetition(); 
        pageSacCompetition.addReponse(messageSource, "compet", "create", result);
        
        return pageSacCompetition.getPage(model, principal, service, userService);
    }
    
    
    
    //Supprimer un membre
    @DeleteMapping("/compet/{id}")
    public String supprCompet(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        int result = service.supprCompetition(id);
         
        PageSacCompetition pageSacCompetition = new PageSacCompetition(); 
        pageSacCompetition.addReponse(messageSource, "compet", "remove", result);
        
        return pageSacCompetition.getPage(model, principal, service, userService);
    }
    
    
    
    //Modifier une competition
    @PutMapping("/compet/{id}")
    public String editCompet(@ModelAttribute FormCompet putCompet, Model model, Principal principal) { 
        int result = service.updateCompetition(putCompet);

        PageSacCompetition pageSacCompetition = new PageSacCompetition(); 
        pageSacCompetition.addReponse(messageSource, "compet", "edit", result);
        
        return pageSacCompetition.getPage(model, principal, service, userService);
    }
    
    
    
    //Lancer la modification d'une competition
    @PostMapping("/compet/{id}")
    public String getCompet(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT COMPETITION");
        System.out.println("ID : " + id);
        
        //Recupération du membre à modifier :
        Competition editCompet = service.findByIdCompetition(id);
        FormCompet formCompet = new FormCompet(id, editCompet.getNbTubesUtilises(), editCompet.getNom());
        model.addAttribute("editCompet", formCompet);
        model.addAttribute("numAction", ActionsTypes.EDIT_COMPETITION.toString());
        
        PageActions pageAction = new PageActions();
        return pageAction.returnPage();
    }
    
}

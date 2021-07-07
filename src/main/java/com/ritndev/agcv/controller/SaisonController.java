package com.ritndev.agcv.controller;

import com.ritndev.agcv.InterfaceService.IMainDataService;
import com.ritndev.agcv.InterfaceService.ISaisonService;
import com.ritndev.agcv.InterfaceService.IUserService;
import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.form.FormSaison;
import com.ritndev.agcv.model.Saison;
import com.ritndev.agcv.pages.PageActions;
import com.ritndev.agcv.pages.PageSaison;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Ritn
 */
@Controller
public class SaisonController {
    
    @Autowired private IUserService userService;
    @Autowired private IMainDataService dataService;
    @Autowired private ISaisonService saisonService;
    
    @Autowired private MessageSource messageSource;
    
    
    @GetMapping(value = {"/admin/saison"})
    public String getSaison(Model model, Principal principal){     
        
        model.addAttribute("listSaisons", saisonService.listSaison());

        PageSaison pageSaison = new PageSaison(model, principal, messageSource);    
        boolean connect = userService.findRoleByUsername(pageSaison.returnUser(principal)).equals("ROLE_SUPADMIN");
        
        return pageSaison.getPage(dataService.returnMainData().getBudgetDefault(),connect);
    } 
    
    // ----------------------------------- PARTIE SAISON ------------------------------------- //
    
    
    //Création d'une nouvelle saison
    @PostMapping("/admin/newSaison")
    public String newSaison(@ModelAttribute FormSaison newSaison, Model model, Principal principal) {
                
        model.addAttribute("listSaisons", saisonService.listSaison());
        
        PageSaison pageSaison = new PageSaison(model, principal, messageSource); 
        
        int result = saisonService.saveSaison(newSaison);
        pageSaison.addReponse("saison", "create", result);
         
        boolean connect = userService.findRoleByUsername(pageSaison.returnUser(principal)).equals("ROLE_SUPADMIN");
        
        return pageSaison.getPage(dataService.returnMainData().getBudgetDefault(),connect);
    }
    
    
    //Supprimer une saison
    @DeleteMapping("/admin/saison/{id}")
    public String supprSaison(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        
        model.addAttribute("listSaisons", saisonService.listSaison());
        
        PageSaison pageSaison = new PageSaison(model, principal, messageSource); 
        
        int result = saisonService.supprSaison(id);
        pageSaison.addReponse("saison", "remove", result);
         
        boolean connect = userService.findRoleByUsername(pageSaison.returnUser(principal)).equals("ROLE_SUPADMIN");
        
        return pageSaison.getPage(dataService.returnMainData().getBudgetDefault(),connect);
    }
    
    
    //Modifier une saison
    @PutMapping("/admin/saison/{id}")
    public String editSaison(@ModelAttribute FormSaison putSaison, Model model, Principal principal) {
                
        model.addAttribute("listSaisons", saisonService.listSaison());
        
        PageSaison pageSaison = new PageSaison(model, principal, messageSource); 
        
        int result = saisonService.updateSaison(putSaison);
        pageSaison.addReponse("saison", "edit", result);
         
        boolean connect = userService.findRoleByUsername(pageSaison.returnUser(principal)).equals("ROLE_SUPADMIN");
        
        return pageSaison.getPage(dataService.returnMainData().getBudgetDefault(),connect);
    }
    
    
    //Lancer la modification d'une saison
    @PostMapping("/admin/saison/{id}")
    public String postSaison(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT SAISON");
        System.out.println(">> ID : " + id);
        
        //Recupération du membre à modifier :
        Saison editSaison = saisonService.findByIdSaison(id);
        FormSaison formSaison = new FormSaison(id, editSaison.getBudgetString(), editSaison.isActuelle());
        model.addAttribute("editSaison", formSaison);
        model.addAttribute("numAction", ActionsTypes.EDIT_SAISON.toString());
                
        PageActions pageAction = new PageActions(model, principal, messageSource);
        return pageAction.returnPage();
    }
    
    
}

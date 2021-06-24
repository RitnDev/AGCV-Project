package com.ritndev.agcv.controller;

import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.form.FormMembre;
import com.ritndev.agcv.form.FormPrixTube;
import com.ritndev.agcv.form.FormSaison;
import com.ritndev.agcv.model.Membre;
import com.ritndev.agcv.model.Saison;
import com.ritndev.agcv.pages.PageActions;
import com.ritndev.agcv.pages.PageAdmin;
import com.ritndev.agcv.services.IUserService;
import com.ritndev.agcv.services.IagcvService;
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
public class AdminController {
    
    @Autowired private IagcvService service;
    @Autowired private IUserService userService;
    @Autowired private MessageSource messageSource;
    
    
    
    //--------------------   Page Admin   ---------------------------- 
    
    @GetMapping(value = { "/admin", "/newMembre", "/newSaison", "/newPrixTube"})
    public String admin(Model model, Principal principal){     
        PageAdmin pageAdmin = new PageAdmin();
        return pageAdmin.getPage(model, principal, service, userService);
    } 
    
    
    
    
// ----------------------------------- PARTIE MEMBRE ------------------------------------- //
    
    
    //Création d'un nouveau membre
    @PostMapping("/newMembre")
    public String newMembre(@ModelAttribute FormMembre newMembre, Model model, Principal principal) {
        int result = service.saveMembre(newMembre);
                          
        PageAdmin pageAdmin = new PageAdmin();
        pageAdmin.addReponse(messageSource, "membre", "create", result);
    
        return pageAdmin.getPage(model, principal, service, userService);
    }
    
    
    //Supprimer un membre
    @DeleteMapping("/membre/{id}")
    public String supprMembre(@PathVariable(value = "id") Long id, Model model, Principal principal) {       
        int result = service.supprMembre(id);

        PageAdmin pageAdmin = new PageAdmin();
        pageAdmin.addReponse(messageSource, "membre", "remove", result);
        
        return pageAdmin.getPage(model, principal, service, userService);
    }
    
    //Modifier un membre
    @PutMapping("/membre/{id}")
    public String editMembre(@ModelAttribute FormMembre putMembre, Model model, Principal principal) {   
        int result = service.updateMembre(putMembre);
        
        PageAdmin pageAdmin = new PageAdmin();
        pageAdmin.addReponse(messageSource, "membre", "edit", result);
        
        return pageAdmin.getPage(model, principal, service, userService);
    }
    
    
    
    //Lancer la modification d'un membre
    @PostMapping("/membre/{id}")
    public String getMembre(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT MEMBRE");
        System.out.println("ID : " + id);
        
        //Recupération du membre à modifier :
        Membre editMembre = service.findByIdMembre(id);
        FormMembre formMembre = new FormMembre(id, editMembre.getPrenom(), editMembre.getNom());
        model.addAttribute("editMembre", formMembre);
        model.addAttribute("numAction", ActionsTypes.EDIT_MEMBRE.toString());
                
        PageActions pageAction = new PageActions();
        return pageAction.returnPage();
    }
    
    
    
    
    
    
    
// ----------------------------------- PARTIE SAISON ------------------------------------- //
    
    
    //Création d'une nouvelle saison
    @PostMapping("/newSaison")
    public String newSaison(@ModelAttribute FormSaison newSaison, Model model, Principal principal) {
        int result = service.saveSaison(newSaison);

        PageAdmin pageAdmin = new PageAdmin();
        pageAdmin.addReponse(messageSource, "saison", "create", result);

        return pageAdmin.getPage(model, principal, service, userService);
    }
    
    
    //Supprimer une saison
    @DeleteMapping("/saison/{id}")
    public String supprSaison(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        int result = service.supprSaison(id);
       
        PageAdmin pageAdmin = new PageAdmin();
        pageAdmin.addReponse(messageSource, "saison", "remove", result);
        
        return pageAdmin.getPage(model, principal, service, userService);
    }
    
    
    //Lancer la modification d'une saison
    @PostMapping("/saison/{id}")
    public String getSaison(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT SAISON");
        System.out.println(">> ID : " + id);
        
        //Recupération du membre à modifier :
        Saison editSaison = service.findByIdSaison(id);
        FormSaison formSaison = new FormSaison(id, editSaison.getBudgetString(), editSaison.isActuelle());
        model.addAttribute("editSaison", formSaison);
        model.addAttribute("numAction", ActionsTypes.EDIT_SAISON.toString());
                
        PageActions pageAction = new PageActions();
        return pageAction.returnPage();
    }
    
    
    //Modifier une saison
    @PutMapping("/saison/{id}")
    public String editSaison(@ModelAttribute FormSaison putSaison, Model model, Principal principal) {
        int result = service.updateSaison(putSaison);
        
        PageAdmin pageAdmin = new PageAdmin();
        pageAdmin.addReponse(messageSource, "saison", "edit", result);
        
        return pageAdmin.getPage(model, principal, service, userService);

    }
    
    
    
    // ----------------------------------- PARTIE PRIX-TUBES ------------------------------------- //
    
    
    //Création d'un nouveau prix-tube
    @PostMapping("/newPrixTube")
    public String newPrixTube(@ModelAttribute FormPrixTube newPrixTube, Model model, Principal principal) {
        PageAdmin pageAdmin = new PageAdmin();
        
        int result = service.savePrixTube(newPrixTube);
        System.out.println(">> result : " + result);
        pageAdmin.addReponse(messageSource, "prixtube", "create", result);
        
        return pageAdmin.getPage(model, principal, service, userService);
    }
    
    
    //Supprimer un prix-tube
    @DeleteMapping("/prixtube/{id}")
    public String supprPrixTube(@PathVariable(value = "id") Long id, Model model, Principal principal) {  
        int result = service.supprPrixTube(id);
        
        PageAdmin pageAdmin = new PageAdmin();
        pageAdmin.addReponse(messageSource, "prixtube", "remove", result);
        
        return pageAdmin.getPage(model, principal, service, userService);
    }
    
    
    //Lancer la modification d'un prix-tube
    @PostMapping("/prixtube/{id}")
    public String getPrixTube(@PathVariable Long id, Model model, Principal principal) {     
        System.out.println(">> POST - EDIT PRIX-TUBE");
        System.out.println(">> ID : " + id);
        
        //Recupération du membre à modifier :        
        model.addAttribute("editPrixTube", service.findByIdPrixTube(id));
        model.addAttribute("numAction", ActionsTypes.EDIT_PRIXTUBE.toString());
                
        PageActions pageAction = new PageActions();
        return pageAction.returnPage();
    }
    
    
    //Modifier un prix-tube
    @PutMapping("/prixtube/{id}")
    public String editPrixTube(@ModelAttribute FormPrixTube putPrixTube, Model model, Principal principal) {
        int result = service.updatePrixTube(putPrixTube);
        
        PageAdmin pageAdmin = new PageAdmin();
        pageAdmin.addReponse(messageSource, "prixtube", "edit", result);
        
        return pageAdmin.getPage(model, principal, service, userService);
    }
    
}

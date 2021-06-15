package com.ritndev.agcv.controller;

import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.form.FormCommande;
import com.ritndev.agcv.model.Commande;
import com.ritndev.agcv.pages.PageActions;
import com.ritndev.agcv.pages.PageCommandesMembres;
import com.ritndev.agcv.services.IagcvService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Ritn
 */
@Controller
public class CommandeController {
    
    @Autowired private IagcvService service;
    @Autowired private MessageSource messageSource;
    
    
    
    //Création d'une nouvelle commande de membre
    @PostMapping("/newCommande")
    public String newCommande(@ModelAttribute FormCommande newCommande, Model model, Principal principal) {
        int result = service.saveCommande(newCommande);
        
        PageCommandesMembres pageCommandesMembres = new PageCommandesMembres();
        pageCommandesMembres.addReponse(messageSource, "commande", "create", result);
        
        return pageCommandesMembres.getPage(model, principal, service);
    }
    
    
        
    //Supprimer un membre
    @DeleteMapping("/commande/{id}")
    public String supprCommande(@PathVariable(value = "id") Long id, Model model, Principal principal) {       
        int result = service.supprCommande(id);

        PageCommandesMembres pageCommandesMembres = new PageCommandesMembres();
        pageCommandesMembres.addReponse(messageSource, "commande", "remove", result);
        
        return pageCommandesMembres.getPage(model, principal, service);
    }
    
    //Modifier un membre
    @PutMapping("/commande/{id}")
    public String editCommande(@ModelAttribute FormCommande putCommande, Model model, Principal principal) {   
        int result = service.updateCommande(putCommande);
        
        PageCommandesMembres pageCommandesMembres = new PageCommandesMembres();
        pageCommandesMembres.addReponse(messageSource, "commande", "edit", result);
        
        return pageCommandesMembres.getPage(model, principal, service);
    }
    
    
    
    //Lancer la modification d'un membre
    @PostMapping("/commande/{id}")
    public String getCommande(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT COMMANDE");
        System.out.println("ID : " + id);
        
        //Recupération du membre à modifier :
        Commande editCommande = service.findByIdCommande(id);
        FormCommande formCommande = new FormCommande(id, editCommande.isRegler());
        model.addAttribute("editCommande", formCommande);
        model.addAttribute("numAction", ActionsTypes.EDIT_COMMANDE.toString());
                
        PageActions pageAction = new PageActions();
        return pageAction.returnPage();
    }
    
    
}

package com.ritndev.agcv.controller;

import com.ritndev.agcv.InterfaceService.ICommandeService;
import com.ritndev.agcv.InterfaceService.IMainDataService;
import com.ritndev.agcv.InterfaceService.IMembreService;
import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.form.FormCommande;
import com.ritndev.agcv.model.Commande;
import com.ritndev.agcv.model.ConsoMois;
import com.ritndev.agcv.pages.PageActions;
import com.ritndev.agcv.pages.PageCommandesMembres;

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
public class CommandeController {
    
    @Autowired private ICommandeService commandeService;
    @Autowired private IMembreService membreService;
    @Autowired private IMainDataService dataService;
    
    @Autowired private MessageSource messageSource;
    
    
    
    
    //--------------   Page Commande de tube des membres   ------------------
    
    @GetMapping(value = {"/commandesMembres"})
    public String commandesMembres(Model model, Principal principal){
        PageCommandesMembres pageCommandesMembres = new PageCommandesMembres(model, principal, messageSource);
        
        model.addAttribute("Membres", membreService.listMembreActif());
        model.addAttribute("Commandes", commandeService.listCommande());
        
        return pageCommandesMembres.getPage(dataService);
    }
    
    
    
    //Création d'une nouvelle commande de membre
    @PostMapping("/newCommande")
    public String newCommande1(@ModelAttribute FormCommande newCommande, Model model, Principal principal) {
       
        ConsoMois consoMois = dataService.returnMainData().getIdSaison().getConsoMois("Compétition", newCommande.getNomMois());
        newCommande.setIdPrixTube(consoMois.getIdPrixTube().getId());
        newCommande.setIdConsoMois(consoMois.getId());
        
        model.addAttribute("prixtube", consoMois.getIdPrixTube().toString());
        model.addAttribute("newCommande", newCommande);
        model.addAttribute("numAction", ActionsTypes.ADD_COMMANDE.toString());
              
        PageActions pageAction = new PageActions(model, principal, messageSource);
        return pageAction.returnPage();
    }

    @PostMapping("/commande")
    public String newCommande2(@ModelAttribute FormCommande newCommande, Model model, Principal principal) {
        int result = commandeService.saveCommande(newCommande);
        
        PageCommandesMembres pageCommandesMembres = new PageCommandesMembres(model, principal, messageSource);
        pageCommandesMembres.addReponse("commande", "create", result);
        
        model.addAttribute("Membres", membreService.listMembreActif());
        model.addAttribute("Commandes", commandeService.listCommande());
        
        return pageCommandesMembres.getPage(dataService);
    }
    
    
        
    //Supprimer un membre
    @DeleteMapping("/commande/{id}")
    public String supprCommande(@PathVariable(value = "id") Long id, Model model, Principal principal) {       
        int result = commandeService.supprCommande(id);

        PageCommandesMembres pageCommandesMembres = new PageCommandesMembres(model, principal, messageSource);
        pageCommandesMembres.addReponse("commande", "remove", result);
        
        model.addAttribute("Membres", membreService.listMembreActif());
        model.addAttribute("Commandes", commandeService.listCommande());
        
        return pageCommandesMembres.getPage(dataService);
    }
    
    //Modifier un membre
    @PutMapping("/commande/{id}")
    public String editCommande(@ModelAttribute FormCommande putCommande, Model model, Principal principal) {   
        int result = commandeService.updateCommande(putCommande);
        
        PageCommandesMembres pageCommandesMembres = new PageCommandesMembres(model, principal, messageSource);
        pageCommandesMembres.addReponse("commande", "edit", result);
        
        model.addAttribute("Membres", membreService.listMembreActif());
        model.addAttribute("Commandes", commandeService.listCommande());
        
        return pageCommandesMembres.getPage(dataService);
    }
    
    
    
    //Lancer la modification d'un membre
    @PostMapping("/commande/{id}")
    public String getCommande(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT COMMANDE");
        System.out.println("ID : " + id);
        
        //Recupération du membre à modifier :
        Commande editCommande = commandeService.findByIdCommande(id);
        FormCommande formCommande = new FormCommande(id, editCommande.isRegler());
        model.addAttribute("editCommande", formCommande);
        model.addAttribute("numAction", ActionsTypes.EDIT_COMMANDE.toString());
        
                
        PageActions pageAction = new PageActions(model, principal, messageSource);
        return pageAction.returnPage();
    }
    
    
}

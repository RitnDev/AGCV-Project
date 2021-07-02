package com.ritndev.agcv.controller;

import com.ritndev.agcv.InterfaceService.ICommandeService;
import com.ritndev.agcv.InterfaceService.IMembreService;
import com.ritndev.agcv.InterfaceService.IPrixTubeService;
import com.ritndev.agcv.InterfaceService.ISaisonService;
import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.classes.NomService;
import com.ritndev.agcv.form.FormCommande;
import com.ritndev.agcv.model.Commande;
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
    @Autowired private ISaisonService saisonService;
    @Autowired private IPrixTubeService prixTubeService;
    
    @Autowired private MessageSource messageSource;
    
    
    
    
    //--------------   Page Commande de tube des membres   ------------------
    
    @GetMapping(value = {"/commandesMembres", "/newCommande"})
    public String commandesMembres(Model model, Principal principal){
        PageCommandesMembres pageCommandesMembres = new PageCommandesMembres(model, principal);
        pageCommandesMembres.addService(NomService.MEMBRE.toString(), membreService);
        pageCommandesMembres.addService(NomService.SAISON.toString(), saisonService);
        pageCommandesMembres.addService(NomService.PRIXTUBE.toString(), prixTubeService);
        pageCommandesMembres.addService(NomService.COMMANDE.toString(), commandeService);
        
        return pageCommandesMembres.getPage();
    }
    
    
    
    //Création d'une nouvelle commande de membre
    @PostMapping("/newCommande")
    public String newCommande(@ModelAttribute FormCommande newCommande, Model model, Principal principal) {
        int result = commandeService.saveCommande(newCommande);
        
        PageCommandesMembres pageCommandesMembres = new PageCommandesMembres(model, principal);
        pageCommandesMembres.addReponse(messageSource, "commande", "create", result);
        pageCommandesMembres.addService(NomService.MEMBRE.toString(), membreService);
        pageCommandesMembres.addService(NomService.SAISON.toString(), saisonService);
        pageCommandesMembres.addService(NomService.PRIXTUBE.toString(), prixTubeService);
        pageCommandesMembres.addService(NomService.COMMANDE.toString(), commandeService);
        
        return pageCommandesMembres.getPage();
    }
    
    
        
    //Supprimer un membre
    @DeleteMapping("/commande/{id}")
    public String supprCommande(@PathVariable(value = "id") Long id, Model model, Principal principal) {       
        int result = commandeService.supprCommande(id);

        PageCommandesMembres pageCommandesMembres = new PageCommandesMembres(model, principal);
        pageCommandesMembres.addReponse(messageSource, "commande", "remove", result);
        pageCommandesMembres.addService(NomService.MEMBRE.toString(), membreService);
        pageCommandesMembres.addService(NomService.SAISON.toString(), saisonService);
        pageCommandesMembres.addService(NomService.PRIXTUBE.toString(), prixTubeService);
        pageCommandesMembres.addService(NomService.COMMANDE.toString(), commandeService);
        
        return pageCommandesMembres.getPage();
    }
    
    //Modifier un membre
    @PutMapping("/commande/{id}")
    public String editCommande(@ModelAttribute FormCommande putCommande, Model model, Principal principal) {   
        int result = commandeService.updateCommande(putCommande);
        
        PageCommandesMembres pageCommandesMembres = new PageCommandesMembres(model, principal);
        pageCommandesMembres.addReponse(messageSource, "commande", "edit", result);
        pageCommandesMembres.addService(NomService.MEMBRE.toString(), membreService);
        pageCommandesMembres.addService(NomService.SAISON.toString(), saisonService);
        pageCommandesMembres.addService(NomService.PRIXTUBE.toString(), prixTubeService);
        pageCommandesMembres.addService(NomService.COMMANDE.toString(), commandeService);
        
        return pageCommandesMembres.getPage();
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
        
                
        PageActions pageAction = new PageActions(model, principal);
        return pageAction.returnPage();
    }
    
    
}

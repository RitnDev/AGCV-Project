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
import com.ritndev.agcv.classes.TypeReponse;
import com.ritndev.agcv.form.FormCompet;
import com.ritndev.agcv.model.Competition;
import com.ritndev.agcv.pages.PageSacCompetition;
import org.springframework.web.bind.annotation.DeleteMapping;



/**
 *
 * @author Ritn
 */
@Controller
public class StockCompetController {
    
    @Autowired
    private IagcvService service;
    @Autowired
    private IUserService userService;
    
    
    //Création d'une nouvelle commande de membre
    @PostMapping("/newCompetition")
    public String newCompetition(@ModelAttribute FormCompet newCompetition, Model model, Principal principal) {
        
        System.out.println(">> POST");
        System.out.println("Nom de la competition : " + newCompetition.getNom());
        System.out.println("Nombre de tubes utilisés : " + newCompetition.getNbTubesUtilises());
        
        int result = service.saveCompetition(newCompetition);
        String reponse = "Compétition non créée.";
        TypeReponse tr = TypeReponse.ERROR;
        
        PageSacCompetition pageSacCompetition = new PageSacCompetition(); 
        
        switch (result) {
            case 1 -> {
                reponse = "Compétition non créée.";
                pageSacCompetition.addReponse(TypeReponse.ERROR, "Aucun MAIN-DATA créée.");
            }
            case 2 -> {
                reponse = "Compétition non créée.";
                pageSacCompetition.addReponse(TypeReponse.ERROR, "Aucune saison active actuellement.");
            }
            case 3 -> {
                reponse = "Compétition : '" + newCompetition.toString() + "' - créer avec succès";
                tr = TypeReponse.ADD;
            }
            default -> tr = TypeReponse.ERROR;
        }
         
        pageSacCompetition.addReponse(tr, reponse);
        
        return pageSacCompetition.getPage(model, principal, service, userService);
    }
    
    
    
    //Supprimer un membre
    @DeleteMapping("/compet/{id}")
    public String supprCompet(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        
        System.out.println(">> DELETE - COMPETITION");
        System.out.println("ID : " + id);
        
        int result = service.supprCompetition(id);
        String reponse = "Compétition non supprimée";
        TypeReponse tr = TypeReponse.ERROR;
        
        PageSacCompetition pageSacCompetition = new PageSacCompetition(); 
        
        switch (result) {
            case 1 -> {
                reponse = "Compétition supprimée avec succès !";
                tr = TypeReponse.REMOVE;
            }
            default -> tr = TypeReponse.ERROR;
        }

        pageSacCompetition.addReponse(tr, reponse);
        
        return pageSacCompetition.getPage(model, principal, service, userService);
    }
    
    
    
    //Modifier une competition
    @PutMapping("/compet/{id}")
    public String editCompet(@ModelAttribute FormCompet putCompet, Model model, Principal principal) {
        
        System.out.println(">> PUT - EDIT COMPETITION");
        System.out.println("ID : " + putCompet.getId());
        System.out.println("Nbr tubes utilisés : " + putCompet.getNbTubesUtilises());
        System.out.println("Nom : " + putCompet.getNom());
        
        int result = service.updateCompetition(putCompet);
        String reponse = "Compétition non modifiée";
        TypeReponse tr = TypeReponse.ERROR;
        
        PageSacCompetition pageSacCompetition = new PageSacCompetition(); 
        
        switch (result) {
            case 1 -> {
                reponse = "Compétition modifiée avec succès !";
                tr = TypeReponse.EDIT;
            }
            default -> tr = TypeReponse.ERROR;
        }

        pageSacCompetition.addReponse(tr, reponse);
        
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
        model.addAttribute("numAction", ActionsTypes.EDIT_COMPETITION.getValue());
        
        PageActions pageAction = new PageActions();
        return pageAction.returnPage();
    }
    
}

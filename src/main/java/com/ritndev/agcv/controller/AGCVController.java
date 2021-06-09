package com.ritndev.agcv.controller;

import java.security.Principal;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.ritndev.agcv.form.FormCommande;
import com.ritndev.agcv.form.FormUser;
import com.ritndev.agcv.model.AppUser;
import com.ritndev.agcv.pages.PageActions;
import com.ritndev.agcv.pages.PageCommandesMembres;
import com.ritndev.agcv.pages.PageIndex;
import com.ritndev.agcv.services.IUserService;
import com.ritndev.agcv.services.IagcvService;
import com.ritndev.agcv.classes.ActionsTypes;
import org.springframework.context.MessageSource;



/**
 *
 * @author Ritn
 */
@Controller
public class AGCVController {
    
    @Autowired private IagcvService service;
    @Autowired private IUserService userService;
    @Autowired private MessageSource messageSource;
    
    //Création d'une nouvelle commande de membre
    @PostMapping("/newCommande")
    public String newCommande(@ModelAttribute FormCommande newCommande, Model model, Principal principal) {
        
        System.out.println(">> POST");
        System.out.println("Membre : " + newCommande.getMembre());
        System.out.println("Nombre de tubes commnadés : " + newCommande.getNbTubeCommande());
        System.out.println("réglée ? " + newCommande.isRegler());
        
        PageCommandesMembres pageCommandesMembres = new PageCommandesMembres();
        return pageCommandesMembres.getPage(model, principal, service);
    }
    
    
    
    //Lancer la modification d'un utilisateur
    @PostMapping("/mdp/{username}")
    public String getUsername(@PathVariable String username, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT USER");
        System.out.println("Username : " + username);
        
        //Recupération de l'AppUser à modifier :
        AppUser editUser = userService.findByUsernameUser(username);
        FormUser formUser = new FormUser(editUser.getUserId(), editUser.getUserName());

        model.addAttribute("editUser", formUser);
        model.addAttribute("numAction", ActionsTypes.EDIT_MDP.toString());
                
        PageActions pageAction = new PageActions();
        return pageAction.returnPage();
    }
    
    
    //Lancer la modification d'un utilisateur
    @PutMapping("/mdp/{id}")
    public String editUsername(@ModelAttribute FormUser putUser, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT USER (MDP)");
        System.out.println("ID : " + putUser.getId());
        
        //Modification du mot de passe utilisateur :
        int result = userService.updateMdpUser(putUser);
                
        PageIndex pageIndex = new PageIndex();
        pageIndex.addReponse(messageSource, "mdp", "edit", result);
        
        return pageIndex.getPage(model, principal);
        
    }
    
     
}

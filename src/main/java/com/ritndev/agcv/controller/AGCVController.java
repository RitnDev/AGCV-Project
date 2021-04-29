package com.ritndev.agcv.controller;


import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.ActionsTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ritndev.agcv.form.FormCommande;
import com.ritndev.agcv.form.FormMembre;
import com.ritndev.agcv.form.FormSaison;
import com.ritndev.agcv.model.Membre;

import com.ritndev.agcv.services.IagcvService;
import com.ritndev.agcv.utils.WebUtils;
import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



/**
 *
 * @author Ritn
 */
@Controller
public class AGCVController {
    
    @Autowired
    private IagcvService service;
    
    private final String title = "AGCV - ";
    private final Link logout = new Link("Page principale", "logout");
    private final Link admin = new Link("Admin", "admin");
    
    
    // ID introuvable
    @GetMapping("/{id}")
    public String page405() {
        System.out.println(">> CHEH !!!");
        return "redirect:/logout";
    }
    
    
    
    //Création d'un nouveau membre
    @PostMapping("/newMembre")
    public String newMembre(@ModelAttribute FormMembre newMembre) {
        System.out.println(">> POST");
        System.out.println("Prenom : " + newMembre.getPrenom());
        System.out.println("Nom : " + newMembre.getNom());
        
        Membre addMembre = new Membre(newMembre.getPrenom(),newMembre.getNom());
        service.saveMembre(addMembre);
        
        return "redirect:/admin";
    }
    
    
    //Supprimer un membre
    @DeleteMapping("/{id}")
    public String supprMembre(@PathVariable(value = "id") Long id) {
        
        System.out.println(">> DELETE - MEMBRE");
        System.out.println("ID : " + id);
        
        Membre delMembre = service.findByIdMembre(id);
        service.supprMembre(delMembre);
        
        return "redirect:/admin";
    }
    
    //Modifier un membre
    @PutMapping("/{id}")
    public String editMembre(@ModelAttribute FormMembre putMembre) {
        
        System.out.println(">> PUT - EDIT MEMBRE");
        System.out.println("ID : " + putMembre.getId());
        System.out.println("Prenom : " + putMembre.getPrenom());
        System.out.println("Nom : " + putMembre.getNom());
        
        Long id = putMembre.getId();
        Membre editMembre = new Membre(putMembre.getPrenom(), putMembre.getNom());
        
        service.updateByIdMembre(id, editMembre);
        
        return "redirect:/admin";

    }
    
    
    
    //Lancer la modification d'un membre
    @PostMapping("/{id}")
    public String getMembre(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT MEMBRE");
        System.out.println("ID : " + id);
        
        //Recupération du membre à modifier :
        Membre editMembre = service.findByIdMembre(id);
        FormMembre formMembre = new FormMembre(id, editMembre.getPrenom(), editMembre.getNom());
        model.addAttribute("editMembre", formMembre);
        model.addAttribute("numAction", ActionsTypes.EDIT_MEMBRE.getValue());
                
        
        /* ----   En tête standard de la page   ----  */
        
            String page = "";
            Link[] links = new Link[] {logout};  
            Link[] menus = new Link[] {};

            // Add Attribute :
            model.addAttribute("pageTitle", "AGCV");
            model.addAttribute("pageName", page);
            model.addAttribute("buttonAdmin", false);
            model.addAttribute("adminPage", admin);
            model.addAttribute("menus", menus);    
            model.addAttribute("links", links);


            Boolean connect = false;
            String user = "";
            if (principal != null) {
                User loginedUser = (User) ((Authentication) principal).getPrincipal();
                user = WebUtils.toString(loginedUser);
                if(user.equals("")){}else{connect = true;}
            }
            model.addAttribute("log", "Connecté : " + user);
            model.addAttribute("connect", connect);
        
        /* --------  */
        
        return "actions";
    }
    
   
    
        
    //Création d'une nouvelle commande de membre
    @PostMapping("/newCommande")
    public String newCommande(@ModelAttribute FormCommande newCommande) {
        
        System.out.println(">> POST");
        System.out.println("Membre : " + newCommande.getMembre());
        System.out.println("Nombre de tubes commnadés : " + newCommande.getNbTubeCommande());
        System.out.println("réglée ? " + newCommande.isRegler());
        
        return "redirect:/commandesMembres";
    }
    
    
    
    
    //Création d'une nouvelle saison
    @PostMapping("/newSaison")
    public String newSaison(@ModelAttribute FormSaison newSaison) {
        System.out.println(">> POST");
        System.out.println("nom de la saison : " + newSaison.toString());
        System.out.println("Budget de la saison : " + newSaison.getBudget());
        
        return "redirect:/admin";
    }
    
    
    
    
    
    
    
}

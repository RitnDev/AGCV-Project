package com.ritndev.agcv.controller;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormTest;
import com.ritndev.agcv.utils.WebUtils;
import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Ritn
 */
@Controller
public class TestController {
    
    private final String title = "AGCV - ";
    
    //Links :
    private final Link index = new Link("Page principale","index");
    private final Link admin = new Link("Admin", "admin");
    /*
    private final Link logoutAdmin = new Link("Déconnexion", "logout");
  
    private final Link commandesMembres = new Link("Commandes tubes des membres","commandesMembres");
    private final Link histoSaison = new Link("Historiques des saisons précédentes", "histoSaison", "topnav-menu");
    private final Link sacCompetition = new Link("Gestion du sac de compétition", "sacCompetition", "topnav-menu");
    
    private final Link newCommande = new Link("Nouvelle commande", "#", "topnav-menu-commande");
    private final Link newSaison = new Link("Nouvelle saison", "#", "topnav-menu-saison");
    private final Link newMembre = new Link("Nouveau membre", "#", "topnav-menu-membre");   
    */
    
    
    @GetMapping("/test")
    public String pageTest(Model model, Principal principal){
        
        String page = "Page de test";
        String returnPage = "test";
        
        FormTest nouveauTest = new FormTest();
        
        Link[] links = new Link[] {};

        Link[] menus = new Link[] { 
            index
        };
        
        String message = "Ici se trouve : ";
        message = message + page;
                
        // Add Attribute :
        model.addAttribute("pageTitle", title + page);
        model.addAttribute("pageName", page);
        model.addAttribute("buttonAdmin", false);
        model.addAttribute("adminPage", admin);
        model.addAttribute("menus", menus);    
        model.addAttribute("links", links);
        model.addAttribute("message", message);
        model.addAttribute("newTest", nouveauTest);

        
        Boolean connect = false;
        String user = "";
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            user = WebUtils.toString(loginedUser);
            if(user.equals("")){}else{connect = true;}
        }
        model.addAttribute("log", "Connecté : " + user);
        model.addAttribute("connect", connect);
        
        
        return returnPage;
    }
    
    
    
    //Création d'un nouveau test
    @PostMapping("/newTest")
    public String newTest(@ModelAttribute FormTest newTest) {
        //FormTest addTest = newTest;
        //System.out.println(">> POST");
        //System.out.println("Prenom : " + newTest.getPrenom());
        //System.out.println("Nom : " + newTest.getNom());
        
        System.out.println(">> POST");
        System.out.println("Prenom : " + newTest.getPrenom());
        System.out.println("Nom : " + newTest.getNom());
        
        return "redirect:/resultTest";
    }
    
    @GetMapping("/resultTest")
    public String pageResult(Model model, Principal principal){
        
        String page = "Page de résultat du test";
        String returnPage = "resultTest";
               
        Link[] links = new Link[] {};

        Link[] menus = new Link[] { 
            index
        };
        
        String message = "Ici se trouve : ";
        message = message + page;
                
        // Add Attribute :
        model.addAttribute("pageTitle", title + page);
        model.addAttribute("pageName", page);
        model.addAttribute("buttonAdmin", false);
        model.addAttribute("adminPage", admin);
        model.addAttribute("menus", menus);    
        model.addAttribute("links", links);
        model.addAttribute("message", message);

        
        Boolean connect = false;
        String user = "";
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            user = WebUtils.toString(loginedUser);
            if(user.equals("")){}else{connect = true;}
        }
        model.addAttribute("log", "Connecté : " + user);
        model.addAttribute("connect", connect);
        
        
        return returnPage;
    }
    
}

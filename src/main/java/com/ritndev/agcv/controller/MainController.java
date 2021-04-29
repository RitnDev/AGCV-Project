package com.ritndev.agcv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ritndev.agcv.utils.WebUtils;
import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormCommande;
import com.ritndev.agcv.form.FormMembre;
import com.ritndev.agcv.form.FormSaison;
import com.ritndev.agcv.model.Membre;
import com.ritndev.agcv.services.IagcvService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *
 * @author Ritn
 */

@Controller
public class MainController {
       
    @Autowired
    private IagcvService service;
    
    private final String title = "AGCV - ";
    
    //Links :
    private final Link index = new Link("Page principale","index");
    private final Link admin = new Link("Admin", "admin");
    private final Link logoutAdmin = new Link("Déconnexion", "logout");
  
    private final Link commandesMembres = new Link("Commandes tubes des membres","commandesMembres");
    private final Link histoSaison = new Link("Historiques des saisons précédentes", "histoSaison", "topnav-menu");
    private final Link sacCompetition = new Link("Gestion du sac de compétition", "sacCompetition", "topnav-menu");
    
    private final Link newCommande = new Link("Nouvelle commande", "#", "topnav-menu-commande");
    private final Link newSaison = new Link("Nouvelle saison", "#", "topnav-menu-saison");
    private final Link newMembre = new Link("Nouveau membre", "#", "topnav-menu-membre");        
   
    
    
//--------------------   Page Index   ----------------------------
    
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model, Principal principal){
        
        String page = "Consommation de tubes sur la saison";
        String returnPage = index.getHref();
        
        Link[] links = new Link[] {
            commandesMembres
        };

        Link[] menus = new Link[] { 
            histoSaison,
            sacCompetition
        };
        
        String message = "Ici se trouve la page d'accueil, elle s'appelle également : ";
        message = message + page;
                
        // Add Attribute :
        model.addAttribute("pageTitle", title + page);
        model.addAttribute("pageName", page);
        model.addAttribute("buttonAdmin", true);
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
 

    
    
//--------------------   Page Admin   ---------------------------- 
    
    @GetMapping("/admin")
    public String admin(Model model, Principal principal){
        String page = "Administrateur";
        String returnPage = admin.getHref();       
        
        //liste des membres en BDD
        List<Membre> listMembres = service.listMembre();
        
        
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int year1 = year + 1;
        
        FormMembre nouveauMembre = new FormMembre("","");
        FormSaison nouvelleSaison = new FormSaison();
        nouvelleSaison.setAnnee_debut(year);
        nouvelleSaison.setAnnee_fin(year1);
        nouvelleSaison.setBudget("1000.00");
        
        Link[] links = new Link[] {
            commandesMembres,
            index
        };
                
        Link[] menus = new Link[] {
            newSaison,
            newMembre
        };
        
        String message = "Ici se trouve la page : ";
        message = message + page;
        
        // Add Attribute :
        model.addAttribute("pageTitle", title + page);
        model.addAttribute("pageName", page);
        model.addAttribute("buttonAdmin", true);
        model.addAttribute("adminPage", logoutAdmin);
        model.addAttribute("menus", menus);
        model.addAttribute("links", links);
        model.addAttribute("message", message);
        model.addAttribute("newMembre", nouveauMembre);
        model.addAttribute("newSaison", nouvelleSaison);
        model.addAttribute("listMembres", listMembres);
        
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
    

    
   
    
    
//--------------   Page Commande de tube des membres   ------------------
    
    @RequestMapping(value = "/commandesMembres", method = RequestMethod.GET)
    public String commandesMembres(Model model, Principal principal){
        
        // recupérer la listes des membre en BDD.
        List<String> listMembres = new ArrayList<>();
        for(Membre m : service.listMembre()) {
            listMembres.add(m.toString());
        }
        
        
        FormCommande nouvelleCommande = new FormCommande("", 1, false);
        
        String page = commandesMembres.toString();
        String returnPage = commandesMembres.getHref();
        
        Link[] links = new Link[] {
            index
        };
        
        Link[] menus = new Link[] { 
            newCommande
        };
        
        String message = "Ici se trouve la page : ";
        message = message + page;
                
        // Add Attribute :
        model.addAttribute("pageTitle", title + page);
        model.addAttribute("pageName", page);
        model.addAttribute("buttonAdmin", true);
        model.addAttribute("adminPage", admin);
        model.addAttribute("menus", menus);    
        model.addAttribute("links", links);
        model.addAttribute("message", message);
        model.addAttribute("newCommande", nouvelleCommande);
        model.addAttribute("Membres", listMembres);
        
        
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
    
  
    
//--------------   Page Historique des saisons précédentes   ------------------
    
    @RequestMapping(value = "/histoSaison", method = RequestMethod.GET)
    public String histoSaison(Model model, Principal principal){
        
        String page = histoSaison.toString();
        String returnPage = histoSaison.getHref();
        
        Link[] links = new Link[] {
            index
        };
                
        Link[] menus = new Link[] {};
        
        String message = "Ici se trouve la page : ";
        message = message + page;
                
        // Add Attribute :
        model.addAttribute("pageTitle", title + page);
        model.addAttribute("pageName", page);
        model.addAttribute("buttonAdmin", true);
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
    
    
    
//--------------   Page Commande de tube des membres   ------------------
    
    @RequestMapping(value = "/sacCompetition", method = RequestMethod.GET)
    public String sacCompetition(Model model, Principal principal){
        
        String page = sacCompetition.toString();
        String returnPage = sacCompetition.getHref();
        
        Link[] links = new Link[] {
            index
        };
                
        Link[] menus = new Link[] {};
        
        String message = "Ici se trouve la page : ";
        message = message + page;
                
        // Add Attribute :
        model.addAttribute("pageTitle", title + page);
        model.addAttribute("pageName", page);
        model.addAttribute("buttonAdmin", true);
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

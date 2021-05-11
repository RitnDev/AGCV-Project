package com.ritndev.agcv.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ritndev.agcv.form.FormCommande;
import com.ritndev.agcv.pages.PageCommandesMembres;

import com.ritndev.agcv.services.IagcvService;
import java.security.Principal;
import org.springframework.ui.Model;




/**
 *
 * @author Ritn
 */
@Controller
public class AGCVController {
    
    @Autowired
    private IagcvService service;
    
    
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
    
     
}

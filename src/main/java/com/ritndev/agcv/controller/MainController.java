package com.ritndev.agcv.controller;

import org.springframework.ui.Model;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import com.ritndev.agcv.services.IagcvService;

import com.ritndev.agcv.pages.PageCommandesMembres;
import com.ritndev.agcv.pages.PageHistoSaison;
import com.ritndev.agcv.pages.PageIndex;
import com.ritndev.agcv.pages.PageSacCompetition;
import com.ritndev.agcv.services.IUserService;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *
 * @author Ritn
 */

@Controller
public class MainController {
       
    @Autowired private IagcvService service;
    @Autowired private IUserService userService;
    
     
//--------------------   Page Index   ----------------------------
    
    @GetMapping(value = { "/", "/index"})
    public String index(Model model, Principal principal){
        PageIndex pageIndex = new PageIndex();
        return pageIndex.getPage(model, principal);
    }
 
    
//--------------   Page Commande de tube des membres   ------------------
    
    @GetMapping(value = {"/commandesMembres", "/newCommande"})
    public String commandesMembres(Model model, Principal principal){
        PageCommandesMembres pageCommandesMembres = new PageCommandesMembres();
        return pageCommandesMembres.getPage(model, principal, service);
    }
    
    
//--------------   Page Historique des saisons précédentes   ------------------
    
    @GetMapping(value = "/histoSaison")
    public String histoSaison(Model model, Principal principal){
        PageHistoSaison pageHistoSaison = new PageHistoSaison();
        return pageHistoSaison.getPage(model, principal); 
    }
    
    
//--------------   Page Commande de tube des membres   ------------------
    
    @GetMapping(value = "/sacCompetition")
    public String sacCompetition(Model model, Principal principal){
        PageSacCompetition pageSacCompetition = new PageSacCompetition();
        return pageSacCompetition.getPage(model, principal, service, userService); 
    }
    
    
}

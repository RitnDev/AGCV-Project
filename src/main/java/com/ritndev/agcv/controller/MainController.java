package com.ritndev.agcv.controller;


import org.springframework.ui.Model;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.stereotype.Controller;
import com.ritndev.agcv.services.IagcvService;

import com.ritndev.agcv.pages.PageAdmin;
import com.ritndev.agcv.pages.PageCommandesMembres;
import com.ritndev.agcv.pages.PageHistoSaison;
import com.ritndev.agcv.pages.PageIndex;
import com.ritndev.agcv.pages.PageSacCompetition;


/**
 *
 * @author Ritn
 */

@Controller
public class MainController {
       
    @Autowired
    private IagcvService service;
    
     
//--------------------   Page Index   ----------------------------
    
    @RequestMapping(value = { "/", "/index"}, method = RequestMethod.GET)
    public String index(Model model, Principal principal){
        PageIndex pageIndex = new PageIndex();
        return pageIndex.getPage(model, principal);
    }
 
    
//--------------------   Page Admin   ---------------------------- 
    
    @RequestMapping(value = { "/admin", "/newMembre", "/newSaison"}, method = RequestMethod.GET)
    public String admin(Model model, Principal principal){     
        PageAdmin pageAdmin = new PageAdmin();
        model.addAttribute("test", null);
        return pageAdmin.getPage(model, principal, service);
    }

    
//--------------   Page Commande de tube des membres   ------------------
    
    @RequestMapping(value = {"/commandesMembres", "/newCommande"}, method = RequestMethod.GET)
    public String commandesMembres(Model model, Principal principal){
        PageCommandesMembres pageCommandesMembres = new PageCommandesMembres();
        return pageCommandesMembres.getPage(model, principal, service);
    }
    
    
//--------------   Page Historique des saisons précédentes   ------------------
    
    @RequestMapping(value = "/histoSaison", method = RequestMethod.GET)
    public String histoSaison(Model model, Principal principal){
        PageHistoSaison pageHistoSaison = new PageHistoSaison();
        return pageHistoSaison.getPage(model, principal); 
    }
    
    
//--------------   Page Commande de tube des membres   ------------------
    
    @RequestMapping(value = "/sacCompetition", method = RequestMethod.GET)
    public String sacCompetition(Model model, Principal principal){
        PageSacCompetition pageSacCompetition = new PageSacCompetition();
        return pageSacCompetition.getPage(model, principal); 
    }
    
    
}

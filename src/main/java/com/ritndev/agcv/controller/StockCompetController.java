package com.ritndev.agcv.controller;

import com.ritndev.agcv.InterfaceService.ICompetitionService;
import com.ritndev.agcv.InterfaceService.IMainDataService;
import com.ritndev.agcv.InterfaceService.IUserService;
import java.security.Principal;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import com.ritndev.agcv.pages.PageActions;
import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.classes.NomService;
import com.ritndev.agcv.form.FormCompet;
import com.ritndev.agcv.model.Competition;
import com.ritndev.agcv.model.Saison;
import com.ritndev.agcv.model.StockCompetition;
import com.ritndev.agcv.pages.PageSacCompetition;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



/**
 *
 * @author Ritn
 */
@Controller
public class StockCompetController {
    
    @Autowired private ICompetitionService competitionService;
    @Autowired private IMainDataService dataService;
    @Autowired private IUserService userService;
    
    @Autowired private MessageSource messageSource;
    
    
    
    
    //--------------   Page Commande de tube des membres   ------------------
    
    @GetMapping(value = "/sacCompetition")
    public String sacCompetition(Model model, Principal principal){
        PageSacCompetition pageSacCompetition = new PageSacCompetition(model, principal);
        
        Saison saisonActuelle = dataService.returnMainData().getIdSaison();
        StockCompetition stockActuel = dataService.returnMainData().getIdStockCompet();
        
        int AdminConnect = 0;
        
        if (!pageSacCompetition.returnUser(principal).equals("")){
            switch (userService.findRoleByUsername(pageSacCompetition.returnUser(principal))) {
                case "ROLE_ADMIN" -> AdminConnect = 1;
                case "ROLE_SUPADMIN" -> AdminConnect = 2;
            }
        }
        
        model.addAttribute("AdminConnect", AdminConnect);
        model.addAttribute("saison", saisonActuelle);
        model.addAttribute("stock", stockActuel);
        
        return pageSacCompetition.getPage(); 
    }
    
    
    //Création d'une nouvelle commande de membre
    @PostMapping("/newCompetition")
    public String newCompetition(@ModelAttribute FormCompet newCompetition, Model model, Principal principal) {
        int result = competitionService.saveCompetition(newCompetition);
        
        PageSacCompetition pageSacCompetition = new PageSacCompetition(model, principal); 
        pageSacCompetition.addReponse(messageSource, "compet", "create", result);
        
        Saison saisonActuelle = dataService.returnMainData().getIdSaison();
        StockCompetition stockActuel = dataService.returnMainData().getIdStockCompet();
        
        int AdminConnect = 0;
        
        if (!pageSacCompetition.returnUser(principal).equals("")){
            switch (userService.findRoleByUsername(pageSacCompetition.returnUser(principal))) {
                case "ROLE_ADMIN" -> AdminConnect = 1;
                case "ROLE_SUPADMIN" -> AdminConnect = 2;
            }
        }
        
        model.addAttribute("AdminConnect", AdminConnect);
        model.addAttribute("saison", saisonActuelle);
        model.addAttribute("stock", stockActuel);
        
        return pageSacCompetition.getPage();
    }
    
    
    
    //Supprimer un membre
    @DeleteMapping("/compet/{id}")
    public String supprCompet(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        int result = competitionService.supprCompetition(id);
         
        PageSacCompetition pageSacCompetition = new PageSacCompetition(model, principal);
        pageSacCompetition.addReponse(messageSource, "compet", "remove", result);
        
        Saison saisonActuelle = dataService.returnMainData().getIdSaison();
        StockCompetition stockActuel = dataService.returnMainData().getIdStockCompet();
        
        int AdminConnect = 0;
        
        if (!pageSacCompetition.returnUser(principal).equals("")){
            switch (userService.findRoleByUsername(pageSacCompetition.returnUser(principal))) {
                case "ROLE_ADMIN" -> AdminConnect = 1;
                case "ROLE_SUPADMIN" -> AdminConnect = 2;
            }
        }
        
        model.addAttribute("AdminConnect", AdminConnect);
        model.addAttribute("saison", saisonActuelle);
        model.addAttribute("stock", stockActuel);
        
        return pageSacCompetition.getPage();
    }
    
    
    
    //Modifier une competition
    @PutMapping("/compet/{id}")
    public String editCompet(@ModelAttribute FormCompet putCompet, Model model, Principal principal) { 
        int result = competitionService.updateCompetition(putCompet);

        PageSacCompetition pageSacCompetition = new PageSacCompetition(model, principal);
        pageSacCompetition.addReponse(messageSource, "compet", "edit", result);
        
        Saison saisonActuelle = dataService.returnMainData().getIdSaison();
        StockCompetition stockActuel = dataService.returnMainData().getIdStockCompet();
        
        int AdminConnect = 0;
        
        if (!pageSacCompetition.returnUser(principal).equals("")){
            switch (userService.findRoleByUsername(pageSacCompetition.returnUser(principal))) {
                case "ROLE_ADMIN" -> AdminConnect = 1;
                case "ROLE_SUPADMIN" -> AdminConnect = 2;
            }
        }
        
        model.addAttribute("AdminConnect", AdminConnect);
        model.addAttribute("saison", saisonActuelle);
        model.addAttribute("stock", stockActuel);
        
        return pageSacCompetition.getPage();
    }
    
    
    
    //Lancer la modification d'une competition
    @PostMapping("/compet/{id}")
    public String getCompet(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT COMPETITION");
        System.out.println("ID : " + id);
        
        //Recupération du membre à modifier :
        Competition editCompet = competitionService.findByIdCompetition(id);
        FormCompet formCompet = new FormCompet(id, editCompet.getNbTubesUtilises(), editCompet.getNom());
        model.addAttribute("editCompet", formCompet);
        model.addAttribute("numAction", ActionsTypes.EDIT_COMPETITION.toString());
        
        PageActions pageAction = new PageActions(model, principal);
        return pageAction.returnPage();
    }
    
}

package com.ritndev.agcv.controller;

import com.ritndev.agcv.InterfaceService.ICompetitionService;
import com.ritndev.agcv.InterfaceService.IMainDataService;
import com.ritndev.agcv.InterfaceService.IStockService;
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
import com.ritndev.agcv.form.FormCompet;
import com.ritndev.agcv.form.FormStock;
import com.ritndev.agcv.model.Competition;
import com.ritndev.agcv.model.StockCompetition;
import com.ritndev.agcv.pages.PageCompetition;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



/**
 *
 * @author Ritn
 */
@Controller
public class CompetController {
    
    @Autowired private ICompetitionService competitionService;
    @Autowired private IStockService stockService;
    @Autowired private IMainDataService dataService;
    @Autowired private IUserService userService;
    
    @Autowired private MessageSource messageSource;
    
    
    
    
    //--------------   Page Commande de tube des membres   ------------------
    
    @GetMapping(value = "/competition")
    public String sacCompetition(Model model, Principal principal){
        PageCompetition pageCompet = new PageCompetition(model, principal, messageSource);
        
        int AdminConnect = 0;
        if (!pageCompet.returnUser(principal).equals("")){
            switch (userService.findRoleByUsername(pageCompet.returnUser(principal))) {
                case "ROLE_ADMIN" -> AdminConnect = 1;
                case "ROLE_SUPADMIN" -> AdminConnect = 2;
            }
        }
        
        model.addAttribute("AdminConnect", AdminConnect);
               
        return pageCompet.getPage(dataService); 
    }
    
    
    //Création d'une nouvelle commande de membre
    @PostMapping("/newCompetition")
    public String newCompetition(@ModelAttribute FormCompet newCompetition, Model model, Principal principal) {
        int result = competitionService.saveCompetition(newCompetition);
        
        PageCompetition pageCompet = new PageCompetition(model, principal, messageSource); 
        pageCompet.addReponse("compet", "create", result);
        
        int AdminConnect = 0;
        if (!pageCompet.returnUser(principal).equals("")){
            switch (userService.findRoleByUsername(pageCompet.returnUser(principal))) {
                case "ROLE_ADMIN" -> AdminConnect = 1;
                case "ROLE_SUPADMIN" -> AdminConnect = 2;
            }
        }
        
        model.addAttribute("AdminConnect", AdminConnect);
        
        return pageCompet.getPage(dataService);
    }
    
    
    
    //Supprimer un membre
    @DeleteMapping("/compet/{id}")
    public String supprCompet(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        int result = competitionService.supprCompetition(id);
         
        PageCompetition pageCompet = new PageCompetition(model, principal, messageSource);
        pageCompet.addReponse("compet", "remove", result);
        
        int AdminConnect = 0;
        if (!pageCompet.returnUser(principal).equals("")){
            switch (userService.findRoleByUsername(pageCompet.returnUser(principal))) {
                case "ROLE_ADMIN" -> AdminConnect = 1;
                case "ROLE_SUPADMIN" -> AdminConnect = 2;
            }
        }
        
        model.addAttribute("AdminConnect", AdminConnect);
        
        return pageCompet.getPage(dataService);
    }
    
    
    
    //Modifier une competition
    @PutMapping("/compet/{id}")
    public String editCompet(@ModelAttribute FormCompet putCompet, Model model, Principal principal) { 
        int result = competitionService.updateCompetition(putCompet);

        PageCompetition pageCompet = new PageCompetition(model, principal, messageSource);
        pageCompet.addReponse("compet", "edit", result);
        
        int AdminConnect = 0;
        if (!pageCompet.returnUser(principal).equals("")){
            switch (userService.findRoleByUsername(pageCompet.returnUser(principal))) {
                case "ROLE_ADMIN" -> AdminConnect = 1;
                case "ROLE_SUPADMIN" -> AdminConnect = 2;
            }
        }
        
        model.addAttribute("AdminConnect", AdminConnect);
        
        return pageCompet.getPage(dataService);
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
        
        PageActions pageAction = new PageActions(model, principal, messageSource);
        return pageAction.returnPage();
    }
    

    
    
    
    
// ----------------------  GESTION DU STOCK DE COMPETITION --------------------------    
    
    
    
    //Modifier une stock de competition
    @PutMapping("/stock/{id}")
    public String editStock(@ModelAttribute FormStock putStock, Model model, Principal principal) {
       int result = stockService.updateStock(putStock);
        
        PageCompetition pageCompet = new PageCompetition(model, principal, messageSource);
        pageCompet.addReponse("stock", "edit", result);
        
        int AdminConnect = 0;
        if (!pageCompet.returnUser(principal).equals("")){
            switch (userService.findRoleByUsername(pageCompet.returnUser(principal))) {
                case "ROLE_ADMIN" -> AdminConnect = 1;
                case "ROLE_SUPADMIN" -> AdminConnect = 2;
            }
        }
        
        model.addAttribute("AdminConnect", AdminConnect);
        
        return pageCompet.getPage(dataService);

    }
    
    
    @GetMapping("/stockCompet")
    public String editStockCompet(Model model, Principal principal) {
        
        StockCompetition stock = dataService.returnMainData().getIdStockCompet();
        FormStock editStock = new FormStock(stock.getId(), stock.getStock());
        
        model.addAttribute("editStock", editStock);
        model.addAttribute("numAction", ActionsTypes.EDIT_STOCK.toString());
        
        PageActions pageAction = new PageActions(model, principal, messageSource);
        return pageAction.returnPage();
    }
    
}
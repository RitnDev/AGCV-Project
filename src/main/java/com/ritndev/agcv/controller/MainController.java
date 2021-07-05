package com.ritndev.agcv.controller;

import com.ritndev.agcv.InterfaceService.IMainDataService;
import com.ritndev.agcv.InterfaceService.IUserService;
import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.form.FormUser;
import com.ritndev.agcv.model.AppUser;
import com.ritndev.agcv.pages.PageActions;
import org.springframework.ui.Model;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.ritndev.agcv.pages.PageHistoSaison;
import com.ritndev.agcv.pages.PageIndex;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


/**
 *
 * @author Ritn
 */

@Controller
public class MainController {
       
    @Autowired private IUserService userService;
    @Autowired private IMainDataService dataService;
    
    @Autowired private MessageSource messageSource;
        
     
//--------------------   Page Index   ----------------------------
    
    @GetMapping(value = { "/", "/index"})
    public String index(Model model, Principal principal){
        PageIndex pageIndex = new PageIndex(model, principal);
        model.addAttribute("saison", dataService.returnMainData().getIdSaison());
        return pageIndex.getPage();
    }
  
    
//--------------   Page Historique des saisons précédentes   ------------------
    
    @GetMapping(value = "/histoSaison")
    public String histoSaison(Model model, Principal principal){
        PageHistoSaison pageHistoSaison = new PageHistoSaison(model, principal);
        return pageHistoSaison.getPage(); 
    }
    
    
    
//--------------   MODIFICATION DU MOT DE PASSE UTILISATEUR   ------------------    
    
    
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
                
        PageActions pageAction = new PageActions(model, principal);
        return pageAction.returnPage();
    }
    
    
    //Lancer la modification d'un utilisateur
    @PutMapping("/mdp/{id}")
    public String editUsername(@ModelAttribute FormUser putUser, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT USER (MDP)");
        System.out.println("ID : " + putUser.getId());
        
        //Modification du mot de passe utilisateur :
        int result = userService.updateMdpUser(putUser);
                
        PageIndex pageIndex = new PageIndex(model, principal);
        pageIndex.addReponse(messageSource, "mdp", "edit", result);
        
        model.addAttribute("saison", dataService.returnMainData().getIdSaison());
        
        return pageIndex.getPage();
        
    }
    
    
}

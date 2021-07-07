package com.ritndev.agcv.controller;

import com.ritndev.agcv.InterfaceService.IMembreService;
import com.ritndev.agcv.InterfaceService.IUserService;
import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.form.FormMembre;
import com.ritndev.agcv.model.Membre;
import com.ritndev.agcv.pages.PageActions;
import com.ritndev.agcv.pages.PageMembre;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class MembreController {
    
    @Autowired private IUserService userService;
    @Autowired private IMembreService membreService;
    
    @Autowired private MessageSource messageSource;
    
    
    @GetMapping("/admin/membre")
    public String getMembre(Model model, Principal principal){    
        
        model.addAttribute("listMembres", membreService.listMembre());
         
        PageMembre pageMembre = new PageMembre(model, principal, messageSource);    
        boolean connect = userService.findRoleByUsername(pageMembre.returnUser(principal)).equals("ROLE_SUPADMIN");
        
        return pageMembre.getPage(connect);
    }
    
    
    // ----------------------------------- PARTIE MEMBRE ------------------------------------- //
    
    
    //Création d'un nouveau membre
    @PostMapping("/admin/newMembre")
    public String newMembre(@ModelAttribute FormMembre newMembre, Model model, Principal principal) {
        
        model.addAttribute("listMembres", membreService.listMembre());   
        
        PageMembre pageMembre = new PageMembre(model, principal, messageSource);
        
        int result = membreService.saveMembre(newMembre);
        pageMembre.addReponse("membre", "create", result);
        
        boolean connect = userService.findRoleByUsername(pageMembre.returnUser(principal)).equals("ROLE_SUPADMIN");
        
        return pageMembre.getPage(connect);
    }
    
    
    //Supprimer un membre
    @DeleteMapping("/admin/membre/{id}")
    public String supprMembre(@PathVariable(value = "id") Long id, Model model, Principal principal) {       

        model.addAttribute("listMembres", membreService.listMembre());
       
        PageMembre pageMembre = new PageMembre(model, principal, messageSource);
        
        int result = membreService.supprMembre(id);
        pageMembre.addReponse("membre", "remove", result);
        
        boolean connect = userService.findRoleByUsername(pageMembre.returnUser(principal)).equals("ROLE_SUPADMIN");

        return pageMembre.getPage(connect);
    }
    
    //Modifier un membre
    @PutMapping("/admin/membre/{id}")
    public String editMembre(@ModelAttribute FormMembre putMembre, Model model, Principal principal) {   
        
        model.addAttribute("listMembres", membreService.listMembre());
       
        PageMembre pageMembre = new PageMembre(model, principal, messageSource);
        
        int result = membreService.updateMembre(putMembre);
        pageMembre.addReponse("membre", "edit", result);
        
        boolean connect = userService.findRoleByUsername(pageMembre.returnUser(principal)).equals("ROLE_SUPADMIN");

        return pageMembre.getPage(connect);
    }
    
    
    
    //Lancer la modification d'un membre
    @PostMapping("/admin/membre/{id}")
    public String postMembre(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT MEMBRE");
        System.out.println("ID : " + id);
        
        //Recupération du membre à modifier :
        Membre editMembre = membreService.findByIdMembre(id);
        FormMembre formMembre = new FormMembre(id, editMembre.getPrenom(), editMembre.getNom());
        model.addAttribute("editMembre", formMembre);
        model.addAttribute("numAction", ActionsTypes.EDIT_MEMBRE.toString());
                
        PageActions pageAction = new PageActions(model, principal, messageSource);
        return pageAction.returnPage();
    }
    
   
    
    
    
    
}

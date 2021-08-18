package com.ritndev.agcv.controller;

import com.ritndev.agcv.InterfaceService.IMembreService;
import com.ritndev.agcv.InterfaceService.IUserService;
import com.ritndev.agcv.Validations.FormMembreValidation;
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
        PageMembre pageMembre = new PageMembre(model, principal, messageSource);  
        model.addAttribute("listMembres", membreService.listMembre());
        boolean connect = userService.findRoleByUsername(pageMembre.returnUser(principal)).equals("ROLE_SUPADMIN");
        
        return pageMembre.getPage(connect);
    }
    
    
    // ----------------------------------- PARTIE MEMBRE ------------------------------------- //
    
    
    //Création d'un nouveau membre
    @PostMapping("/admin/newMembre")
    public String newMembre(@ModelAttribute FormMembre newMembre, Model model, Principal principal) {
        //Construction de ma page Membre
        PageMembre pageMembre = new PageMembre(model, principal, messageSource);
        //Validation du FormMembre avant envoie au service
        FormMembreValidation validMembre = new FormMembreValidation(newMembre);
        //Si non valide, on envoie un message et on revient sur la page Membre
        if (!validMembre.getValid()){
            pageMembre.addReponse(validMembre.getReponse());
        }else{
            //Si c'est valide on envoie le FormMembre au service
            pageMembre.addReponse(membreService.saveMembre(newMembre));
        }
        
        boolean connect = userService.findRoleByUsername(pageMembre.returnUser(principal)).equals("ROLE_SUPADMIN");
        
        model.addAttribute("listMembres", membreService.listMembre()); 
        return pageMembre.getPage(connect);
    }
    
    
    //Supprimer un membre
    @DeleteMapping("/admin/membre/{id}")
    public String supprMembre(@PathVariable(value = "id") Long id, Model model, Principal principal) {       
        PageMembre pageMembre = new PageMembre(model, principal, messageSource);
        pageMembre.addReponse(membreService.supprMembre(id));
        
        boolean connect = userService.findRoleByUsername(pageMembre.returnUser(principal)).equals("ROLE_SUPADMIN");

        model.addAttribute("listMembres", membreService.listMembre());
        return pageMembre.getPage(connect);
    }
    
    //Modifier un membre
    @PutMapping("/admin/membre/{id}")
    public String editMembre(@ModelAttribute FormMembre putMembre, Model model, Principal principal) {   
        //Construction de ma page Membre
        PageMembre pageMembre = new PageMembre(model, principal, messageSource);
        //Validation du FormMembre avant envoie au service
        FormMembreValidation validMembre = new FormMembreValidation(putMembre);
        //Si non valide, on envoie un message et on revient sur la page Membre
        if (!validMembre.getValid()){
            pageMembre.addReponse(validMembre.getReponse());
        }else{
            //Si c'est valide on envoie le FormMembre au service
            pageMembre.addReponse(membreService.updateMembre(putMembre));
        }
        
        boolean connect = userService.findRoleByUsername(pageMembre.returnUser(principal)).equals("ROLE_SUPADMIN");

        model.addAttribute("listMembres", membreService.listMembre());
        return pageMembre.getPage(connect);
    }
    
    
    
    //Lancer la modification d'un membre
    @PostMapping("/admin/membre/{id}")
    public String postMembre(@PathVariable Long id, Model model, Principal principal) {
        //Recupération du membre à modifier :
        Membre editMembre = membreService.findByIdMembre(id);
        FormMembre formMembre = new FormMembre(id, editMembre.getPrenom(), editMembre.getNom(), editMembre.isActif());
        model.addAttribute("editMembre", formMembre);
        model.addAttribute("numAction", ActionsTypes.EDIT_MEMBRE.toString());
                
        PageActions pageAction = new PageActions(model, principal, messageSource);
        return pageAction.returnPage();
    }
    
}

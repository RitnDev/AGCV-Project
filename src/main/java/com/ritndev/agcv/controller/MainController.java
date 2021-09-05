package com.ritndev.agcv.controller;

import com.ritndev.agcv.InterfaceService.IMainDataService;
import com.ritndev.agcv.InterfaceService.IUserService;
import com.ritndev.agcv.Validations.FormUserValidation;
import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.form.FormUser;
import com.ritndev.agcv.model.AppUser;
import com.ritndev.agcv.pages.PageActions;
import org.springframework.ui.Model;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    
    @GetMapping({ "/", "/index"})
    public String index(Model model, Principal principal){
        PageIndex pageIndex = new PageIndex(model, principal, messageSource);
        return pageIndex.getPage(dataService);
    }
    
    
//--------------   MODIFICATION DU MOT DE PASSE UTILISATEUR   ------------------    
    
    
    //Lancer la modification d'un utilisateur
    @PostMapping("/mdp/{username}")
    public String getUsername(@PathVariable String username, Model model, Principal principal) {
        //Recupération de l'AppUser à modifier :
        AppUser editUser = userService.findByUsernameUser(username);
        FormUser formUser = new FormUser(editUser.getUserId(), editUser.getUserName());

        model.addAttribute("editUser", formUser);
        model.addAttribute("numAction", ActionsTypes.EDIT_MDP.toString());
                
        PageActions pageAction = new PageActions(model, principal, messageSource);
        return pageAction.returnPage();
    }
    
    
    //Lancer la modification d'un utilisateur
    @PutMapping("/mdp/{id}")
    public String editUsername(@ModelAttribute FormUser putUser, Model model, Principal principal) {
        //Construction de ma page Index
        PageIndex pageIndex = new PageIndex(model, principal, messageSource);
        //Validation du FormUser avant envoie au service
        FormUserValidation validUser = new FormUserValidation(putUser, false);
        //Si non valide, on envoie un message et on revient sur la page Index
        if (!validUser.getValid()){
            pageIndex.addReponse(validUser.getReponse());
        }else{
            //Si c'est valide on envoie le FormUser au service
            pageIndex.addReponse(userService.updateMdpUser(putUser));
        }
                
        return pageIndex.getPage(dataService);
    }
    
}

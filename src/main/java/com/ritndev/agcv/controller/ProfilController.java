package com.ritndev.agcv.controller;

import com.ritndev.agcv.form.FormProfil;
import com.ritndev.agcv.pages.PageIndex;
import com.ritndev.agcv.pages.PageProfil;
import com.ritndev.agcv.services.IProfilService;
import com.ritndev.agcv.services.IagcvService;
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


/**
 *
 * @author Ritn
 */
@Controller
public class ProfilController {
    
    @Autowired private IProfilService service;
    @Autowired private IagcvService agcvService;
    @Autowired private MessageSource messageSource;
    
    
    // ----------------------------------- PARTIE MEMBRE ------------------------------------- //
    
    //--------------------   Page Index   ----------------------------
    
    @GetMapping(value = "/profil")
    public String index(Model model, Principal principal){
        PageProfil pageProfil = new PageProfil();
        return pageProfil.getPage(model, principal, service, agcvService);
    }
    
    
    //Création d'un nouveau membre
    @PostMapping("/newProfil")
    public String newMembre(@ModelAttribute FormProfil newProfil, Model model, Principal principal) {
        int result = service.saveProfil(newProfil);
                          
        PageProfil pageProfil = new PageProfil();
        pageProfil.addReponse(messageSource, "profil", "create", result);
    
        return pageProfil.getPage(model, principal, service, agcvService);
    }
    
    
    //Supprimer un membre
    @DeleteMapping("/profil/{id}")
    public String supprMembre(@PathVariable(value = "id") Long id, Model model, Principal principal) {       
        int result = service.supprProfil(id);

        PageProfil pageProfil = new PageProfil();
        pageProfil.addReponse(messageSource, "profil", "remove", result);
        
        return pageProfil.getPage(model, principal, service, agcvService);
    }
    
    
    
}

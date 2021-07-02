package com.ritndev.agcv.controller;

import com.ritndev.agcv.InterfaceService.IMainDataService;
import com.ritndev.agcv.InterfaceService.IMembreService;
import com.ritndev.agcv.InterfaceService.IPrixTubeService;
import com.ritndev.agcv.InterfaceService.ISaisonService;
import com.ritndev.agcv.InterfaceService.ITypeTubeService;
import com.ritndev.agcv.InterfaceService.IUserService;
import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.classes.NomService;
import com.ritndev.agcv.form.FormMembre;
import com.ritndev.agcv.form.FormPrixTube;
import com.ritndev.agcv.form.FormSaison;
import com.ritndev.agcv.model.Membre;
import com.ritndev.agcv.model.Saison;
import com.ritndev.agcv.pages.PageActions;
import com.ritndev.agcv.pages.PageAdmin;
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
public class AdminController {
    
    @Autowired private IUserService userService;
    @Autowired private IMainDataService dataService;
    @Autowired private IMembreService membreService;
    @Autowired private ISaisonService saisonService;
    @Autowired private IPrixTubeService prixTubeService;
    @Autowired private ITypeTubeService typeTubeService;
    
    @Autowired private MessageSource messageSource;
    
    
    
    //--------------------   Page Admin   ---------------------------- 
    
    @GetMapping(value = { "/admin", "/newMembre", "/newSaison", "/newPrixTube"})
    public String admin(Model model, Principal principal){     
        PageAdmin pageAdmin = new PageAdmin(model, principal);
        pageAdmin.addService(NomService.USER.toString(), userService);
        pageAdmin.addService(NomService.DATA.toString(), dataService);
        pageAdmin.addService(NomService.MEMBRE.toString(), membreService);
        pageAdmin.addService(NomService.SAISON.toString(), saisonService);
        pageAdmin.addService(NomService.PRIXTUBE.toString(), prixTubeService);
        pageAdmin.addService(NomService.TYPETUBE.toString(), typeTubeService);
        
        return pageAdmin.getPage();
    } 
    
    
    
    
// ----------------------------------- PARTIE MEMBRE ------------------------------------- //
    
    
    //Création d'un nouveau membre
    @PostMapping("/newMembre")
    public String newMembre(@ModelAttribute FormMembre newMembre, Model model, Principal principal) {
        int result = membreService.saveMembre(newMembre);
                          
        PageAdmin pageAdmin = new PageAdmin(model, principal);
        pageAdmin.addReponse(messageSource, "membre", "create", result);
        pageAdmin.addService(NomService.USER.toString(), userService);
        pageAdmin.addService(NomService.DATA.toString(), dataService);
        pageAdmin.addService(NomService.MEMBRE.toString(), membreService);
        pageAdmin.addService(NomService.SAISON.toString(), saisonService);
        pageAdmin.addService(NomService.PRIXTUBE.toString(), prixTubeService);
        pageAdmin.addService(NomService.TYPETUBE.toString(), typeTubeService);
    
        return pageAdmin.getPage();
    }
    
    
    //Supprimer un membre
    @DeleteMapping("/membre/{id}")
    public String supprMembre(@PathVariable(value = "id") Long id, Model model, Principal principal) {       
        int result = membreService.supprMembre(id);

        PageAdmin pageAdmin = new PageAdmin(model, principal);
        pageAdmin.addReponse(messageSource, "membre", "remove", result);
        pageAdmin.addService(NomService.USER.toString(), userService);
        pageAdmin.addService(NomService.DATA.toString(), dataService);
        pageAdmin.addService(NomService.MEMBRE.toString(), membreService);
        pageAdmin.addService(NomService.SAISON.toString(), saisonService);
        pageAdmin.addService(NomService.PRIXTUBE.toString(), prixTubeService);
        pageAdmin.addService(NomService.TYPETUBE.toString(), typeTubeService);
        
        return pageAdmin.getPage();
    }
    
    //Modifier un membre
    @PutMapping("/membre/{id}")
    public String editMembre(@ModelAttribute FormMembre putMembre, Model model, Principal principal) {   
        int result = membreService.updateMembre(putMembre);
        
        PageAdmin pageAdmin = new PageAdmin(model, principal);
        pageAdmin.addReponse(messageSource, "membre", "edit", result);
        pageAdmin.addService(NomService.USER.toString(), userService);
        pageAdmin.addService(NomService.DATA.toString(), dataService);
        pageAdmin.addService(NomService.MEMBRE.toString(), membreService);
        pageAdmin.addService(NomService.SAISON.toString(), saisonService);
        pageAdmin.addService(NomService.PRIXTUBE.toString(), prixTubeService);
        pageAdmin.addService(NomService.TYPETUBE.toString(), typeTubeService);
        
        return pageAdmin.getPage();
    }
    
    
    
    //Lancer la modification d'un membre
    @PostMapping("/membre/{id}")
    public String getMembre(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT MEMBRE");
        System.out.println("ID : " + id);
        
        //Recupération du membre à modifier :
        Membre editMembre = membreService.findByIdMembre(id);
        FormMembre formMembre = new FormMembre(id, editMembre.getPrenom(), editMembre.getNom());
        model.addAttribute("editMembre", formMembre);
        model.addAttribute("numAction", ActionsTypes.EDIT_MEMBRE.toString());
                
        PageActions pageAction = new PageActions(model, principal);
        return pageAction.returnPage();
    }
    
    
    
    
    
    
    
// ----------------------------------- PARTIE SAISON ------------------------------------- //
    
    
    //Création d'une nouvelle saison
    @PostMapping("/newSaison")
    public String newSaison(@ModelAttribute FormSaison newSaison, Model model, Principal principal) {
        int result = saisonService.saveSaison(newSaison);

        PageAdmin pageAdmin = new PageAdmin(model, principal);
        pageAdmin.addReponse(messageSource, "saison", "create", result);
        pageAdmin.addService(NomService.USER.toString(), userService);
        pageAdmin.addService(NomService.DATA.toString(), dataService);
        pageAdmin.addService(NomService.MEMBRE.toString(), membreService);
        pageAdmin.addService(NomService.SAISON.toString(), saisonService);
        pageAdmin.addService(NomService.PRIXTUBE.toString(), prixTubeService);
        pageAdmin.addService(NomService.TYPETUBE.toString(), typeTubeService);

        return pageAdmin.getPage();
    }
    
    
    //Supprimer une saison
    @DeleteMapping("/saison/{id}")
    public String supprSaison(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        int result = saisonService.supprSaison(id);
       
        PageAdmin pageAdmin = new PageAdmin(model, principal);
        pageAdmin.addReponse(messageSource, "saison", "remove", result);
        pageAdmin.addService(NomService.USER.toString(), userService);
        pageAdmin.addService(NomService.DATA.toString(), dataService);
        pageAdmin.addService(NomService.MEMBRE.toString(), membreService);
        pageAdmin.addService(NomService.SAISON.toString(), saisonService);
        pageAdmin.addService(NomService.PRIXTUBE.toString(), prixTubeService);
        pageAdmin.addService(NomService.TYPETUBE.toString(), typeTubeService);
        
        return pageAdmin.getPage();
    }
    
    
    //Lancer la modification d'une saison
    @PostMapping("/saison/{id}")
    public String getSaison(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT SAISON");
        System.out.println(">> ID : " + id);
        
        //Recupération du membre à modifier :
        Saison editSaison = saisonService.findByIdSaison(id);
        FormSaison formSaison = new FormSaison(id, editSaison.getBudgetString(), editSaison.isActuelle());
        model.addAttribute("editSaison", formSaison);
        model.addAttribute("numAction", ActionsTypes.EDIT_SAISON.toString());
                
        PageActions pageAction = new PageActions(model, principal);
        return pageAction.returnPage();
    }
    
    
    //Modifier une saison
    @PutMapping("/saison/{id}")
    public String editSaison(@ModelAttribute FormSaison putSaison, Model model, Principal principal) {
        int result = saisonService.updateSaison(putSaison);
        
        PageAdmin pageAdmin = new PageAdmin(model, principal);
        pageAdmin.addReponse(messageSource, "saison", "edit", result);
        pageAdmin.addService(NomService.USER.toString(), userService);
        pageAdmin.addService(NomService.DATA.toString(), dataService);
        pageAdmin.addService(NomService.MEMBRE.toString(), membreService);
        pageAdmin.addService(NomService.SAISON.toString(), saisonService);
        pageAdmin.addService(NomService.PRIXTUBE.toString(), prixTubeService);
        pageAdmin.addService(NomService.TYPETUBE.toString(), typeTubeService);
        
        return pageAdmin.getPage();

    }
    
    
    
    // ----------------------------------- PARTIE PRIX-TUBES ------------------------------------- //
    
    
    //Création d'un nouveau prix-tube
    @PostMapping("/newPrixTube")
    public String newPrixTube(@ModelAttribute FormPrixTube newPrixTube, Model model, Principal principal) {
        PageAdmin pageAdmin = new PageAdmin(model, principal);
        
        int result = prixTubeService.savePrixTube(newPrixTube);
        System.out.println(">> result : " + result);
        pageAdmin.addReponse(messageSource, "prixtube", "create", result);
        pageAdmin.addService(NomService.USER.toString(), userService);
        pageAdmin.addService(NomService.DATA.toString(), dataService);
        pageAdmin.addService(NomService.MEMBRE.toString(), membreService);
        pageAdmin.addService(NomService.SAISON.toString(), saisonService);
        pageAdmin.addService(NomService.PRIXTUBE.toString(), prixTubeService);
        pageAdmin.addService(NomService.TYPETUBE.toString(), typeTubeService);
        
        return pageAdmin.getPage();
    }
    
    
    //Supprimer un prix-tube
    @DeleteMapping("/prixtube/{id}")
    public String supprPrixTube(@PathVariable(value = "id") Long id, Model model, Principal principal) {  
        int result = prixTubeService.supprPrixTube(id);
        
        PageAdmin pageAdmin = new PageAdmin(model, principal);
        pageAdmin.addReponse(messageSource, "prixtube", "remove", result);
        pageAdmin.addService(NomService.USER.toString(), userService);
        pageAdmin.addService(NomService.DATA.toString(), dataService);
        pageAdmin.addService(NomService.MEMBRE.toString(), membreService);
        pageAdmin.addService(NomService.SAISON.toString(), saisonService);
        pageAdmin.addService(NomService.PRIXTUBE.toString(), prixTubeService);
        pageAdmin.addService(NomService.TYPETUBE.toString(), typeTubeService);
        
        return pageAdmin.getPage();
    }
    
    
    //Lancer la modification d'un prix-tube
    @PostMapping("/prixtube/{id}")
    public String getPrixTube(@PathVariable Long id, Model model, Principal principal) {     
        System.out.println(">> POST - EDIT PRIX-TUBE");
        System.out.println(">> ID : " + id);
        
        //Recupération du membre à modifier :        
        model.addAttribute("editPrixTube", prixTubeService.findByIdPrixTube(id));
        model.addAttribute("numAction", ActionsTypes.EDIT_PRIXTUBE.toString());
                
        PageActions pageAction = new PageActions(model, principal);
        return pageAction.returnPage();
    }
    
    
    //Modifier un prix-tube
    @PutMapping("/prixtube/{id}")
    public String editPrixTube(@ModelAttribute FormPrixTube putPrixTube, Model model, Principal principal) {
        int result = prixTubeService.updatePrixTube(putPrixTube);
        
        PageAdmin pageAdmin = new PageAdmin(model, principal);
        pageAdmin.addReponse(messageSource, "prixtube", "edit", result);
        pageAdmin.addService(NomService.USER.toString(), userService);
        pageAdmin.addService(NomService.DATA.toString(), dataService);
        pageAdmin.addService(NomService.MEMBRE.toString(), membreService);
        pageAdmin.addService(NomService.SAISON.toString(), saisonService);
        pageAdmin.addService(NomService.PRIXTUBE.toString(), prixTubeService);
        pageAdmin.addService(NomService.TYPETUBE.toString(), typeTubeService);
        
        return pageAdmin.getPage();
    }
    
}

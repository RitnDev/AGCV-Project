package com.ritndev.agcv.controller;

import com.ritndev.agcv.InterfaceService.IPrixTubeService;
import com.ritndev.agcv.InterfaceService.ITypeTubeService;
import com.ritndev.agcv.InterfaceService.IUserService;
import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.form.FormPrixTube;
import com.ritndev.agcv.model.enumeration.NomTypeTube;
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
    @Autowired private IPrixTubeService prixTubeService;
    @Autowired private ITypeTubeService typeTubeService;
    
    @Autowired private MessageSource messageSource;
    
    
    
    //--------------------   Page Admin   ---------------------------- 
    
    @GetMapping(value = { "/admin", "/newPrixTube"})
    public String admin(Model model, Principal principal){     

        model.addAttribute("listPrixTubes", prixTubeService.listPrixTube());
        model.addAttribute("prixPlastiques", prixTubeService.ListPrixTubeName(NomTypeTube.PLASTIQUE.toString()));
        model.addAttribute("prixEntrainements", prixTubeService.ListPrixTubeName(NomTypeTube.ENTRAINEMENT.toString()));
        model.addAttribute("prixCompetitions", prixTubeService.ListPrixTubeName(NomTypeTube.COMPETITION.toString()));
        model.addAttribute("typeTubes", typeTubeService.listDataTypeTube());

        PageAdmin pageAdmin = new PageAdmin(model, principal);    
        boolean connect = userService.findRoleByUsername(pageAdmin.returnUser(principal)).equals("ROLE_SUPADMIN");
        
        return pageAdmin.getPage(connect);
    } 
    
    
    // ----------------------------------- PARTIE PRIX-TUBES ------------------------------------- //
    
    
    //Création d'un nouveau prix-tube
    @PostMapping("/newPrixTube")
    public String newPrixTube(@ModelAttribute FormPrixTube newPrixTube, Model model, Principal principal) {
        
        model.addAttribute("listPrixTubes", prixTubeService.listPrixTube());
        model.addAttribute("prixPlastiques", prixTubeService.ListPrixTubeName(NomTypeTube.PLASTIQUE.toString()));
        model.addAttribute("prixEntrainements", prixTubeService.ListPrixTubeName(NomTypeTube.ENTRAINEMENT.toString()));
        model.addAttribute("prixCompetitions", prixTubeService.ListPrixTubeName(NomTypeTube.COMPETITION.toString()));
        model.addAttribute("typeTubes", typeTubeService.listDataTypeTube());
        
        PageAdmin pageAdmin = new PageAdmin(model, principal);
        
        int result = prixTubeService.savePrixTube(newPrixTube);
        pageAdmin.addReponse(messageSource, "prixtube", "create", result);

        boolean connect = userService.findRoleByUsername(pageAdmin.returnUser(principal)).equals("ROLE_SUPADMIN");

        return pageAdmin.getPage(connect);
    }
    
    
    //Supprimer un prix-tube
    @DeleteMapping("/prixtube/{id}")
    public String supprPrixTube(@PathVariable(value = "id") Long id, Model model, Principal principal) {  
        
        model.addAttribute("listPrixTubes", prixTubeService.listPrixTube());
        model.addAttribute("prixPlastiques", prixTubeService.ListPrixTubeName(NomTypeTube.PLASTIQUE.toString()));
        model.addAttribute("prixEntrainements", prixTubeService.ListPrixTubeName(NomTypeTube.ENTRAINEMENT.toString()));
        model.addAttribute("prixCompetitions", prixTubeService.ListPrixTubeName(NomTypeTube.COMPETITION.toString()));
        model.addAttribute("typeTubes", typeTubeService.listDataTypeTube());
        
        PageAdmin pageAdmin = new PageAdmin(model, principal);
        
        int result = prixTubeService.supprPrixTube(id);
        pageAdmin.addReponse(messageSource, "prixtube", "remove", result);
        
        
        boolean connect = userService.findRoleByUsername(pageAdmin.returnUser(principal)).equals("ROLE_SUPADMIN");

        return pageAdmin.getPage(connect);
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
        
        model.addAttribute("listPrixTubes", prixTubeService.listPrixTube());
        model.addAttribute("prixPlastiques", prixTubeService.ListPrixTubeName(NomTypeTube.PLASTIQUE.toString()));
        model.addAttribute("prixEntrainements", prixTubeService.ListPrixTubeName(NomTypeTube.ENTRAINEMENT.toString()));
        model.addAttribute("prixCompetitions", prixTubeService.ListPrixTubeName(NomTypeTube.COMPETITION.toString()));
        model.addAttribute("typeTubes", typeTubeService.listDataTypeTube());
        
        PageAdmin pageAdmin = new PageAdmin(model, principal);
        
        int result = prixTubeService.updatePrixTube(putPrixTube);
        pageAdmin.addReponse(messageSource, "prixtube", "edit", result);
        
        boolean connect = userService.findRoleByUsername(pageAdmin.returnUser(principal)).equals("ROLE_SUPADMIN");

        return pageAdmin.getPage(connect);
    }
    
}

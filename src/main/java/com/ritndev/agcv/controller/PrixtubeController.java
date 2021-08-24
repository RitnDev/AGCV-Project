package com.ritndev.agcv.controller;

import com.ritndev.agcv.InterfaceService.IPrixTubeService;
import com.ritndev.agcv.InterfaceService.ITypeTubeService;
import com.ritndev.agcv.InterfaceService.IUserService;
import com.ritndev.agcv.Validations.FormPrixTubeValidation;
import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.form.FormPrixTube;
import com.ritndev.agcv.model.enumeration.NomTypeTube;
import com.ritndev.agcv.pages.PageActions;
import com.ritndev.agcv.pages.PagePrixtube;
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
public class PrixtubeController {
    
    @Autowired private IUserService userService;
    @Autowired private IPrixTubeService prixTubeService;
    @Autowired private ITypeTubeService typeTubeService;
    
    @Autowired private MessageSource messageSource;
    
    
    //Page des PrixTubes
    @GetMapping(value = "/admin/prixtube")
    public String getPrixtubes(Model model, Principal principal){     

        model.addAttribute("listPrixTubes", prixTubeService.listPrixTube());
        model.addAttribute("prixPlastiques", prixTubeService.ListPrixTubeName(NomTypeTube.PLASTIQUE.toString()));
        model.addAttribute("prixEntrainements", prixTubeService.ListPrixTubeName(NomTypeTube.ENTRAINEMENT.toString()));
        model.addAttribute("prixCompetitions", prixTubeService.ListPrixTubeName(NomTypeTube.COMPETITION.toString()));
        model.addAttribute("typeTubes", typeTubeService.listDataTypeTube());

        PagePrixtube pagePrixtube = new PagePrixtube(model, principal, messageSource);
        boolean connect = userService.findRoleByUsername(pagePrixtube.returnUser(principal)).equals("ROLE_SUPADMIN");
        
        return pagePrixtube.getPage(connect);
    }
    
    
    
        
    // ----------------------------------- PARTIE PRIX-TUBES ------------------------------------- //
    
    
    //Création d'un nouveau prix-tube
    @PostMapping("/admin/newPrixTube")
    public String newPrixTube(@ModelAttribute FormPrixTube newPrixTube, Model model, Principal principal) {
        //Construction de ma page Prixtube
        PagePrixtube pagePrixtube = new PagePrixtube(model, principal, messageSource); 
        //Validation du FormPrixTube avant envoie au service
        FormPrixTubeValidation validPrixtube = new FormPrixTubeValidation(newPrixTube);
        //Si non valide, on envoie un message et on revient sur la page Prixtube
        if (!validPrixtube.getValid()){
            pagePrixtube.addReponse(validPrixtube.getReponse());
        }else{
            //Si c'est valide on envoie le FormPrixTube au service
            pagePrixtube.addReponse(prixTubeService.savePrixTube(newPrixTube));
        }
        
        model.addAttribute("listPrixTubes", prixTubeService.listPrixTube());
        model.addAttribute("prixPlastiques", prixTubeService.ListPrixTubeName(NomTypeTube.PLASTIQUE.toString()));
        model.addAttribute("prixEntrainements", prixTubeService.ListPrixTubeName(NomTypeTube.ENTRAINEMENT.toString()));
        model.addAttribute("prixCompetitions", prixTubeService.ListPrixTubeName(NomTypeTube.COMPETITION.toString()));
        model.addAttribute("typeTubes", typeTubeService.listDataTypeTube());
           
        boolean connect = userService.findRoleByUsername(pagePrixtube.returnUser(principal)).equals("ROLE_SUPADMIN");
        
        return pagePrixtube.getPage(connect);
    }
    
    
    //Supprimer un prix-tube
    @DeleteMapping("/admin/prixtube/{id}")
    public String supprPrixTube(@PathVariable(value = "id") Long id, Model model, Principal principal) {  
        PagePrixtube pagePrixtube = new PagePrixtube(model, principal, messageSource); 
        pagePrixtube.addReponse(prixTubeService.supprPrixTube(id));
        
        model.addAttribute("listPrixTubes", prixTubeService.listPrixTube());
        model.addAttribute("prixPlastiques", prixTubeService.ListPrixTubeName(NomTypeTube.PLASTIQUE.toString()));
        model.addAttribute("prixEntrainements", prixTubeService.ListPrixTubeName(NomTypeTube.ENTRAINEMENT.toString()));
        model.addAttribute("prixCompetitions", prixTubeService.ListPrixTubeName(NomTypeTube.COMPETITION.toString()));
        model.addAttribute("typeTubes", typeTubeService.listDataTypeTube());
         
        boolean connect = userService.findRoleByUsername(pagePrixtube.returnUser(principal)).equals("ROLE_SUPADMIN");

        return pagePrixtube.getPage(connect);
    }
    
    
    //Lancer la modification d'un prix-tube
    @PostMapping("/admin/prixtube/{id}")
    public String postPrixTube(@PathVariable Long id, Model model, Principal principal) {     
        //Recupération du membre à modifier :        
        model.addAttribute("editPrixTube", prixTubeService.findByIdPrixTube(id));
        model.addAttribute("numAction", ActionsTypes.EDIT_PRIXTUBE.toString());
                
        PageActions pageAction = new PageActions(model, principal, messageSource);
        return pageAction.returnPage();
    }
    
    
    //Modifier un prix-tube
    @PutMapping("/admin/prixtube/{id}")
    public String editPrixTube(@ModelAttribute FormPrixTube putPrixTube, Model model, Principal principal) {
        //Construction de ma page Prixtube
        PagePrixtube pagePrixtube = new PagePrixtube(model, principal, messageSource); 
        //Validation du FormPrixTube avant envoie au service
        FormPrixTubeValidation validPrixtube = new FormPrixTubeValidation(putPrixTube);
        //Si non valide, on envoie un message et on revient sur la page Prixtube
        if (!validPrixtube.getValid()){
            pagePrixtube.addReponse(validPrixtube.getReponse());
        }else{
            //Si c'est valide on envoie le FormPrixTube au service
            pagePrixtube.addReponse(prixTubeService.updatePrixTube(putPrixTube));
        } 
        
        
        model.addAttribute("listPrixTubes", prixTubeService.listPrixTube());
        model.addAttribute("prixPlastiques", prixTubeService.ListPrixTubeName(NomTypeTube.PLASTIQUE.toString()));
        model.addAttribute("prixEntrainements", prixTubeService.ListPrixTubeName(NomTypeTube.ENTRAINEMENT.toString()));
        model.addAttribute("prixCompetitions", prixTubeService.ListPrixTubeName(NomTypeTube.COMPETITION.toString()));
        model.addAttribute("typeTubes", typeTubeService.listDataTypeTube());
        
        boolean connect = userService.findRoleByUsername(pagePrixtube.returnUser(principal)).equals("ROLE_SUPADMIN");

        return pagePrixtube.getPage(connect);
    }
        
}

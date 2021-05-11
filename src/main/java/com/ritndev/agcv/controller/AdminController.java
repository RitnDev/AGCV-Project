package com.ritndev.agcv.controller;

import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.classes.TypeReponse;
import com.ritndev.agcv.form.FormMembre;
import com.ritndev.agcv.form.FormSaison;
import com.ritndev.agcv.model.MainData;
import com.ritndev.agcv.model.Membre;
import com.ritndev.agcv.model.Saison;
import com.ritndev.agcv.pages.PageActions;
import com.ritndev.agcv.pages.PageAdmin;
import com.ritndev.agcv.services.IagcvService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    
    @Autowired
    private IagcvService service;
    
    
// ----------------------------------- PARTIE MEMBRE ------------------------------------- //
    
    
    //Création d'un nouveau membre
    @PostMapping("/newMembre")
    public String newMembre(@ModelAttribute FormMembre newMembre, Model model, Principal principal) {
        System.out.println(">> POST");
        System.out.println("Prenom : " + newMembre.getPrenom());
        System.out.println("Nom : " + newMembre.getNom());
        
        Membre addMembre = new Membre(newMembre.getPrenom(),newMembre.getNom());
        service.saveMembre(addMembre);
        
        String reponse1 = "Membre : '" + addMembre.toString() + "' - créer avec succès";
        String reponse2 = "Ceci est un test d'erreur !";
          
        PageAdmin pageAdmin = new PageAdmin();
        pageAdmin.addReponse(TypeReponse.ADD, reponse1);
        pageAdmin.addReponse(TypeReponse.ERROR, reponse2);
        
        return pageAdmin.getPage(model, principal, service);
    }
    
    
    //Supprimer un membre
    @DeleteMapping("/membre/{id}")
    public String supprMembre(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        
        System.out.println(">> DELETE - MEMBRE");
        System.out.println("ID : " + id);
        
        service.supprMembre(id);
        
        String reponse1 = "Membre supprimé avec succès";
        
        PageAdmin pageAdmin = new PageAdmin();
        pageAdmin.addReponse(TypeReponse.REMOVE, reponse1);
        
        return pageAdmin.getPage(model, principal, service);
    }
    
    //Modifier un membre
    @PutMapping("/membre/{id}")
    public String editMembre(@ModelAttribute FormMembre putMembre, Model model, Principal principal) {
        
        System.out.println(">> PUT - EDIT MEMBRE");
        System.out.println("ID : " + putMembre.getId());
        System.out.println("Prenom : " + putMembre.getPrenom());
        System.out.println("Nom : " + putMembre.getNom());
        
        Long id = putMembre.getId();
        Membre editMembre = new Membre(putMembre.getPrenom(), putMembre.getNom());
        
        service.updateByIdMembre(id, editMembre);
        
        String reponse1 = "Membre : '" + editMembre.toString() + "' - modifié avec succès";
        
        PageAdmin pageAdmin = new PageAdmin();
        pageAdmin.addReponse(TypeReponse.EDIT, reponse1);
        
        return pageAdmin.getPage(model, principal, service);

    }
    
    
    
    //Lancer la modification d'un membre
    @PostMapping("/membre/{id}")
    public String getMembre(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT MEMBRE");
        System.out.println("ID : " + id);
        
        //Recupération du membre à modifier :
        Membre editMembre = service.findByIdMembre(id);
        FormMembre formMembre = new FormMembre(id, editMembre.getPrenom(), editMembre.getNom());
        model.addAttribute("editMembre", formMembre);
        model.addAttribute("numAction", ActionsTypes.EDIT_MEMBRE.getValue());
                
        PageActions pageAction = new PageActions();
        return pageAction.returnPage();
    }
    
    
    
    
    
    
    
// ----------------------------------- PARTIE SAISON ------------------------------------- //
    
    
    //Création d'une nouvelle saison
    @PostMapping("/newSaison")
    public String newSaison(@ModelAttribute FormSaison newSaison, Model model, Principal principal) {
        System.out.println(">> POST");
        System.out.println("nom de la saison : " + newSaison.toString());
        System.out.println("Budget de la saison : " + newSaison.getBudget());
        
        String reponse = "-- Saison non créée --";
        TypeReponse tr = TypeReponse.ERROR;
        
        PageAdmin pageAdmin = new PageAdmin();
        
        if(newSaison != null 
                && !"".equals(newSaison.toString())
                && !"".equals(newSaison.getBudget())
                ){
            
            Saison maSaison = new Saison(newSaison.getAnnee_debut(), newSaison.getBudgetDouble());
            
            System.out.println("Ma Saison : " + maSaison.toString());
            System.out.println("Budget previ de ma Saison : " + maSaison.getBudget());
            long result = service.saveSaison(maSaison);
            
            if (result > 0){
                reponse = "-- Saison créée avec succès --";
                tr = TypeReponse.ADD;
                MainData md = service.returnMainData();
                if(md.getId()>0){
                    md.setIdSaison(result);
                    service.updateByIdMainData(md.getId(), md); 
                }else{
                    pageAdmin.addReponse(TypeReponse.ERROR, "DATA non mise à jour !");
                }
            }else{
                reponse = "Cette saison existe déjà !";
            }
        }
        
        
        pageAdmin.addReponse(tr, reponse);
        
        return pageAdmin.getPage(model, principal, service);
    }
    
    
    
    
    
}

package com.ritndev.agcv.controller;

import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.classes.TypeReponse;
import com.ritndev.agcv.form.FormMembre;
import com.ritndev.agcv.form.FormSaison;
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
        System.out.println("--------------------------------");
        System.out.println(">> POST");
        System.out.println(">> nom de la saison : " + newSaison.toString());
        System.out.println(">> Budget de la saison : " + newSaison.getBudget());
        System.out.println(">> Saison actuelle : " + newSaison.isActuelle());
        System.out.println("--------------------------------");
        
        String reponse = "-- Saison non créée --";
        TypeReponse tr = TypeReponse.ERROR;
        
        PageAdmin pageAdmin = new PageAdmin();
        
        if(newSaison != null 
                && !"".equals(newSaison.toString())
                && !"".equals(newSaison.getBudget())
                ){
            
            Saison maSaison = new Saison(newSaison.getAnnee_debut(), newSaison.getBudgetDouble(), newSaison.isActuelle());
            
            System.out.println(">> Ma Saison : " + maSaison.toString());
            System.out.println(">> Budget previ de ma Saison : " + maSaison.getBudget());
            System.out.println(">> Saison actuelle : " + maSaison.isActuelle());
            int result = service.saveSaison(maSaison);
            
            switch(result){
                case 1 -> {
                    reponse = "-- Saison créée avec succès --";
                    tr = TypeReponse.ADD;
                    pageAdmin.addReponse(TypeReponse.ERROR, "DATA non mise à jour !");
                }
                case 2 -> {
                    reponse = "-- Saison créée avec succès --";
                    tr = TypeReponse.ADD;
                }
                default -> reponse = "Cette saison existe déjà !";
            }
        }
        
        pageAdmin.addReponse(tr, reponse);
        
        return pageAdmin.getPage(model, principal, service);
    }
    
    
    //Supprimer une saison
    @DeleteMapping("/saison/{id}")
    public String supprSaison(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        
        System.out.println(">> DELETE - SAISON");
        System.out.println("ID : " + id);
        
        service.supprSaison(id);
        
        String reponse = "Saison supprimé avec succès";
        
        PageAdmin pageAdmin = new PageAdmin();
        pageAdmin.addReponse(TypeReponse.REMOVE, reponse);
        
        return pageAdmin.getPage(model, principal, service);
    }
    
    
    //Lancer la modification d'un membre
    @PostMapping("/saison/{id}")
    public String getSaison(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT SAISON");
        System.out.println(">> ID : " + id);
        
        //Recupération du membre à modifier :
        Saison editSaison = service.findByIdSaison(id);
        FormSaison formSaison = new FormSaison(id, editSaison.getBudgetString(), editSaison.isActuelle());
        model.addAttribute("editSaison", formSaison);
        model.addAttribute("numAction", ActionsTypes.EDIT_SAISON.getValue());
                
        PageActions pageAction = new PageActions();
        return pageAction.returnPage();
    }
    
    
    //Modifier un membre
    @PutMapping("/saison/{id}")
    public String editSaison(@ModelAttribute FormSaison putSaison, Model model, Principal principal) {
        System.out.println("--------------------------------");
        System.out.println(">> PUT - EDIT SAISON");
        System.out.println(">> Budget de la saison : " + putSaison.getBudget());
        System.out.println(">> Saison actuelle : " + putSaison.isActuelle());
        System.out.println("--------------------------------");
        
        String reponse = "La saison n'a pas pus être modifié !";
        TypeReponse tr = TypeReponse.ERROR;
        
        PageAdmin pageAdmin = new PageAdmin();
        
        if(putSaison != null 
                && !"".equals(putSaison.getBudget())
                ){
            
            Long id = putSaison.getId();
            Saison maSaison = new Saison(putSaison.getId(), putSaison.getBudgetDouble(), putSaison.isActuelle());
            
            int result = service.updateByIdSaison(id, maSaison);
            
            switch (result) {
                case 1 -> {
                    reponse = "Saison modifiée avec succès";
                    tr = TypeReponse.EDIT;
                    pageAdmin.addReponse(TypeReponse.ERROR, "DATA non mise à jour !");
                }
                case 2 -> {
                    reponse = "Saison modifiée avec succès";
                    tr = TypeReponse.EDIT;
                    pageAdmin.addReponse(TypeReponse.INFO, "DATA mise à jour, plus aucune saison n'est active !");
                }
                case 3 -> {
                    reponse = "Saison modifié avec succès";
                    tr = TypeReponse.EDIT;
                    pageAdmin.addReponse(TypeReponse.SUCCESS, "DATA mise à jour, cette saison est maintenant l'actuelle !");
                }
                case 4 -> {
                    reponse = "Saison modifié avec succès";
                    tr = TypeReponse.EDIT;
                }
                default -> tr = TypeReponse.ERROR;
            }
            
        }
                
        
        pageAdmin.addReponse(tr, reponse);
        
        return pageAdmin.getPage(model, principal, service);

    }
    
}

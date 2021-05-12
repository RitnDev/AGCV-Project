package com.ritndev.agcv.controller;

import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.classes.TypeReponse;
import com.ritndev.agcv.form.FormData;
import com.ritndev.agcv.model.MainData;
import com.ritndev.agcv.pages.PageActions;
import com.ritndev.agcv.pages.PageSuperAdmin;
import com.ritndev.agcv.services.IagcvService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Ritn
 */
@Controller
public class SuperAdminController {
    
    @Autowired
    private IagcvService service;
    
    
    
    //--------------------   Page Super Admin   ---------------------------- 
    @RequestMapping(value = {"/superAdmin"}, method = RequestMethod.GET)
    public String superAdmin(Model model, Principal principal){     
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        return pageSuperAdmin.getPage(model, principal, service);
    }
    
    
    
    @GetMapping("/newData")
    public String newData(Model model, Principal principal) {
        boolean create = service.newMainData();
        String reponse;
        TypeReponse tr;
        if (create){
            reponse = "- MAIN DATA créée avec succès -";
            tr = TypeReponse.ADD;
        }else{
            reponse = "- MAIN DATA déjà créée -";
            tr = TypeReponse.ERROR;
        }
        
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        pageSuperAdmin.addReponse(tr, reponse);
        return pageSuperAdmin.getPage(model, principal, service);
    }
    
    //Supprimer une main-data
    @DeleteMapping("/data/{id}")
    public String supprMembre(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        
        System.out.println(">> DELETE - DATA");
        System.out.println(">> ID : " + id);
        
        service.supprMainData(id);
        
        String reponse1 = "-- MAIN DATA supprimé avec succès --";
        
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        pageSuperAdmin.addReponse(TypeReponse.REMOVE, reponse1);
        
        return pageSuperAdmin.getPage(model, principal, service);
    }
    
    
    //Lancer la modification d'une main-data
    @PostMapping("/data/{id}")
    public String getData(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT DATA");
        System.out.println(">> ID : " + id);
        
        //Recupération du membre à modifier :
        MainData editData = service.findByIdMainData(id);
        FormData formData = new FormData(id, editData.getIdSaison(), editData.getIdStockCompet(), editData.isActif());
        model.addAttribute("editData", formData);
        model.addAttribute("numAction", ActionsTypes.EDIT_DATA.getValue());
                
        PageActions pageAction = new PageActions();
        return pageAction.returnPage();
    }
    
    
    
    //Modifier un membre
    @PutMapping("/data/{id}")
    public String editMembre(@ModelAttribute FormData putData, Model model, Principal principal) {
        
        System.out.println(">> PUT - EDIT DATA");
        System.out.println(">> ID : " + putData.getId());
        System.out.println(">> ID Saison : " + putData.getIdSaison());
        System.out.println(">> ID Stock Compet : " + putData.getIdStockCompet());
        System.out.println(">> Actif : " + putData.isActif());
        
        Long id = putData.getId();
        MainData editData = new MainData(putData.getIdSaison(), putData.getIdStockCompet(), putData.isActif());
        System.out.println(">> (debug) Actif : " + editData.isActif());
        
        boolean result = service.updateByIdMainData(id, editData);
        
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
                
        String reponse;
        if (result){
            reponse = "-- MAIN-DATA modifié avec succès --";
            pageSuperAdmin.addReponse(TypeReponse.EDIT, reponse);
        }else{
            reponse = "-- MAIN-DATA non modifié --";
            pageSuperAdmin.addReponse(TypeReponse.ERROR, reponse);
        }
                
        return pageSuperAdmin.getPage(model, principal, service);

    }
    
}

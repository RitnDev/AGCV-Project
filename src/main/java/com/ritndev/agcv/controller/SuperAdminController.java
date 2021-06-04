package com.ritndev.agcv.controller;

import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.classes.TypeReponse;
import com.ritndev.agcv.form.FormData;
import com.ritndev.agcv.form.FormStock;
import com.ritndev.agcv.form.FormUser;
import com.ritndev.agcv.model.AppRole;
import com.ritndev.agcv.model.AppUser;
import com.ritndev.agcv.model.MainData;
import com.ritndev.agcv.model.StockCompetition;
import com.ritndev.agcv.pages.PageActions;
import com.ritndev.agcv.pages.PageSuperAdmin;
import com.ritndev.agcv.services.IagcvService;
import com.ritndev.agcv.services.IUserService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SuperAdminController {
    
    @Autowired private IagcvService service;
    @Autowired private IUserService userService;
    
    
    //--------------------   Page Super Admin   ---------------------------- 
    @GetMapping("/superAdmin")
    public String superAdmin(Model model, Principal principal){     
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        return pageSuperAdmin.getPage(model, principal, service, userService);
    }
    
 
    
// ---------------------------- MAIN-DATA -------------------------------------  
    
    
    //Creation d'une nouvelle Main-Data
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
        return pageSuperAdmin.getPage(model, principal, service, userService);
    }
    
    //Supprimer une main-data
    @DeleteMapping("/data/{id}")
    public String supprData(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        
        System.out.println(">> DELETE - DATA");
        System.out.println(">> ID : " + id);
        
        service.supprMainData(id);
        
        String reponse1 = "-- MAIN DATA supprimé avec succès --";
        
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        pageSuperAdmin.addReponse(TypeReponse.REMOVE, reponse1);
        
        return pageSuperAdmin.getPage(model, principal, service, userService);
    }
    
    
    //Lancer la modification d'une main-data
    @PostMapping("/data/{id}")
    public String getData(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT DATA");
        System.out.println(">> ID : " + id);
        
        //Recupération de la DATA à modifier :
        MainData editData = service.findByIdMainData(id);
        FormData formData = new FormData(id, editData.getIdSaison(), editData.getIdStockCompet(), editData.isActif());
        model.addAttribute("editData", formData);
        model.addAttribute("numAction", ActionsTypes.EDIT_DATA.getValue());
                
        PageActions pageAction = new PageActions();
        return pageAction.returnPage();
    }
    
    
    
    //Modifier une Main-Data
    @PutMapping("/data/{id}")
    public String editData(@ModelAttribute FormData putData, Model model, Principal principal) {
        
        System.out.println(">> PUT - EDIT DATA");
        System.out.println(">> ID : " + putData.getId());
        System.out.println(">> ID Saison : " + putData.getIdSaison());
        System.out.println(">> ID Stock Compet : " + putData.getIdStockCompet());
        System.out.println(">> Actif : " + putData.isActif());
        
        boolean result = service.updateMainData(putData);
        
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
                
        String reponse;
        if (result){
            reponse = "-- MAIN-DATA modifié avec succès --";
            pageSuperAdmin.addReponse(TypeReponse.EDIT, reponse);
        }else{
            reponse = "-- MAIN-DATA non modifié --";
            pageSuperAdmin.addReponse(TypeReponse.ERROR, reponse);
        }
                
        return pageSuperAdmin.getPage(model, principal, service, userService);

    }
    
// ---------------------------- STOCK COMPET -------------------------------------  
    
    
    //Creation d'une nouvelle Main-Data
    @GetMapping("/newStock")
    public String newStock(Model model, Principal principal) {
        String reponse = "Stock Competition non créé !";
        TypeReponse tr = TypeReponse.ERROR;
        
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        
        int result = service.newStock();
        
        switch(result) {
            case 1 -> {
                reponse = "Stock Competition créé avec succès.";
                tr = TypeReponse.ADD;
                pageSuperAdmin.addReponse(TypeReponse.ERROR, "DATA non mise à jour !");
            }
            case 2 -> {
                reponse = "Stock Competition créé avec succès.";
                tr = TypeReponse.ADD;
            }
            default -> tr = TypeReponse.ERROR;
        }
        
        pageSuperAdmin.addReponse(tr, reponse);
        return pageSuperAdmin.getPage(model, principal, service, userService);
    }
    
    
    
    //Supprimer une main-data
    @DeleteMapping("/stock/{id}")
    public String supprStock(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        
        System.out.println(">> DELETE - STOCK");
        System.out.println(">> ID : " + id);
        
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        
        String reponse = "Erreur lors de la suppression";
        TypeReponse tr = TypeReponse.ERROR;
        
        int result = service.supprStock(id);
        
        switch(result) {
            case 1 -> {
                reponse = "Stock Competition supprimé avec succès.";
                tr = TypeReponse.ADD;
                pageSuperAdmin.addReponse(TypeReponse.ERROR, "MAIN DATA non existant et non mise à jour !");
            }
            case 2 -> {
                reponse = "Stock Competition supprimé avec succès.";
                tr = TypeReponse.ADD;
            }
            default -> tr = TypeReponse.ERROR;
        }
        
        
        pageSuperAdmin.addReponse(tr, reponse);
        
        return pageSuperAdmin.getPage(model, principal, service, userService);
    }
    
    
    //Lancer la modification d'une main-data
    @PostMapping("/stock/{id}")
    public String getStock(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT STOCK");
        System.out.println(">> ID : " + id);
        
        //Recupération de la DATA à modifier :
        StockCompetition editStock = service.findByIdStock(id);
        FormStock formStock = new FormStock(id, editStock.getStock());
         
        model.addAttribute("editStock", formStock);
        model.addAttribute("numAction", ActionsTypes.EDIT_STOCK.getValue());
                
        PageActions pageAction = new PageActions();
        return pageAction.returnPage();
    }
    
    
    //Modifier une Main-Data
    @PutMapping("/stock/{id}")
    public String editStock(@ModelAttribute FormStock putStock, Model model, Principal principal) {
        
        System.out.println(">> PUT - EDIT STOCK");
        System.out.println(">> ID : " + putStock.getId());
        System.out.println(">> Stock Compet : " + putStock.getStock());
        
        int result = service.updateStock(putStock);
        
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
                
        String reponse;
        if (result==1){
            reponse = "-- Stock modifié avec succès --";
            pageSuperAdmin.addReponse(TypeReponse.EDIT, reponse);
        }else{
            reponse = "-- Stock non modifié --";
            pageSuperAdmin.addReponse(TypeReponse.ERROR, reponse);
        }
                
        return pageSuperAdmin.getPage(model, principal, service, userService);

    }
    
    
    
// ---------------------------- UTILISATEURS ------------------------------------- 
    
    
    //Creation d'un nouvel Utilisateur
    @PostMapping("/newUser")
    public String newData(@ModelAttribute FormUser newUser, Model model, Principal principal) {
        
        System.out.println(">> POST");
        System.out.println(">> Identifiant : " + newUser.getIdentifiant());
        System.out.println(">> Mot de passe : " + newUser.getMdp());
        System.out.println(">> Role ID : " + newUser.getRoleId());
        
        int result = userService.saveUser(newUser);

        TypeReponse tr = TypeReponse.ERROR;
        String reponse = "Utilisateur non enregistré !";
                
        switch(result) {
            case 1 -> {
                tr = TypeReponse.ADD;
                reponse = "Utilisateur : '" + newUser.getIdentifiant() + "' - créé avec succès";
            }
            default -> tr = TypeReponse.ERROR;
        }
          
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        pageSuperAdmin.addReponse(tr, reponse);
        return pageSuperAdmin.getPage(model, principal, service, userService);
    }
    
    
    //Supprimer un utilisateur
    @DeleteMapping("/user/{id}")
    public String supprUser(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        
        System.out.println(">> DELETE - USER");
        System.out.println(">> ID : " + id);
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        String userNameLog = pageSuperAdmin.returnUser(principal);
        
        String reponse = "-- Utilisateur non supprimé --";
        TypeReponse tr = TypeReponse.ERROR;
        
        // L'utilisateur ne peut pas se supprimer soit même.
        if (!userNameLog.equals(userService.findByIdUser(id).getUserName())) {            
            int result = userService.supprUser(id);
            switch(result) {
                case 1 -> {
                    reponse = "-- Utilisateur supprimé avec succès --";
                    tr = TypeReponse.REMOVE;
                }
                default -> tr = TypeReponse.ERROR;
            }
        }
        
        pageSuperAdmin.addReponse(tr, reponse);
        
        return pageSuperAdmin.getPage(model, principal, service, userService);
    }
    
    
    
    //Lancer la modification d'une main-data
    @PostMapping("/user/{id}")
    public String getUser(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT USER");
        System.out.println(">> ID : " + id);
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        String userNameLog = pageSuperAdmin.returnUser(principal);
        
        String reponse = "-- Utilisateur non modifié --";
        TypeReponse tr = TypeReponse.ERROR;
        
        //Recupération du user à modifier :
        AppUser editUser = userService.findByIdUser(id);
        
        // L'utilisateur ne peut pas se modifier soit même.
        if (!userNameLog.equals(editUser.getUserName())) {
            FormUser formUser = new FormUser(id, editUser.getUserName(), userService.findRoleIdByUserId(id), editUser.isEnabled());

            List<AppRole> roleList = userService.listRole();
            model.addAttribute("roleList", roleList);
            model.addAttribute("editUser", formUser);
            model.addAttribute("numAction", ActionsTypes.EDIT_USER.getValue());

            PageActions pageAction = new PageActions();

            return pageAction.returnPage();
        }
        
        pageSuperAdmin.addReponse(tr, reponse);
        
        return pageSuperAdmin.getPage(model, principal, service, userService);
    }
    
    
        //Modifier une Main-Data
    @PutMapping("/user/{id}")
    public String editUser(@ModelAttribute FormUser putUser, Model model, Principal principal) {
        
        System.out.println(">> PUT - EDIT USER");
        System.out.println(">> ID : " + putUser.getId());
        
        int result = userService.updateUser(putUser);
                
        String reponse = "-- Utilisateur non modifié --";
        TypeReponse tr = TypeReponse.ERROR;
        
        switch (result) {
            case 1 -> {
                reponse = "-- Utilisateur modifié avec succès --";
                tr = TypeReponse.EDIT;
            }
            case 2 -> {
                reponse = "-- Utilisateur et rôle modifié avec succès --";
                tr = TypeReponse.EDIT;
            }
            default -> tr = TypeReponse.ERROR;
        }
                
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        pageSuperAdmin.addReponse(tr, reponse);
        
        return pageSuperAdmin.getPage(model, principal, service, userService);
    }
    
    
}

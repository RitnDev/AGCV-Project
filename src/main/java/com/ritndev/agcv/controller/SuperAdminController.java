package com.ritndev.agcv.controller;

import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.classes.TypeReponse;
import com.ritndev.agcv.form.FormData;
import com.ritndev.agcv.form.FormStock;
import com.ritndev.agcv.form.FormTypeTube;
import com.ritndev.agcv.form.FormUser;
import com.ritndev.agcv.model.AppRole;
import com.ritndev.agcv.model.AppUser;
import com.ritndev.agcv.model.MainData;
import com.ritndev.agcv.model.StockCompetition;
import com.ritndev.agcv.model.TypeTube;
import com.ritndev.agcv.pages.PageActions;
import com.ritndev.agcv.pages.PageSuperAdmin;
import com.ritndev.agcv.services.IagcvService;
import com.ritndev.agcv.services.IUserService;
import java.security.Principal;
import java.util.List;
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
public class SuperAdminController {
    
    @Autowired private IagcvService service;
    @Autowired private IUserService userService;
    @Autowired private MessageSource messageSource;
    
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
        int result = service.newMainData();
        
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        pageSuperAdmin.addReponse(messageSource, "data", "create", result);
        return pageSuperAdmin.getPage(model, principal, service, userService);
    }
    
    //Supprimer une main-data
    @DeleteMapping("/data/{id}")
    public String supprData(@PathVariable(value = "id") Long id, Model model, Principal principal) {      
        int result = service.supprMainData(id);
       
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        pageSuperAdmin.addReponse(messageSource, "data", "remove", result);
        
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
        model.addAttribute("numAction", ActionsTypes.EDIT_DATA.toString());
                
        PageActions pageAction = new PageActions();
        return pageAction.returnPage();
    }
    
    
    
    //Modifier une Main-Data
    @PutMapping("/data/{id}")
    public String editData(@ModelAttribute FormData putData, Model model, Principal principal) {
        int result = service.updateMainData(putData);
        
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        pageSuperAdmin.addReponse(messageSource, "data", "edit", result);
                
        return pageSuperAdmin.getPage(model, principal, service, userService);

    }
    
// ---------------------------- STOCK COMPET -------------------------------------  
    
    
    //Creation d'un nouveau stock de competition
    @GetMapping("/newStock")
    public String newStock(Model model, Principal principal) {
        int result = service.newStock();
        
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        pageSuperAdmin.addReponse(messageSource, "stock", "create", result);
        
        return pageSuperAdmin.getPage(model, principal, service, userService);
    }
    
    
    
    //Supprimer une stock de competition
    @DeleteMapping("/stock/{id}")
    public String supprStock(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        int result = service.supprStock(id);
        
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        pageSuperAdmin.addReponse(messageSource, "stock", "remove", result);
        
        return pageSuperAdmin.getPage(model, principal, service, userService);
    }
    
    
    //Lancer la modification d'un stock de competition
    @PostMapping("/stock/{id}")
    public String getStock(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT STOCK");
        System.out.println(">> ID : " + id);
        
        //Recupération du stock à modifier :
        StockCompetition editStock = service.findByIdStock(id);
        FormStock formStock = new FormStock(id, editStock.getStock());
         
        model.addAttribute("editStock", formStock);
        model.addAttribute("numAction", ActionsTypes.EDIT_STOCK.toString());
                
        PageActions pageAction = new PageActions();
        return pageAction.returnPage();
    }
    
    
    //Modifier une stock de competition
    @PutMapping("/stock/{id}")
    public String editStock(@ModelAttribute FormStock putStock, Model model, Principal principal) {
       int result = service.updateStock(putStock);
        
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        pageSuperAdmin.addReponse(messageSource, "stock", "edit", result);
                
        return pageSuperAdmin.getPage(model, principal, service, userService);

    }
    
    
    
// ---------------------------- TYPE TUBE -------------------------------------  
    
    
    //Creation d'une nouvelle Type-Tube
    @PostMapping("/newTypeTube")
    public String newTypeTube(@ModelAttribute FormTypeTube newTypeTube, Model model, Principal principal) {
        int result = service.saveTypeTube(newTypeTube);
        
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        pageSuperAdmin.addReponse(messageSource, "typetube", "create", result);
        
        return pageSuperAdmin.getPage(model, principal, service, userService);
    }
    
    
    
    //Supprimer une Type-Tube
    @DeleteMapping("/typetube/{id}")
    public String supprTypeTube(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        int result = service.supprTypeTube(id);
        
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        pageSuperAdmin.addReponse(messageSource, "typetube", "remove", result);
        
        return pageSuperAdmin.getPage(model, principal, service, userService);
    }
    
    
    //Lancer la modification d'une Type-Tube
    @PostMapping("/typetube/{id}")
    public String getTypeTube(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT STOCK");
        System.out.println(">> ID : " + id);
        
        //Recupération de la DATA à modifier :
        TypeTube editTypeTube = service.findByIdTypeTube(id);
        FormTypeTube formTypeTube = new FormTypeTube(id, editTypeTube.getNom(), editTypeTube.isActif(), editTypeTube.isCommande());
         
        model.addAttribute("editTypeTube", formTypeTube);
        model.addAttribute("numAction", ActionsTypes.EDIT_TYPETUBE.toString());
                
        PageActions pageAction = new PageActions();
        return pageAction.returnPage();
    }
    
    
    //Modifier une Type-Tube
    @PutMapping("/typetube/{id}")
    public String editTypeTube(@ModelAttribute FormTypeTube putTypeTube, Model model, Principal principal) {
        int result = service.updateTypeTube(putTypeTube);
        
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();     
        pageSuperAdmin.addReponse(messageSource, "typetube", "remove", result);
                
        return pageSuperAdmin.getPage(model, principal, service, userService);
    }
    
    
// ---------------------------- UTILISATEURS ------------------------------------- 
    
    
    //Creation d'un nouvel Utilisateur
    @PostMapping("/newUser")
    public String newUser(@ModelAttribute FormUser newUser, Model model, Principal principal) {
        
        System.out.println(">> --------------- POST -----------------");
        System.out.println(">> Identifiant : " + newUser.getIdentifiant());
        System.out.println(">> Mot de passe : " + newUser.getMdp());
        System.out.println(">> Role ID : " + newUser.getRoleId());
        System.out.println(">> --------------------------------------");
        
        int result = userService.saveUser(newUser);

        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();     
        pageSuperAdmin.addReponse(messageSource, "user", "create", result);
                
        return pageSuperAdmin.getPage(model, principal, service, userService);
    }
    
    
    //Supprimer un utilisateur
    @DeleteMapping("/user/{id}")
    public String supprUser(@PathVariable(value = "id") Long id, Model model, Principal principal) {

        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        String userNameLog = pageSuperAdmin.returnUser(principal);
        
        int result = 3;
        
        // L'utilisateur ne peut pas se supprimer soit même.
        if (!userNameLog.equals(userService.findByIdUser(id).getUserName())) {            
            result = userService.supprUser(id);
        }
        
        pageSuperAdmin.addReponse(messageSource, "user", "remove", result);
        
        return pageSuperAdmin.getPage(model, principal, service, userService);
    }
    
    
    
    //Lancer la modification d'une main-data
    @PostMapping("/user/{id}")
    public String getUser(@PathVariable Long id, Model model, Principal principal) {
        
        System.out.println(">> POST - EDIT USER");
        System.out.println(">> ID : " + id);
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        String userNameLog = pageSuperAdmin.returnUser(principal);
        
        //Recupération du user à modifier :
        AppUser editUser = userService.findByIdUser(id);
        
        // L'utilisateur ne peut pas se modifier soit même.
        if (!userNameLog.equals(editUser.getUserName())) {
            if (!userNameLog.equals("ritn")) {
                FormUser formUser = new FormUser(id, editUser.getUserName(), userService.findRoleIdByUserId(id), editUser.isEnabled());

            List<AppRole> roleList = userService.listRole();
            model.addAttribute("roleList", roleList);
            model.addAttribute("editUser", formUser);
            model.addAttribute("numAction", ActionsTypes.EDIT_USER.toString());

            PageActions pageAction = new PageActions();

            return pageAction.returnPage();
            }else{
                pageSuperAdmin.addReponse(messageSource, "ritn", "edit", 3);
            }
        }else{
            pageSuperAdmin.addReponse(messageSource, "user", "edit", 3);
        }
        
        
        
        return pageSuperAdmin.getPage(model, principal, service, userService);
    }
    
    
        //Modifier une Main-Data
    @PutMapping("/user/{id}")
    public String editUser(@ModelAttribute FormUser putUser, Model model, Principal principal) {
        int result = userService.updateUser(putUser);
                
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin();
        pageSuperAdmin.addReponse(messageSource, "user", "edit", result);
        
        return pageSuperAdmin.getPage(model, principal, service, userService);
    }
    
    
}

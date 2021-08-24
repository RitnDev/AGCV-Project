package com.ritndev.agcv.controller;

import com.ritndev.agcv.InterfaceService.IMainDataService;
import com.ritndev.agcv.InterfaceService.ISaisonService;
import com.ritndev.agcv.InterfaceService.ITypeTubeService;
import com.ritndev.agcv.InterfaceService.IUserService;
import com.ritndev.agcv.Validations.FormUserValidation;
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

import com.ritndev.agcv.classes.ActionsTypes;
import com.ritndev.agcv.classes.Reponse;

import com.ritndev.agcv.form.FormData;
import com.ritndev.agcv.form.FormTypeTube;
import com.ritndev.agcv.form.FormUser;

import com.ritndev.agcv.model.AppRole;
import com.ritndev.agcv.model.AppUser;
import com.ritndev.agcv.model.MainData;
import com.ritndev.agcv.model.TypeTube;

import com.ritndev.agcv.pages.PageActions;
import com.ritndev.agcv.pages.PageSuperAdmin;
import com.ritndev.agcv.pages.PageUsers;
import java.util.HashMap;
import java.util.Map;




/**
 *
 * @author Ritn
 */
@Controller
public class SuperAdminController {
    
    @Autowired private IUserService userService;
    @Autowired private IMainDataService dataService;
    @Autowired private ITypeTubeService typeTubeService;
    @Autowired private ISaisonService saisonService;
    
    @Autowired private MessageSource messageSource;
    
    //--------------------   Page Super Admin   ---------------------------- 
    @GetMapping("/superAdmin")
    public String superAdmin(Model model, Principal principal){     
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin(model, principal, messageSource);
        
        model.addAttribute("mainData", dataService.listMainData());
        model.addAttribute("typeTubeList", typeTubeService.listDataTypeTube());
        
        return pageSuperAdmin.getPage();
    }
    
 
    
// ---------------------------- MAIN-DATA -------------------------------------  
    
    
    //Creation d'une nouvelle Main-Data
    @GetMapping("/newData")
    public String newData(Model model, Principal principal) {
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin(model, principal, messageSource);
        pageSuperAdmin.addReponse(dataService.newMainData());
        
        model.addAttribute("mainData", dataService.listMainData());
        model.addAttribute("typeTubeList", typeTubeService.listDataTypeTube());
        
        return pageSuperAdmin.getPage();
    }
    
    //Supprimer une main-data
    @DeleteMapping("/data/{id}")
    public String supprData(@PathVariable(value = "id") Long id, Model model, Principal principal) {      
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin(model, principal, messageSource);
        pageSuperAdmin.addReponse(dataService.supprMainData(id));
        
        model.addAttribute("mainData", dataService.listMainData());
        model.addAttribute("typeTubeList", typeTubeService.listDataTypeTube());
        
        return pageSuperAdmin.getPage();
    }
    
    
    //Lancer la modification d'une main-data
    @PostMapping("/data/{id}")
    public String getData(@PathVariable Long id, Model model, Principal principal) {
       
        //Recupération de la DATA à modifier :
        MainData editData = dataService.findByIdMainData(id);
        FormData formData = new FormData(id, editData.getIdSaison().getId(), editData.isActif());
        model.addAttribute("editData", formData);
        model.addAttribute("numAction", ActionsTypes.EDIT_DATA.toString());
        model.addAttribute("listSaison", saisonService.listSaison());
                
        PageActions pageAction = new PageActions(model, principal, messageSource);
        return pageAction.returnPage();
    }
    
    
    
    //Modifier une Main-Data
    @PutMapping("/data/{id}")
    public String editData(@ModelAttribute FormData putData, Model model, Principal principal) {
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin(model, principal, messageSource);
        pageSuperAdmin.addReponse(dataService.updateMainData(putData));
        
        model.addAttribute("mainData", dataService.listMainData());
        model.addAttribute("typeTubeList", typeTubeService.listDataTypeTube());
        
        return pageSuperAdmin.getPage();
    }
    
    
    
// ---------------------------- TYPE TUBE -------------------------------------  
    
    
    //Creation d'une nouvelle Type-Tube
    @PostMapping("/newTypeTube")
    public String newTypeTube(@ModelAttribute FormTypeTube newTypeTube, Model model, Principal principal) {
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin(model, principal, messageSource);
        pageSuperAdmin.addReponse(typeTubeService.saveTypeTube(newTypeTube));
        
        model.addAttribute("mainData", dataService.listMainData());
        model.addAttribute("typeTubeList", typeTubeService.listDataTypeTube());
        
        return pageSuperAdmin.getPage();
    }
    
    
    
    //Supprimer une Type-Tube
    @DeleteMapping("/typetube/{id}")
    public String supprTypeTube(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin(model, principal, messageSource);
        pageSuperAdmin.addReponse(typeTubeService.supprTypeTube(id));
        
        model.addAttribute("mainData", dataService.listMainData());
        model.addAttribute("typeTubeList", typeTubeService.listDataTypeTube());
        
        return pageSuperAdmin.getPage();
    }
    
    
    //Lancer la modification d'une Type-Tube
    @PostMapping("/typetube/{id}")
    public String getTypeTube(@PathVariable Long id, Model model, Principal principal) {
        
        //Recupération de la DATA à modifier :
        TypeTube editTypeTube = typeTubeService.findByIdTypeTube(id);
        FormTypeTube formTypeTube = new FormTypeTube(id, editTypeTube.getNom(), editTypeTube.isCommande());
         
        model.addAttribute("editTypeTube", formTypeTube);
        model.addAttribute("numAction", ActionsTypes.EDIT_TYPETUBE.toString());
                
        PageActions pageAction = new PageActions(model, principal, messageSource);
        return pageAction.returnPage();
    }
    
    
    //Modifier une Type-Tube
    @PutMapping("/typetube/{id}")
    public String editTypeTube(@ModelAttribute FormTypeTube putTypeTube, Model model, Principal principal) {
        PageSuperAdmin pageSuperAdmin = new PageSuperAdmin(model, principal, messageSource);     
        pageSuperAdmin.addReponse(typeTubeService.updateTypeTube(putTypeTube));
        
        model.addAttribute("mainData", dataService.listMainData());
        model.addAttribute("typeTubeList", typeTubeService.listDataTypeTube());
        
        return pageSuperAdmin.getPage();
    }
 
    
    
// ---------------------------- UTILISATEURS ------------------------------------- 
    
    //--------------------   Page Super Admin   ---------------------------- 
    @GetMapping("/superAdmin/users")
    public String getUsers(Model model, Principal principal){     
        PageUsers pageUsers = new PageUsers(model, principal, messageSource);
        
        Map<Long,String> userRoleList = new HashMap<>();
        for (AppUser user : userService.listUser()){
           userRoleList.put(user.getUserId(), pageUsers.formatRole(userService.findRoleByUsername(user.getUserName())));
        }

        model.addAttribute("userList", userService.listUser());
        model.addAttribute("userRoleList", userRoleList);
        model.addAttribute("roleList", userService.listRole());
        
        return pageUsers.getPage();
    }
    
    
    //Creation d'un nouvel Utilisateur
    @PostMapping("/superAdmin/newUser")
    public String newUser(@ModelAttribute FormUser newUser, Model model, Principal principal) {
        //Construction de ma page Index
        PageUsers pageUsers = new PageUsers(model, principal, messageSource); 
        //Validation du FormUser avant envoie au service
        FormUserValidation validUser = new FormUserValidation(newUser, true);
        //Si non valide, on envoie un message et on revient sur la page Index
        if (!validUser.getValid()){
            pageUsers.addReponse(validUser.getReponse());
        }else{
            //Si c'est valide on envoie le FormUser au service
            pageUsers.addReponse(userService.saveUser(newUser));
        }
                
        Map<Long,String> userRoleList = new HashMap<>();
        for (AppUser user : userService.listUser()){
           userRoleList.put(user.getUserId(), pageUsers.formatRole(userService.findRoleByUsername(user.getUserName())));
        }

        model.addAttribute("userList", userService.listUser());
        model.addAttribute("userRoleList", userRoleList);
        model.addAttribute("roleList", userService.listRole());
        
        return pageUsers.getPage();
    }
    
    
    //Supprimer un utilisateur
    @DeleteMapping("/superAdmin/user/{id}")
    public String supprUser(@PathVariable(value = "id") Long id, Model model, Principal principal) {

        PageUsers pageUsers = new PageUsers(model, principal, messageSource);
        String userNameLog = pageUsers.returnUser(principal);
        
        // L'utilisateur ne peut pas se supprimer soit même.
        if (!userNameLog.equals(userService.findByIdUser(id).getUserName())) {            
            pageUsers.addReponse(userService.supprUser(id));
        }else{
            pageUsers.addReponse(new Reponse("user", "remove", 3));
        }
        
        
        
        Map<Long,String> userRoleList = new HashMap<>();
        for (AppUser user : userService.listUser()){
           userRoleList.put(user.getUserId(), pageUsers.formatRole(userService.findRoleByUsername(user.getUserName())));
        }

        model.addAttribute("userList", userService.listUser());
        model.addAttribute("userRoleList", userRoleList);
        model.addAttribute("roleList", userService.listRole());
        
        return pageUsers.getPage();
    }
    
    
    
    //Lancer la modification d'une main-data
    @PostMapping("/superAdmin/user/{id}")
    public String getUser(@PathVariable Long id, Model model, Principal principal) {
        
        PageUsers pageUsers = new PageUsers(model, principal, messageSource);
        String userNameLog = pageUsers.returnUser(principal);
        
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

            PageActions pageAction = new PageActions(model, principal, messageSource);

            return pageAction.returnPage();
            }else{
                pageUsers.addReponse(new Reponse("ritn", "edit", 3));
            }
        }else{
            pageUsers.addReponse(new Reponse("user", "edit", 3));
        }
        
        
        Map<Long,String> userRoleList = new HashMap<>();
        for (AppUser user : userService.listUser()){
           userRoleList.put(user.getUserId(), pageUsers.formatRole(userService.findRoleByUsername(user.getUserName())));
        }

        model.addAttribute("userList", userService.listUser());
        model.addAttribute("userRoleList", userRoleList);
        model.addAttribute("roleList", userService.listRole());
        
        return pageUsers.getPage();
    }
    
    
        //Modifier une Main-Data
    @PutMapping("/superAdmin/user/{id}")
    public String editUser(@ModelAttribute FormUser putUser, Model model, Principal principal) {        
        //Construction de ma page Index
        PageUsers pageUsers = new PageUsers(model, principal, messageSource); 
        //Validation du FormUser avant envoie au service
        FormUserValidation validUser = new FormUserValidation(putUser, true);
        //Si non valide, on envoie un message et on revient sur la page Index
        if (!validUser.getValid()){
            pageUsers.addReponse(validUser.getReponse());
        }else{
            //Si c'est valide on envoie le FormUser au service
            pageUsers.addReponse(userService.updateUser(putUser));
        }
                
        Map<Long,String> userRoleList = new HashMap<>();
        for (AppUser user : userService.listUser()){
           userRoleList.put(user.getUserId(), pageUsers.formatRole(userService.findRoleByUsername(user.getUserName())));
        }

        model.addAttribute("userList", userService.listUser());
        model.addAttribute("userRoleList", userRoleList);
        model.addAttribute("roleList", userService.listRole());
        
        return pageUsers.getPage();
    }
    
    
}

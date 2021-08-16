package com.ritndev.agcv.controller;

import com.ritndev.agcv.InterfaceService.IMainDataService;
import com.ritndev.agcv.InterfaceService.ITypeVolantService;
import com.ritndev.agcv.InterfaceService.IUserService;
import com.ritndev.agcv.form.FormData;
import com.ritndev.agcv.form.FormTypeVolant;
import com.ritndev.agcv.pages.PageAdmin;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Ritn
 */
@Controller
public class AdminController {
    
    @Autowired private ITypeVolantService typeVolantService;
    @Autowired private IUserService userService;
    @Autowired private IMainDataService dataService;
    
    @Autowired private MessageSource messageSource;
    
    
    //--------------------   Page Admin   ---------------------------- 
    
    @GetMapping(value = {"/admin"})
    public String admin(Model model, Principal principal){     
        
        PageAdmin pageAdmin = new PageAdmin(model, principal, messageSource);   
        boolean connect = userService.findRoleByUsername(pageAdmin.returnUser(principal)).equals("ROLE_SUPADMIN");
                 
        return pageAdmin.getPage(connect, dataService);
        
    } 
    
    
    
    @PostMapping(value = {"/admin/stock/{id}"})
    public String editStockVolant(@PathVariable(value = "id") Long id, @ModelAttribute FormTypeVolant putTypeVolant, Model model, Principal principal) {
        putTypeVolant.setId(id);
        
        PageAdmin pageAdmin = new PageAdmin(model, principal, messageSource);   
        boolean connect = userService.findRoleByUsername(pageAdmin.returnUser(principal)).equals("ROLE_SUPADMIN");
        
        pageAdmin.addReponse(typeVolantService.updateTypeVolant(putTypeVolant));
        
        return pageAdmin.getPage(connect, dataService);
    }
    
    
    @PostMapping(value = {"/admin/budget"})
    public String editBudget(@ModelAttribute FormData putData, Model model, Principal principal) {
        PageAdmin pageAdmin = new PageAdmin(model, principal, messageSource);   
        boolean connect = userService.findRoleByUsername(pageAdmin.returnUser(principal)).equals("ROLE_SUPADMIN");
        
        pageAdmin.addReponse(dataService.updateBudget(putData));
        
        return pageAdmin.getPage(connect, dataService);
    }
    
    
    @PostMapping(value = {"/admin/seuil"})
    public String editSeuil(@ModelAttribute FormData putData, Model model, Principal principal) {
        PageAdmin pageAdmin = new PageAdmin(model, principal, messageSource);   
        boolean connect = userService.findRoleByUsername(pageAdmin.returnUser(principal)).equals("ROLE_SUPADMIN");
        
        pageAdmin.addReponse(dataService.updateSeuil(putData));
        
        return pageAdmin.getPage(connect, dataService);
    }

    
}

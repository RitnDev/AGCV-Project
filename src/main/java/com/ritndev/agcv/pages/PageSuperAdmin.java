package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormConsoMois;
import com.ritndev.agcv.form.FormTypeTube;
import com.ritndev.agcv.form.FormTypeVolant;
import com.ritndev.agcv.form.FormUser;
import com.ritndev.agcv.model.AppUser;
import com.ritndev.agcv.model.enumeration.NomMois;
import com.ritndev.agcv.model.enumeration.NomTypeTube;
import com.ritndev.agcv.services.IUserService;
import com.ritndev.agcv.services.IagcvService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageSuperAdmin extends Page {

    //Construction de la page ADMIN :
    public PageSuperAdmin() {
        
        super.setPage("Super Administrateur");
        super.setLinkPage(new Link("Super Admin", "/superAdmin"));
        super.setAdminPage(true);
        super.setLinkAdminPage(new Link("Déconnexion", "/logout"));
        
        Link index = new Link("Retour page Admin","/admin");
        
        super.addLinks(index);
       
        Link newData = new Link("Nouvelle MAIN-DATA", "/newData", "topnav-menu-data");
        Link newUser = new Link("Nouvel utilisateur", "#", "topnav-menu-user");
        Link newStock = new Link("Nouveau stock de competition", "/newStock", "topnav-menu-data");
        Link newTypeTube = new Link("Nouveau Type de tube", "#", "topnav-menu-typetube");
        
        Link[] menu = new Link[] {
            newData,
            newUser,
            newStock,
            newTypeTube,
        };
        super.setMenu(menu);
        
    }

      
    
    public String getPage(Model model, Principal principal, IagcvService service, IUserService userService) {
    
        String message = "Ici se trouve la page : ";
        message = message + getPage();
 
        Map<Long,String> userRoleList = new HashMap<>();
        for (AppUser user : userService.listUser()){
           userRoleList.put(user.getUserId(), formatRole(userService.findRoleByUsername(user.getUserName())));
        }
        
        
        // Add Attribute :
        model = getPageGenerique(model, principal);
        model.addAttribute("message", message);
        model.addAttribute("mainData", service.listMainData());
        model.addAttribute("userList", userService.listUser());
        model.addAttribute("stockList", service.listStock());
        model.addAttribute("userRoleList", userRoleList);
        model.addAttribute("typeTubeList", service.listDataTypeTube());
        model.addAttribute("prixTubeList", service.listPrixTube());
        model.addAttribute("typeVolantList", service.listTypeVolant());
        model.addAttribute("consoMoisList", service.listConsoMois());
        model.addAttribute("newUser", new FormUser());
        model.addAttribute("newTypeTube", new FormTypeTube());
        model.addAttribute("newTypeVolant", new FormTypeVolant());
        model.addAttribute("newConsoMois", new FormConsoMois());
        model.addAttribute("roleList", userService.listRole());
        model.addAttribute("saisons", service.listSaison());
        model.addAttribute("nomMois", new ArrayList<>(Arrays.asList(NomMois.values())));
        model.addAttribute("nomTypeTubes", new ArrayList<>(Arrays.asList(NomTypeTube.values())));
        
        return returnPage();
    }
    
    
    //Formalise le nom du role
    public String formatRole(String role) {
        String result;
        switch(role){
            case "ROLE_ADMIN" -> result = "Admin";
            case "ROLE_SUPADMIN" -> result = "Super Admin";
            default -> result = "User";
        }
        return result;
    }
    
    
}

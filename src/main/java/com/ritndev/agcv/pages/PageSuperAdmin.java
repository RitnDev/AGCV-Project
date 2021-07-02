package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormConsoMois;
import com.ritndev.agcv.form.FormTypeTube;
import com.ritndev.agcv.form.FormTypeVolant;
import com.ritndev.agcv.form.FormUser;
import com.ritndev.agcv.model.AppUser;
import com.ritndev.agcv.model.enumeration.NomMois;
import com.ritndev.agcv.model.enumeration.NomTypeTube;
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

    //Construction de la page SUPER-ADMIN :
    public PageSuperAdmin(Model model, Principal principal) {
        super(model, principal);
        
        super.setNomPage("Super Administrateur");
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

      
    
    public String getPage() {
    
        String message = "Ici se trouve la page : ";
        message = message + getNomPage();
 
        Map<Long,String> userRoleList = new HashMap<>();
        for (AppUser user : getUserService().listUser()){
           userRoleList.put(user.getUserId(), formatRole(getUserService().findRoleByUsername(user.getUserName())));
        }
        
        
        // Add Attribute :
        getPageGenerique();
        super.getModel().addAttribute("message", message);
        super.getModel().addAttribute("mainData", getDataService().listMainData());
        super.getModel().addAttribute("userList", getUserService().listUser());
        super.getModel().addAttribute("stockList", getStockService().listStock());
        super.getModel().addAttribute("userRoleList", userRoleList);
        super.getModel().addAttribute("typeTubeList", getTypeTubeService().listDataTypeTube());
        super.getModel().addAttribute("prixTubeList", getPrixTubeService().listPrixTube());
        super.getModel().addAttribute("typeVolantList", getTypeVolantService().listTypeVolant());
        super.getModel().addAttribute("consoMoisList", getConsoMoisService().listConsoMois());
        super.getModel().addAttribute("newUser", new FormUser());
        super.getModel().addAttribute("newTypeTube", new FormTypeTube());
        super.getModel().addAttribute("newTypeVolant", new FormTypeVolant());
        super.getModel().addAttribute("newConsoMois", new FormConsoMois());
        super.getModel().addAttribute("roleList", getUserService().listRole());
        super.getModel().addAttribute("saisons", getSaisonService().listSaison());
        super.getModel().addAttribute("nomMois", new ArrayList<>(Arrays.asList(NomMois.values())));
        super.getModel().addAttribute("nomTypeTubes", new ArrayList<>(Arrays.asList(NomTypeTube.values())));
        
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

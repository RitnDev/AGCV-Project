package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormConsoMois;
import com.ritndev.agcv.form.FormTypeTube;
import com.ritndev.agcv.form.FormTypeVolant;
import com.ritndev.agcv.form.FormUser;
import com.ritndev.agcv.model.enumeration.NomMois;
import com.ritndev.agcv.model.enumeration.NomTypeTube;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageSuperAdmin extends Page {

    //Construction de la page SUPER-ADMIN :
    public PageSuperAdmin(Model model, Principal principal, MessageSource messageSource) {
        super("superAdmin", model, principal, messageSource);
        
        super.setAdminPage(true);
        super.replaceLinkAdminPage("superAdmin", "logout");
        super.addLinks(returnLink("superAdmin", "admin"));
        super.addLinks(returnLink("index"));
       
        Link newData = new Link("Nouvelle MAIN-DATA", "/newData", "topnav-menu-data");
        Link newUser = new Link("Nouvel utilisateur", "#", "topnav-menu-user");
        Link newTypeTube = new Link("Nouveau Type de tube", "#", "topnav-menu-typetube");
        
        Link[] menu = new Link[] {
            newData,
            newUser,
            newTypeTube,
        };
        super.setMenu(menu);
        
    }

      
    
    public String getPage() {
        // Add Attribute :
        getPageGenerique();
       
        super.getModel().addAttribute("newUser", new FormUser());
        super.getModel().addAttribute("newTypeTube", new FormTypeTube());
        super.getModel().addAttribute("newTypeVolant", new FormTypeVolant());
        super.getModel().addAttribute("newConsoMois", new FormConsoMois());
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

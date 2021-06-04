package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormMembre;
import com.ritndev.agcv.form.FormSaison;
import com.ritndev.agcv.services.IUserService;
import com.ritndev.agcv.services.IagcvService;
import java.security.Principal;
import java.util.Calendar;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageAdmin extends Page {

    //Construction de la page ADMIN :
    public PageAdmin() {
        
        super.setPage("Administrateur");
        super.setLinkPage(new Link("Admin", "/admin"));
        super.setAdminPage(true);
        super.setSuperAdminPage(true);
        super.setLinkAdminPage(new Link("Déconnexion", "/logout"));
        
        Link index = new Link("Page principale","/index");
        super.addLinks(index);
        
        
        Link newSaison = new Link("Nouvelle saison", "#", "topnav-menu-saison");
        Link newMembre = new Link("Nouveau membre", "#", "topnav-menu-membre"); 
        
        Link[] menu = new Link[] {
            newSaison,
            newMembre
        };
        super.setMenu(menu);
               
    }

      
    
    public String getPage(Model model, Principal principal, IagcvService service, IUserService userService) {
             
        String message = "Ici se trouve la page : ";
        message = message + getPage();
        
        String budget = service.returnMainData().getBudgetDefault();
        
        // Add Attribute :
        model = getPageGenerique(model, principal);
        model.addAttribute("message", message);
        model.addAttribute("newMembre", new FormMembre("",""));
        //Charge l'année en cours + Budget par défaut "1000" + saison active par defaut.
        model.addAttribute("newSaison", new FormSaison(Calendar.getInstance().get(Calendar.YEAR),budget, true));
        model.addAttribute("listMembres", service.listMembre());
        model.addAttribute("listSaisons", service.listSaison());
        
        boolean connect = userService.findRoleByUsername(super.returnUser(principal)).equals("ROLE_SUPADMIN");
        Link pageSupAdmin = new Link("supAdmin", "Super Admin", "/superAdmin", connect);
        super.addLinks(pageSupAdmin);
        
        return returnPage();
        
    }
    
}

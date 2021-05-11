package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.model.MainData;
import com.ritndev.agcv.services.IagcvService;
import java.security.Principal;
import java.util.List;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageSuperAdmin extends Page {

    //Construction de la page ADMIN :
    public PageSuperAdmin() {
        
        super.setPage("Super Administrateur");
        super.setLinkPage(new Link("Super Admin", "superAdmin"));
        super.setAdminPage(true);
        super.setLinkAdminPage(new Link("Déconnexion", "logout"));
        
        Link index = new Link("Page principale","logout");
        
        super.addLinks(index);
       
        Link newData = new Link("Nouvelle DATA-MAIN", "/newData", "topnav-menu-data");
        
        Link[] menu = new Link[] {
            newData
        };
        super.setMenu(menu);
               
    }

      
    
    
    public String getPage(Model model, Principal principal, IagcvService service) {
    
        String message = "Ici se trouve la page : ";
        message = message + getPage();
        
        List<MainData> mainData = service.listMainData();
                
        // Add Attribute :
        model = getPageGenerique(model, principal);
        model.addAttribute("message", message);
        model.addAttribute("mainData", mainData);
                
        return returnPage();
        
    }
    
}

package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormProfil;
import com.ritndev.agcv.services.IProfilService;
import com.ritndev.agcv.services.IagcvService;
import java.security.Principal;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageProfil extends Page{

    public PageProfil() {
        
        super.setPage("Profil");
        super.setLinkPage(new Link("Profil","profil"));
        super.setAdminPage(true);
        super.setLinkAdminPage(new Link("Admin", "/admin"));
        
        Link index = new Link("Page principale","/index");
        super.addLinks(index);
        
        Link newProfil = new Link("Nouveau profil", "#", "topnav-menu-profil"); 
        
        Link[] menu = new Link[] {
            newProfil
        };
        super.setMenu(menu);
                
    }

    
    
    public String getPage(Model model, Principal principal, IProfilService service, IagcvService AGCVservice) {
        
        model = getPageGenerique(model, principal);
        
        model.addAttribute("listMembres", AGCVservice.listMembre());
        model.addAttribute("newProfil", new FormProfil());
        model.addAttribute("listProfils", service.listProfil());
        
        return returnPage();
    }
    
    
    
}

package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormMembre;
import com.ritndev.agcv.form.FormSaison;
import com.ritndev.agcv.model.Membre;
import com.ritndev.agcv.services.IagcvService;
import java.security.Principal;
import java.util.Calendar;
import java.util.List;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageAdmin extends Page {

    //Construction de la page ADMIN :
    public PageAdmin() {
        
        super.setPage("Administrateur");
        super.setLinkPage(new Link("Admin", "admin"));
        super.setAdminPage(true);
        super.setLinkAdminPage(new Link("Déconnexion", "logout"));
        
        Link index = new Link("Page principale","index");
        Link commandesMembres = new Link("Commandes tubes des membres","commandesMembres");
        
        Link[] links = new Link[] {
            commandesMembres,
            index
        };
        super.setLinks(links);
        
        
        
        Link newSaison = new Link("Nouvelle saison", "#", "topnav-menu-saison");
        Link newMembre = new Link("Nouveau membre", "#", "topnav-menu-membre"); 
        
        Link[] menu = new Link[] {
            newSaison,
            newMembre
        };
        super.setMenu(menu);
               
    }

      
    
    
    public String getPage(Model model, Principal principal, IagcvService service) {
           
        //liste des membres en BDD
        List<Membre> listMembres = service.listMembre();
        
        
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int year1 = year + 1;
        
        FormMembre nouveauMembre = new FormMembre("","");
        FormSaison nouvelleSaison = new FormSaison();
        nouvelleSaison.setAnnee_debut(year);
        nouvelleSaison.setAnnee_fin(year1);
        nouvelleSaison.setBudget("1000.00");
            
        String message = "Ici se trouve la page : ";
        message = message + getPage();
        
        // Add Attribute :
        model = getPageGenerique(model, principal);
        model.addAttribute("message", message);
        model.addAttribute("newMembre", nouveauMembre);
        model.addAttribute("newSaison", nouvelleSaison);
        model.addAttribute("listMembres", listMembres);
        
        
        return returnPage();
        
    }
    
}

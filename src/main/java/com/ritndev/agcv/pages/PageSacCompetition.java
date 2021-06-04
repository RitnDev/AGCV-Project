package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormCompet;
import com.ritndev.agcv.model.Saison;
import com.ritndev.agcv.model.StockCompetition;
import com.ritndev.agcv.services.IUserService;
import com.ritndev.agcv.services.IagcvService;
import java.security.Principal;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageSacCompetition extends Page {
    
    public PageSacCompetition() {
       
        super.setPage("Gestion du stock de compétition");
        super.setLinkPage(new Link("Gestion du stock de compétition", "sacCompetition", "topnav-menu"));
        super.setAdminPage(true);
        super.setLinkAdminPage(new Link("Admin", "/admin"));
        
        
        Link competition = new Link("Nouvelle compétition", "#", "topnav-menu-compet"); 
        Link stock = new Link("Ajuster le stock", "#", "topnav-menu-stock");
        
        Link[] menu = new Link[] {
            competition,
            stock
        };
        super.setMenu(menu);
        
        
        Link index = new Link("Page principale","/index");
        super.addLinks(index);
        
    }
    
    
    public String getPage(Model model, Principal principal, IagcvService service, IUserService userService) {
                
        String message = "Ici se trouve la page : ";
        message = message + getPage() + " ";
        message = message + "Ici sera affiché la liste des compétition de la saison. ";
        message = message + "Il sera possible de faire le restockage du stock de compétition.";
        
        Saison saisonActuelle = service.findByIdSaison(service.returnMainData().getIdSaison());
        StockCompetition stockActuel = service.findByIdStock(service.returnMainData().getIdStockCompet());
        
        int AdminConnect = 0;
        
        if (!returnUser(principal).equals("")){
            switch (userService.findRoleByUsername(returnUser(principal))) {
                case "ROLE_ADMIN" -> AdminConnect = 1;
                case "ROLE_SUPADMIN" -> AdminConnect = 2;
            }
        }
        
        // Add Attribute :
        model = getPageGenerique(model, principal);
        model.addAttribute("AdminConnect", AdminConnect);
        model.addAttribute("message", message);
        model.addAttribute("newCompetition", new FormCompet());
        model.addAttribute("saison", saisonActuelle);
        model.addAttribute("stock", stockActuel);
        model.addAttribute("listCompet", service.listCompetitionBySaison(saisonActuelle.getId()));
        
        return returnPage();
        
    }
    
}

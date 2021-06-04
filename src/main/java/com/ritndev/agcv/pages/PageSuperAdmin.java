package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.form.FormUser;
import com.ritndev.agcv.model.AppRole;
import com.ritndev.agcv.model.AppUser;
import com.ritndev.agcv.model.MainData;
import com.ritndev.agcv.model.StockCompetition;
import com.ritndev.agcv.services.IUserService;
import com.ritndev.agcv.services.IagcvService;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
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
        
        Link[] menu = new Link[] {
            newData,
            newUser,
            newStock
        };
        super.setMenu(menu);
        
    }

      
    
    public String getPage(Model model, Principal principal, IagcvService service, IUserService userService) {
    
        String message = "Ici se trouve la page : ";
        message = message + getPage();
        
        List<MainData> mainData = service.listMainData();
        List<AppUser> userList = userService.listUser();
        List<AppRole> roleList = userService.listRole();
        List<StockCompetition> stockList = service.listStock();

        Map<Long,String> userRoleList = new HashMap<>();
        for (AppUser user : userList){
           userRoleList.put(user.getUserId(), formatRole(userService.findRoleByUsername(user.getUserName())));
        }
        
        // Add Attribute :
        model = getPageGenerique(model, principal);
        model.addAttribute("message", message);
        model.addAttribute("mainData", mainData);
        model.addAttribute("userList", userList);
        model.addAttribute("stockList", stockList);
        model.addAttribute("userRoleList", userRoleList);
        model.addAttribute("newUser", new FormUser());
        model.addAttribute("roleList", roleList);
                
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

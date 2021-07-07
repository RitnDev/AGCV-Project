package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;

import java.security.Principal;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageAdmin extends Page {
    
    //Construction de la page ADMIN :
    public PageAdmin(Model model, Principal principal, MessageSource messageSource) {
        super("admin", model, principal, messageSource);
        
        super.setAdminPage(true);
        super.setSuperAdminPage(true);
        super.setLinkAdminPage("logout");
        super.addLinks(returnLink("index"));
        
        
        Link newSaison = new Link("Saisons", "/admin/saison", "topnav-menu-saison");
        Link newMembre = new Link("Membres", "/admin/membre", "topnav-menu-membre"); 
        Link newPrixTube = new Link("Prix des tubes", "/admin/prixtube", "topnav-menu-prixtube"); 
        
        Link[] menu = new Link[] {
            newSaison,
            newMembre,
            newPrixTube
        };
        super.setMenu(menu);
               
    }

    /*
    //Ajout de reponse spécifique
    @Override public void addReponse(MessageSource messageSource, String modelName, String methodeName, int result) {
        String strResult = "error";
        TypeReponse tr = TypeReponse.OTHER;
        
        if(result<=4){
            super.addReponse(messageSource, modelName, methodeName, result);
        }else{
            if(result==3004){
                strResult = "3004";
                tr = TypeReponse.ADD;
                super.getReponses().put(tr, messageSource.getMessage("reponse." + modelName + "." + methodeName + "." + strResult, null, Locale.FRENCH));
            }
        }
    }
    */
    
    
    //Renvoie la page
    public String getPage(boolean connect) {
       
        // Add Attribute :
        getPageGenerique();
        
        Link pageSupAdmin = new Link("superAdmin", "Super Admin", "/superAdmin", connect);
        super.addLinks(pageSupAdmin);
        
        return returnPage();
    }
}

package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.classes.TypeReponse;

import com.ritndev.agcv.form.FormPrixTube;

import java.security.Principal;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

/**
 *
 * @author Ritn
 */
public class PageAdmin extends Page {
    
    //Construction de la page ADMIN :
    public PageAdmin(Model model, Principal principal) {
        super(model, principal); 
        
        super.setNomPage("Administrateur");
        super.setLinkPage(new Link("Admin", "/admin"));
        super.setAdminPage(true);
        super.setSuperAdminPage(true);
        super.setLinkAdminPage(new Link("Déconnexion", "/logout"));
        
        Link index = new Link("Page principale","/index");
        super.addLinks(index);
        
        
        Link newSaison = new Link("Saisons", "/admin/saison", "topnav-menu-saison");
        Link newMembre = new Link("Membres", "/admin/membre", "topnav-menu-membre"); 
        Link newPrixTube = new Link("Nouveau prix de tube", "#", "topnav-menu-prixtube"); 
        
        Link[] menu = new Link[] {
            newSaison,
            newMembre,
            newPrixTube
        };
        super.setMenu(menu);
               
    }

    
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
    
    
    
    //Renvoie la page
    public String getPage(boolean connect) {
             
        String message = "Ici se trouve la page : ";
        message = message + getNomPage();

        
        // Add Attribute :
        getPageGenerique();
        super.getModel().addAttribute("newPrixTube", new FormPrixTube());

        Link pageSupAdmin = new Link("supAdmin", "Super Admin", "/superAdmin", connect);
        super.addLinks(pageSupAdmin);
        
        return returnPage();
    }
}

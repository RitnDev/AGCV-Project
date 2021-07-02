package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.classes.TypeReponse;

import com.ritndev.agcv.form.FormMembre;
import com.ritndev.agcv.form.FormPrixTube;
import com.ritndev.agcv.form.FormSaison;

import com.ritndev.agcv.model.enumeration.NomTypeTube;

import java.security.Principal;
import java.util.Calendar;
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
        
        
        Link newSaison = new Link("Nouvelle saison", "#", "topnav-menu-saison");
        Link newMembre = new Link("Nouveau membre", "#", "topnav-menu-membre"); 
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
    public String getPage() {
             
        String message = "Ici se trouve la page : ";
        message = message + getNomPage();
               
                
        String budget = getDataService().returnMainData().getBudgetDefault();
        
        // Add Attribute :
        getPageGenerique();
        super.getModel().addAttribute("message", message);
        super.getModel().addAttribute("newMembre", new FormMembre());
        super.getModel().addAttribute("newPrixTube", new FormPrixTube());
        //Charge l'année en cours + Budget par défaut "1000" + saison active par defaut.
        super.getModel().addAttribute("newSaison", new FormSaison(Calendar.getInstance().get(Calendar.YEAR),budget, true));
        super.getModel().addAttribute("listMembres", getMembreService().listMembre());
        super.getModel().addAttribute("listSaisons", getSaisonService().listSaison());
        super.getModel().addAttribute("listPrixTubes", getPrixTubeService().listPrixTube());
        super.getModel().addAttribute("prixPlastiques", getPrixTubeService().ListPrixTubeName(NomTypeTube.PLASTIQUE.toString()));
        super.getModel().addAttribute("prixEntrainements", getPrixTubeService().ListPrixTubeName(NomTypeTube.ENTRAINEMENT.toString()));
        super.getModel().addAttribute("prixCompetitions", getPrixTubeService().ListPrixTubeName(NomTypeTube.COMPETITION.toString()));
        super.getModel().addAttribute("typeTubes", getTypeTubeService().listDataTypeTube());
        
        boolean connect = getUserService().findRoleByUsername(super.returnUser(super.getPrincipal())).equals("ROLE_SUPADMIN");
        Link pageSupAdmin = new Link("supAdmin", "Super Admin", "/superAdmin", connect);
        super.addLinks(pageSupAdmin);
        
        return returnPage();
    }
}

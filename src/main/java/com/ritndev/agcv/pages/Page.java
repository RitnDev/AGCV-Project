package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.classes.TypeReponse;
import com.ritndev.agcv.utils.WebUtils;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;


/**
 *
 * @author Ritn
 */
public class Page {
           
    @Getter @Setter private Model model;
    @Getter @Setter private Principal principal;
    @Getter @Setter private MessageSource messageSource;
    
    //Nom de la page html
    @Getter @Setter private String nom;
    
    //Nom de la page
    @Getter @Setter
    private String nomPage;
    
    //lien de la page
    @Getter @Setter
    private Link linkPage;
    
    //lien vers la page Admin
    @Getter @Setter
    private Link linkAdminPage;
    
    //Listes des liens vers les autres pages (menu)
    @Getter @Setter
    private List<Link> links = new ArrayList<>();
    
    //Listes des menus vers les autres pages (menu)
    @Getter @Setter
    private Link[] menu;
    
    //La page est elle une page admin ?
    @Getter @Setter
    private boolean adminPage = false;
    
    //liste des différentes réponses à affichés suite à l'execution d'une methode : GET/POST/...
    @Getter @Setter
    private Map<TypeReponse, String> reponses = new HashMap<>();;
    
    //Y a-t-il un lien vers la page super admin ?
    @Getter @Setter
    private boolean superAdminPage = false;
    
    
    //Constructeur
    public Page() {}
    
    public Page(String nom, Model model, Principal principal, MessageSource messageSource) {
        this.nom = nom;
        this.model = model;
        this.principal = principal;
        this.messageSource = messageSource;
        
        this.nomPage = messageSource.getMessage("texte.page." + nom + ".titre", null, Locale.FRENCH);
        this.linkPage = returnLink(nom);
        this.linkAdminPage = returnLink("admin");
    }
    
    
    
    /*
        --- METHODES ---
    */
    
    
    //Construit la vue (générique)
    public void getPageGenerique() {
        
        // Add Attribute :
        model.addAttribute("pageTitle", returnTitre());
        model.addAttribute("pageName", getNomPage());
        model.addAttribute("buttonAdmin", isAdminPage());
        model.addAttribute("adminPage", getLinkAdminPage());
        
        model.addAttribute("menus", getMenu());
        model.addAttribute("links", getLinks());
        model.addAttribute("reponses", getReponses());
        
        //Login page
        String user = returnUser(principal);
        Boolean connect = !user.equals("");
  
        model.addAttribute("userActif", user);
        model.addAttribute("log", "Connecté : " + user);
        model.addAttribute("connect", connect);

        //return model;
    }
    
    
    //Retourne le nom d'utilisateur connecté
    public String returnUser(Principal principal){
        String user = "";
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            user = WebUtils.toString(loginedUser);
        }
        return user;
    }
    
        
    //Ajouter une reponse à la liste des réponses
    public void addReponse(TypeReponse tr, String reponse){
        reponses.put(tr, reponse);
    }
    
    public void addReponse(String modelName, String methodeName, int result) {
        String strResult = "error";
        TypeReponse tr = TypeReponse.OTHER;
        
        switch (methodeName) {
            case "create" : tr = TypeReponse.ADD;
            case "remove" : tr = TypeReponse.REMOVE;
            case "edit" : tr = TypeReponse.EDIT;
            case "info" : tr = TypeReponse.INFO;
        }
        
        switch (result) {
            default : {
                //Erreur lors de l'execution
                strResult = "error";
                tr = TypeReponse.ERROR;
                break;
            }
            case 1 : {
                //Impossible de créer à cause d'un autre élément qui rentre en conflit.
                strResult = "error1";
                tr = TypeReponse.ERROR;
                break;
            }
            case 2 : {
                strResult = "success";
                break;
            } //Reussite !
            
            //Autres réponses possible dans certain cas :
            case 3 : {
                strResult = "result3";
                tr = TypeReponse.INFO;
                break;
            }          
            case 4 : {
                strResult = "result4";
                break;
            }
        }
        
        reponses.put(tr, messageSource.getMessage("reponse." + modelName + "." + methodeName + "." + strResult, null, Locale.FRENCH));
    }
    
    
    public void addLinks(Link link) {
        links.add(link);
    }
    
    //Retourne le titre de la page
    public String returnTitre(){
        return messageSource.getMessage("texte.global.page.titre", null, Locale.FRENCH) + getNomPage();
    }
    
    //Renvoie vers la Page
    public String returnPage() {
        return "/" + nom;
    }
    
    //Renvoie vers la Page
    public Link returnLink(String nom) {
        return new Link(messageSource.getMessage("texte.page." + nom + ".link", null, Locale.FRENCH), nom);
    }
    
    
    //Remplace le lien vers la page Admin
    public void setLinkAdminPage(String nom){
        linkAdminPage = returnLink(nom);
    }
    
    public String getTexteRessource(String paramTexte) {
        return messageSource.getMessage("texte.page." + nom + "." + paramTexte, null, Locale.FRENCH);
    }
    
}

package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.classes.Reponse;
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
    @Getter @Setter private String nomPage;
    
    //lien de la page
    @Getter @Setter private Link linkPage;
    
    //lien vers la page Admin
    @Getter @Setter private Link linkAdminPage;
    
    //Listes des liens vers les autres pages (menu)
    @Getter @Setter private List<Link> links = new ArrayList<>();
    
    //Listes des menus vers les autres pages (menu)
    @Getter @Setter private Link[] menu;
    
    //La page est elle une page admin ?
    @Getter @Setter private boolean adminPage = false;
    
    //liste des différentes réponses à affichés suite à l'execution d'une methode : GET/POST/...
    @Getter @Setter private Map<TypeReponse, String> reponses = new HashMap<>();;
    
    //Y a-t-il un lien vers la page super admin ?
    @Getter @Setter private boolean superAdminPage = false;
    
    
    
    
    //Constructeur   
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
        model.addAttribute("log", getGlobalTexteRessource("connect") + user);
        model.addAttribute("connect", connect);
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
    public void addReponse(Reponse reponse){
        reponses.put(reponse.getTypeReponse(), messageSource.getMessage(reponse.getStrReponse(), null, Locale.FRENCH));
    }
     
    //Ajout de lien dans le menu Links de la page
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
    public final Link returnLink(String nom) {
        return new Link(messageSource.getMessage("texte.page." + nom + ".link", null, Locale.FRENCH), "/" + nom);
    }
    public final Link returnLink(String nom, String paramTexte) {
        return new Link(messageSource.getMessage("texte.page." + nom + "." + paramTexte, null, Locale.FRENCH), "/" + paramTexte);
    }
    
    
    //Remplace le lien vers la page Admin
    public void replaceLinkAdminPage(String nom, String paramTexte){
        linkAdminPage = returnLink(nom, paramTexte);
    }
    
    //Renvoie un texte spécifique
    public String getTexteRessource(String paramTexte) {
        return messageSource.getMessage("texte.page." + nom + "." + paramTexte, null, Locale.FRENCH);
    }
    
    //Renvoie une information globale
    public final String getGlobalTexteRessource(String paramTexte) {
        return messageSource.getMessage("texte.global.page." + paramTexte, null, Locale.FRENCH);
    }

    
}

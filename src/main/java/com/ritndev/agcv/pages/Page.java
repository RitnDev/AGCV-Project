package com.ritndev.agcv.pages;

import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.classes.TypeReponse;
import com.ritndev.agcv.utils.WebUtils;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;


/**
 *
 * @author Ritn
 */
public class Page {
    
    
    private final String title = "AGCV - ";
    
    
    //Nom de la page
    private String page;
    public String getPage() {return page;}
    public void setPage(String page) {this.page = page;}
    
    //lien de la page
    private Link linkPage;
    public Link getLinkPage() {return linkPage;}
    public void setLinkPage(Link linkPage) {this.linkPage = linkPage;}
    
    //lien vers la page Admin
    private Link linkAdminPage;
    public Link getLinkAdminPage() {return linkAdminPage;}
    public void setLinkAdminPage(Link linkAdminPage) {this.linkAdminPage = linkAdminPage;}
    
    //Listes des liens vers les autres pages (menu)
    private List<Link> links = new ArrayList<>();
    public List<Link> getLinks() {return links;}
    public void setLinks(List<Link> links) {this.links = links;}
    
    //Listes des menus vers les autres pages (menu)
    private Link[] menu;
    public Link[] getMenu() {return menu;}
    public void setMenu(Link[] menu) {this.menu = menu;}
    
    //La page est elle une page admin ?
    private boolean adminPage = false;
    public boolean isAdminPage() {return adminPage;}
    public void setAdminPage(boolean adminPage) {this.adminPage = adminPage;}
    
    //liste des différentes réponses à affichés suite à l'execution d'une methode : GET/POST/...
    private Map<TypeReponse, String> reponses = new HashMap<>();;
    public Map<TypeReponse, String> getReponses() {return reponses;}
    public void setReponses(Map<TypeReponse, String> reponses) {this.reponses = reponses;}
    
    //Y a-t-il un lien vers la page super admin ?
    private boolean superAdminPage = false;
    public boolean isSuperAdminPage() {return superAdminPage;}
    public void setSuperAdminPage(boolean superAdminPage) {this.superAdminPage = superAdminPage;}
     
    
    //Constructeur
    public Page() {}
    
    
    
    /*
        --- METHODES ---
    */
    
    
    //Construit la vue (générique)
    Model getPageGenerique(Model model, Principal principal) {
        
        
        // Add Attribute :
        model.addAttribute("pageTitle", returnTitre());
        model.addAttribute("pageName", getPage());
        model.addAttribute("buttonAdmin", isAdminPage());
        model.addAttribute("adminPage", getLinkAdminPage());
        
        model.addAttribute("menus", getMenu());
        model.addAttribute("links", getLinks());
        model.addAttribute("reponses", getReponses());
        
        //Login page
        String user = returnUser(principal);
        Boolean connect = !user.equals("");
  
        model.addAttribute("log", "Connecté : " + user);
        model.addAttribute("connect", connect);

        return model;
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
    
    public void addLinks(Link link) {
        links.add(link);
    }
    
    //Retourne le titre de la page
    public String returnTitre(){
        return title + getPage();
    }
    
    //Renvoie vers la Page
    public String returnPage() {
        return linkPage.getHref();
    }
    
    //Remplace le titre généré par celui inscrit en paramètre de la méthode
    public void setTitlePage(Model model, String title){
        model.addAttribute("pageTitle", title);
    }
    
     
}

package com.ritndev.agcv.pages;

import com.ritndev.agcv.InterfaceService.*;
import com.ritndev.agcv.classes.Link;
import com.ritndev.agcv.classes.NomService;
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
    
    private final String title = "AGCV - ";
        
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
    
    @Getter @Setter Map<String,Object> listServices = new HashMap<>();

    
    //Constructeur
    public Page() {}

    public Page(Model model, Principal principal) {
        this.model = model;
        this.principal = principal;
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
    
    public void addReponse(MessageSource messageSource, String modelName, String methodeName, int result) {
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
        return title + getNomPage();
    }
    
    //Renvoie vers la Page
    public String returnPage() {
        return linkPage.getHref();
    }
    
    //Remplace le titre généré par celui inscrit en paramètre de la méthode
    public void setTitlePage(String title){
        model.addAttribute("pageTitle", title);
    }
    
    public void addService(String nomService, Object service) {
        listServices.put(nomService, service);
    }
    
    public IUserService getUserService() {
        return (IUserService)listServices.get(NomService.USER.toString());
    }
    public IMainDataService getDataService() {
        return (IMainDataService)listServices.get(NomService.DATA.toString());
    }
    public IMembreService getMembreService() {
        return (IMembreService)listServices.get(NomService.MEMBRE.toString());
    }
    public ISaisonService getSaisonService() {
        return (ISaisonService)listServices.get(NomService.SAISON.toString());
    }
    public ICommandeService getCommandeService() {
        return (ICommandeService)listServices.get(NomService.COMMANDE.toString());
    }
    public ICompetitionService getCompetitionService() {
        return (ICompetitionService)listServices.get(NomService.COMPETITION.toString());
    }
    public ITypeTubeService getTypeTubeService() {
        return (ITypeTubeService)listServices.get(NomService.TYPETUBE.toString());
    }
    public IConsoMoisService getConsoMoisService() {
        return (IConsoMoisService)listServices.get(NomService.CONSOMOIS.toString());
    }
    public IPrixTubeService getPrixTubeService() {
        return (IPrixTubeService)listServices.get(NomService.PRIXTUBE.toString());
    }
    public ITypeVolantService getTypeVolantService() {
        return (ITypeVolantService)listServices.get(NomService.TYPEVOLANT.toString());
    }
    public IStockService getStockService() {
        return (IStockService)listServices.get(NomService.STOCK.toString());
    }
    
}

package com.ritndev.agcv.Validations;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormUser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormUserValidation {
    
    private final String regexIdent = "^[A-Za-zéèçàöôëêùâäüûîï]{2,20}(-| )?([A-Za-zéèçàöôëêùâäüûîï]{2,20})?$";
    private final String regexMdp = "^([a-zA-Zéèçàöôëêùâäüûîï]+[a-zA-Zéèçàöôëêùâäüûîï0-9 -]{2,40})";
    
    @Setter private boolean valid;
    public boolean getValid() {return valid;}
    
    @Getter @Setter private FormUser fUser;
    @Getter @Setter private int resultValue;
    @Getter @Setter private boolean userValid;
    
    private final Reponse reponse;

    //Constructeur
    public FormUserValidation(FormUser fUser, boolean userValid) {
        this.fUser = fUser;
        this.resultValue = 0;
        this.userValid = userValid;
        this.valid = isValid();
        this.reponse = getReponse();
    }
    
    
    
    /*
    -> userValid = true 
    resultVal :
           0 = tous les champs sont incorrect.
           1 = mdp incorrect
           3 = identifiant incorrect
           4 = tous les champs sont correct.
    
    -> userValid = false 
    resultVal :
           0  = tous les champs sont incorrect.
           1  = Les champs "Nouveau mot de passe" et "Confirmation mot de passe" sont incorrects.
           10 = Les champs "Ancien mot de passe" et "Confirmation mot de passe" sont incorrects.
           11 = Le champ "Confirmation mot de passe" est incorrect.
           20 = Les champs "Ancien mot de passe" et "Nouveau mot de passe" sont incorrects.
           21 = Le champ "Nouveau mot de passe" est incorrect.
           30 = Le champ "Ancien mot de passe" est incorrect.
           31 = tous les champs sont correct.
    */
    private boolean isValid() {
        boolean result = false;
        Pattern patternIdent = Pattern.compile(regexIdent);
        Pattern patternMdp = Pattern.compile(regexMdp);
        
        if (userValid) {
            
            //Test de validation du Identifiant :
            if(!fUser.getIdentifiant().isEmpty()) {
                Matcher matcher = patternIdent.matcher(fUser.getIdentifiant());
                if(matcher.matches()) {
                    resultValue = resultValue + 1;
                }
            }

            //Test de validation du Mot de passe :
            if(!fUser.getMdp().isEmpty()) {
                Matcher matcher = patternMdp.matcher(fUser.getMdp());
                if(matcher.matches()) {
                    resultValue = resultValue + 3;
                }
            }
            
            if (resultValue==4){
                result = true;
            }
            
        }else{
            
            //Test de validation du Mot de passe :
            if(!fUser.getMdp().isEmpty()) {
                Matcher matcher = patternMdp.matcher(fUser.getMdp());
                if(matcher.matches()) {
                    resultValue = resultValue + 1;
                }
            }

            //Test de validation du Nouveau Mot de passe :
            if(!fUser.getNewMdp().isEmpty()) {
                Matcher matcher = patternMdp.matcher(fUser.getNewMdp());
                if(matcher.matches()) {
                    resultValue = resultValue + 10;
                }
            }

            //Test de validation de la confirmation Mot de passe :
            if(!fUser.getConfirmMdp().isEmpty()) {
                Matcher matcher = patternMdp.matcher(fUser.getConfirmMdp());
                if(matcher.matches()) {
                    resultValue = resultValue + 20;
                }
            }
            
            if (resultValue==9){
                result = true;
            }
                    
        }
        
        return result;
    }
    
    //Construction de la reponse
    public Reponse getReponse() {
        if (userValid) {
            return new Reponse("user", "other", resultValue, true);
        }else{
            return new Reponse("mdp", "other", resultValue, true);
        }
    }
    
}

package com.ritndev.agcv.Validations;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormSaison;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormSaisonValidation {
    
    private final String regexBudget = "^(0?|[1-9]+\\d*)(.|,)((0{1}|(\\d{1,2})))$";
    private final String regexAnnee = "^[1-9]\\d*$";
    
    @Setter private boolean valid;
    public boolean getValid() {return valid;}
    
    @Getter @Setter private FormSaison fSaison;
    @Getter @Setter private int resultValue;
    
    private final Reponse reponse;

    //Constructeur
    public FormSaisonValidation(FormSaison fSaison) {
        this.fSaison = fSaison;
        this.resultValue = 0;
        this.valid = isValid();
        this.reponse = getReponse();
    }
    
    
    
    /*
    resultVal :
           0 = tous les champs sont incorrects.
           1 = Budget est incorrect.
           3 = Annee début est incorrect.
           4 = tous les champs sont correct.
    */
    private boolean isValid() {
        boolean result = false;
        Pattern patternAnnee = Pattern.compile(regexAnnee);
        Pattern patternBudget = Pattern.compile(regexBudget);
        
        //Test de validation du Annee de début de saison :
        if(!fSaison.getAnnee_debut().isEmpty()) {
            Matcher matcher = patternAnnee.matcher(fSaison.getAnnee_debut());
            if(matcher.matches()) {
                resultValue = resultValue + 1;
            }
        }
        
        //Test de validation du Budget :
        if(!fSaison.getBudget().isEmpty()) {
            Matcher matcher = patternBudget.matcher(fSaison.getBudget());
            if(matcher.matches()) {
                resultValue = resultValue + 3;
            }
        }
        
        if (resultValue==4){
            result = true;
        }
        return result;
    }
    
    //Construction de la reponse
    public Reponse getReponse() {
        return new Reponse("saison", "other", resultValue, true);
    }
    
}

package com.ritndev.agcv.Validations;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormMembre;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormTypeVolantValidation {
    
    private final String regex = "\\p{L}*(-\\p{L}*)*";
    
    @Setter private boolean valid;
    public boolean getValid() {return valid;}
    
    @Getter @Setter private FormMembre fMembre;
    @Getter @Setter private int resultValue;
    
    private final Reponse reponse;

    //Constructeur
    public FormTypeVolantValidation(FormMembre fMembre) {
        this.fMembre = fMembre;
        this.resultValue = 0;
        this.valid = isValid();
        this.reponse = getReponse();
    }
    
    
    
    /*
    resultVal :
           0 = prénom et nom incorrect.
           1 = nom est incorrect.
           3 = prénom incorrect.
           4 = tous les champs sont correct.
    */
    private boolean isValid() {
        boolean result = false;
        Pattern pattern = Pattern.compile(regex);
        
        //Test de validation du prénom :
        if(!fMembre.getPrenom().isEmpty()) {
            Matcher matcher = pattern.matcher(fMembre.getPrenom());
            if(matcher.matches()) {
                resultValue = resultValue + 1;
            }
        }
        
        //Test de validation du nom :
        if(!fMembre.getNom().isEmpty()) {
            Matcher matcher = pattern.matcher(fMembre.getNom());
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
        return new Reponse("membre", "other", resultValue, true);
    }
    
}

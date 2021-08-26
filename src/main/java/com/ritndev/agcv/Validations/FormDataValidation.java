package com.ritndev.agcv.Validations;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormData;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormDataValidation {
    
    private final String regex1 = "^(0?|[1-9]+\\d*)(.|,)((0{1}|(\\d{1,2})))$";
    
    @Setter private boolean valid;
    public boolean getValid() {return valid;}
    
    @Getter @Setter private FormData fData;
    @Getter @Setter private int resultValue;
    
    private final Reponse reponse;

    //Constructeur
    public FormDataValidation(FormData fData) {
        this.fData = fData;
        this.resultValue = 0;
        this.valid = isValid();
        this.reponse = getReponse();
    }
    
    
    
    /*
    resultVal :
           0 = tous les champs sont incorrects.
           2 = tous les champs sont corrects.
    */
    private boolean isValid() {
        boolean result = false;
        Pattern pattern1 = Pattern.compile(regex1);
        
        //Test de validation du budget :
        if(!fData.getBudget().isEmpty()) {
            Matcher matcher = pattern1.matcher(fData.getBudget());
            if(matcher.matches()) {
                resultValue = resultValue + 2;
            }
        }
                
        if (resultValue==2){
            result = true;
        }
        return result;
    }
    
    //Construction de la reponse
    public Reponse getReponse() {
        return new Reponse("data", "other", resultValue, true);
    }
    
}

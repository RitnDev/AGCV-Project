package com.ritndev.agcv.Validations;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormTypeVolant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormTypeVolantValidation {
    
    private final String regex = "^\\d*$";
    
    @Setter private boolean valid;
    public boolean getValid() {return valid;}
    
    @Getter @Setter private FormTypeVolant fTypeVolant;
    @Getter @Setter private int resultValue;
    
    private final Reponse reponse;

    //Constructeur
    public FormTypeVolantValidation(FormTypeVolant fTypeVolant) {
        this.fTypeVolant = fTypeVolant;
        this.resultValue = 0;
        this.valid = isValid();
        this.reponse = getReponse();
    }
    
    
    
    /*
    resultVal :
           0 = Champs Stock incorrect.
           2 = tous les champs sont correct.
    */
    private boolean isValid() {
        boolean result = false;
        Pattern pattern = Pattern.compile(regex);
        
        //Test de validation du prénom :
        if(!fTypeVolant.getStock().isEmpty()) {
            Matcher matcher = pattern.matcher(fTypeVolant.getStock());
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
        return new Reponse("typevolant", "other", resultValue, true);
    }
    
}

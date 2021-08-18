package com.ritndev.agcv.Validations;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormRestock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormRestockValidation {
    
    private final String regex = "^[1-9]\\d*$";
    
    @Setter private boolean valid;
    public boolean getValid() {return valid;}
    
    @Getter @Setter private FormRestock fRestock;
    @Getter @Setter private int resultValue;
    
    private final Reponse reponse;

    //Constructeur
    public FormRestockValidation(FormRestock fRestock) {
        this.fRestock = fRestock;
        this.resultValue = 0;
        this.valid = isValid();
        this.reponse = getReponse();
    }
    
    
    
    /*
    resultVal :
           0 = Valeur de restock incorrect.
           2 = tous les champs sont correct.
    */
    private boolean isValid() {
        boolean result = false;
        Pattern pattern = Pattern.compile(regex);
        
        //Test de validation de la valeur de restock :
        if(!fRestock.getValeur().isEmpty()) {
            Matcher matcher = pattern.matcher(fRestock.getValeur());
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
        return new Reponse("restock", "other", resultValue, true);
    }
    
}

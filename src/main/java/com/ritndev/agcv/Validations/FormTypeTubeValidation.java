package com.ritndev.agcv.Validations;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormTypeTube;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormTypeTubeValidation {
    
    private final String regex = "^[1-9]\\d*$";
    
    @Setter private boolean valid;
    public boolean getValid() {return valid;}
    
    @Getter @Setter private FormTypeTube fTypeTube;
    @Getter @Setter private int resultValue;
    
    private final Reponse reponse;

    //Constructeur
    public FormTypeTubeValidation(FormTypeTube fTypeTube) {
        this.fTypeTube = fTypeTube;
        this.resultValue = 0;
        this.valid = isValid();
        this.reponse = getReponse();
    }
    
    
    
    /*
    resultVal :
           0 = Valeur de seuil bas incorrect.
           2 = tous les champs sont correct.
    */
    private boolean isValid() {
        boolean result = false;
        Pattern pattern = Pattern.compile(regex);
        
        //Test de validation de la valeur du seuil bas :
        if(!fTypeTube.getSeuilBas().isEmpty()) {
                Matcher matcher = pattern.matcher(fTypeTube.getSeuilBas());
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
        return new Reponse("typetube", "other", resultValue, true);
    }
    
}

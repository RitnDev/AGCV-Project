package com.ritndev.agcv.Validations;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormConsoMois;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormConsoMoisValidation {
    
    private final String regex = "^\\d*$";
    
    @Setter private boolean valid;
    public boolean getValid() {return valid;}
    
    @Getter @Setter private FormConsoMois fConsoMois;
    @Getter @Setter private int resultValue;
    
    private final Reponse reponse;

    //Constructeur
    public FormConsoMoisValidation(FormConsoMois fConsoMois) {
        this.fConsoMois = fConsoMois;
        this.resultValue = 0;
        this.valid = isValid();
        this.reponse = getReponse();
    }
    
    
    
    /*
    resultVal :
           0 = tous les champs sont incorrects.
           2 = tous les champs sont correct.
    */
    private boolean isValid() {
        boolean result = false;
        Pattern pattern = Pattern.compile(regex);
       
            //Test de validation du NbTubeCommande :
            if(fConsoMois.getNbTubeCommande()!=null){
                if(!fConsoMois.getNbTubeCommande().isEmpty()) {
                    Matcher matcher = pattern.matcher(fConsoMois.getNbTubeCommande());
                    if(matcher.matches()) {
                        resultValue = resultValue + 2;
                    }
                }
            }

            //Test de validation du NbTubeUtilise :
            if(fConsoMois.getNbTubeUtilise()!=null){
                if(!fConsoMois.getNbTubeUtilise().isEmpty()) {
                    Matcher matcher = pattern.matcher(fConsoMois.getNbTubeUtilise());
                    if(matcher.matches()) {
                        resultValue = resultValue + 2;
                    }
                }
            }

        
        if (resultValue==2){
            result = true;
        }
        return result;
    }
    
    //Construction de la reponse
    public Reponse getReponse() {
        return new Reponse("consomois", "other", resultValue, true);
    }
    
}

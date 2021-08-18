package com.ritndev.agcv.Validations;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormCommande;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormCommandeValidation {
    
    private final String regex = "^[1-9]\\d*$";
    
    @Setter private boolean valid;
    public boolean getValid() {return valid;}
    
    @Getter @Setter private FormCommande fCommande;
    @Getter @Setter private int resultValue;
    
    private final Reponse reponse;

    //Constructeur
    public FormCommandeValidation(FormCommande fCommande) {
        this.fCommande = fCommande;
        this.resultValue = 0;
        this.valid = isValid();
        this.reponse = getReponse();
    }
    
    
    
    /*
    resultVal :
           0 = Le champ "nombre de tube commandés" est incorrect.
           2 = tous les champs sont correct.
    */
    private boolean isValid() {
        boolean result = false;
        Pattern pattern = Pattern.compile(regex);
        
        //Test de validation du NbTubeCommande :
        if(!fCommande.getNbTubeCommande().isEmpty()) {
            Matcher matcher = pattern.matcher(fCommande.getNbTubeCommande());
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
        return new Reponse("commande", "other", resultValue, true);
    }
    
}

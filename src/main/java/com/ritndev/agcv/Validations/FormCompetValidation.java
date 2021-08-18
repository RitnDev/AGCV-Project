package com.ritndev.agcv.Validations;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormCompet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormCompetValidation {
    
    private final String regex1 = "^([a-zA-Zéèçàöôëêùâäüûîï]+[a-zA-Zéèçàöôëêùâäüûîï0-9 -]{2,40})";
    private final String regex2 = "^[1-9]\\d*$";
    
    @Setter private boolean valid;
    public boolean getValid() {return valid;}
    
    @Getter @Setter private FormCompet fCompet;
    @Getter @Setter private int resultValue;
    
    private final Reponse reponse;

    //Constructeur
    public FormCompetValidation(FormCompet fCompet) {
        this.fCompet = fCompet;
        this.resultValue = 0;
        this.valid = isValid();
        this.reponse = getReponse();
    }
    
    
    
    /*
    resultVal :
           0 = nom et TubesUtilises incorrect.
           1 = TubesUtilises est incorrect.
           3 = Nom incorrect.
           4 = tous les champs sont correct.
    */
    private boolean isValid() {
        boolean result = false;
        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);
        
        //Test de validation du nom :
        if(!fCompet.getNom().isEmpty()) {
            Matcher matcher = pattern1.matcher(fCompet.getNom());
            if(matcher.matches()) {
                resultValue = resultValue + 1;
            }
        }
        
        //Test de validation du NbTubeCommande :
        if(!fCompet.getNbTubesUtilises().isEmpty()) {
            Matcher matcher = pattern2.matcher(fCompet.getNbTubesUtilises());
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
        return new Reponse("compet", "other", resultValue, true);
    }
    
}

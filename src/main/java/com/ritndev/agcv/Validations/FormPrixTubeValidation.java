package com.ritndev.agcv.Validations;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormPrixTube;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class FormPrixTubeValidation {
    
    private final String regexMarque = "^([a-zA-Zéèçàöôëêùâäüûîï]+[a-zA-Zéèçàöôëêùâäüûîï0-9 -]{2,40})";
    private final String regexPrix = "^(0?|[1-9]+\\d*)(.|,)((0{1}|(\\d{1,2})))$";
    
    @Setter private boolean valid;
    public boolean getValid() {return valid;}
    
    @Getter @Setter private FormPrixTube fprixtube;
    @Getter @Setter private int resultValue;
    
    private final Reponse reponse;

    //Constructeur
    public FormPrixTubeValidation(FormPrixTube fprixtube) {
        this.fprixtube = fprixtube;
        this.resultValue = 0;
        this.valid = isValid();
        this.reponse = getReponse();
    }
    
    
    
    /*
    resultVal :
            0 = Tous les champs sont incorrects.
            1 = Le Prix et le prix membre saisis sont incorrects.
           10 = La Marque et le prix membre saisis sont incorrects.
           11 = Le prix membre est incorrect.
           20 = La Marque et le prix saisis sont incorrects.
           21 = Le prix est incorrect.
           30 = La Marque est incorrect.
           31 = tous les champs sont correct.
    */
    private boolean isValid() {
        boolean result = false;
        Pattern patternMarque = Pattern.compile(regexMarque);
        Pattern patternPrix = Pattern.compile(regexPrix);
        
        //Test de validation du Marque :
        if(!fprixtube.getMarque().isEmpty()) {
            Matcher matcher = patternMarque.matcher(fprixtube.getMarque());
            if(matcher.matches()) {
                resultValue = resultValue + 1;
            }
        }
        
        //Test de validation du Prix :
        if(!fprixtube.getPrix().isEmpty()) {
            Matcher matcher = patternPrix.matcher(fprixtube.getPrix());
            if(matcher.matches()) {
                if(fprixtube.getPrixDouble() > 0.00 && fprixtube.getPrixDouble() <= 1000.00){
                    resultValue = resultValue + 10;
                }
            }
        }
        
        //Test de validation du Prix Membre :
        if(!fprixtube.getPrixMembre().isEmpty()) {
            Matcher matcher = patternPrix.matcher(fprixtube.getPrixMembre());
            if(matcher.matches()) {
                if(fprixtube.getPrixMembreDouble() >= 0.00 && fprixtube.getPrixMembreDouble() <= fprixtube.getPrixDouble()){
                    resultValue = resultValue + 20;
                }
            }
        }
        
        if (resultValue==31){
            result = true;
        }
        return result;
    }
    
    //Construction de la reponse
    public Reponse getReponse() {
        return new Reponse("prixtube", "other", resultValue, true);
    }
    
}

package com.ritndev.agcv.classes;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ritn
 */
public class Reponse {
       
    @Getter @Setter private String modelName;
    @Getter @Setter private String methodeName;
    @Getter @Setter private int resultValue;
    
    @Getter @Setter private TypeReponse typeReponse;
    @Getter @Setter private String strReponse;
    

    //Constructeur
    public Reponse(String modelName, String methodeName, int resultValue) {
        this.modelName = modelName;
        this.methodeName = methodeName;
        this.resultValue = resultValue;
        setReponse();
    }
    
    //Initialise typeReponse et strReponse en fonction du constructeur
    private void setReponse() {
        strReponse = "reponse." + modelName + "." + methodeName + ".";
        typeReponse = TypeReponse.OTHER;
        
        switch (methodeName) {
            case "create" -> typeReponse = TypeReponse.ADD;
            case "remove" -> typeReponse = TypeReponse.REMOVE;
            case "edit" -> typeReponse = TypeReponse.EDIT;
            case "info" -> typeReponse = TypeReponse.INFO;
        }
        
        switch (resultValue) {
            default : {
                //Erreur lors de l'execution
                strReponse = strReponse + "error";
                typeReponse = TypeReponse.ERROR;
                break;
            }
            case 1 : {
                //Impossible de créer à cause d'un autre élément qui rentre en conflit.
                strReponse = strReponse + "error1";
                typeReponse = TypeReponse.ERROR;
                break;
            }
            case 2 : {
                strReponse = strReponse + "success";
                break;
            } //Reussite !
            
            //Autres réponses possible dans certain cas :
            case 3 : {
                strReponse = strReponse + "result3";
                typeReponse = TypeReponse.INFO;
                break;
            }          
            case 4 : {
                strReponse = strReponse + "result4";
                break;
            }
        }  
    }
    
}

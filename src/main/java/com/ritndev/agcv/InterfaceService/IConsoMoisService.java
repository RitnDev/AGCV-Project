package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormConsoMois;
import com.ritndev.agcv.model.ConsoMois;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface IConsoMoisService {
    
    //Methode ConsoMois
    public Reponse saveConsoMois(FormConsoMois newConsoMois);
    public List<ConsoMois> listConsoMois();
    public ConsoMois findByIdConsoMois(Long id);
    public Reponse supprConsoMois(Long id);
    public Reponse updateConsoMoisPrixtube(FormConsoMois editConsoMois);
    public Reponse updateConsoMoisNbUtilises(FormConsoMois editConsoMois);
    public Reponse updateConsoMoisNbCommandes(FormConsoMois editConsoMois);
    public Reponse updateConsoMois(FormConsoMois editConsoMois);
    
}

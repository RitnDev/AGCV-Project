package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.form.FormConsoMois;
import com.ritndev.agcv.model.ConsoMois;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface IConsoMoisService {
    
    //Methode ConsoMois
    public int saveConsoMois(FormConsoMois newConsoMois);
    public List<ConsoMois> listConsoMois();
    public ConsoMois findByIdConsoMois(Long id);
    public int supprConsoMois(Long id);
    public int updateConsoMoisPrixtube(FormConsoMois editConsoMois);
    public int updateConsoMoisNbUtilises(FormConsoMois editConsoMois);
    public int updateConsoMoisNbCommandes(FormConsoMois editConsoMois);
    public int updateConsoMois(FormConsoMois editConsoMois);
    
}

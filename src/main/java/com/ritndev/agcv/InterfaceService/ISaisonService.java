package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormSaison;
import com.ritndev.agcv.model.Saison;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface ISaisonService {
    
    //Methode Saison
    public Reponse saveSaison(FormSaison newSaison);
    public List<Saison> listSaison();
    public Saison findByIdSaison(Long id);
    public Saison findByAnneeSaison(int anneeDebut);
    public Saison findByAnneeFinSaison(int anneeFin);
    public Reponse supprSaison(Long id);
    public Reponse updateSaison(FormSaison editSaison);
    public List<Saison> listSaisonHisto();
    
}

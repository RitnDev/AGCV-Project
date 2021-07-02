package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.form.FormSaison;
import com.ritndev.agcv.model.Saison;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface ISaisonService {
    
    //Methode Saison
    public int saveSaison(FormSaison newSaison);
    public List<Saison> listSaison();
    public Saison findByIdSaison(Long id);
    public int supprSaison(Long id);
    public int updateSaison(FormSaison editSaison);
    
}

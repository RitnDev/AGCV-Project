package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.form.FormPrixTube;
import com.ritndev.agcv.model.PrixTube;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface IPrixTubeService {
    
    //Methode PrixTube
    public int savePrixTube(FormPrixTube newPrixTube);
    public List<PrixTube> listPrixTube();
    public List<PrixTube> ListPrixTubeName(String nom);
    public List<PrixTube> ListPrixTubeActif();
    public PrixTube findByIdPrixTube(Long id);
    public int supprPrixTube(Long id);
    public int updatePrixTube(FormPrixTube editPrixTube);
    
}

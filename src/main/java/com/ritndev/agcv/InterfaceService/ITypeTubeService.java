package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormTypeTube;
import com.ritndev.agcv.model.TypeTube;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface ITypeTubeService {
    
    //Methode TypeTube
    public Reponse saveTypeTube(FormTypeTube formTypeTube);
    public List<TypeTube> listTypeTube();
    public List<TypeTube> listDataTypeTube();
    public TypeTube findByIdTypeTube(Long id);
    public Reponse supprTypeTube(Long id);
    public Reponse updateTypeTube(FormTypeTube editTypeTube);
    
}

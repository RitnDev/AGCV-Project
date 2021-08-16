package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormData;
import com.ritndev.agcv.model.MainData;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface IMainDataService {
    
    //Methode MainData
    public Reponse newMainData();
    public List<MainData> listMainData();
    public MainData findByIdMainData(Long id);
    public Reponse supprMainData(Long id);
    public Reponse updateMainData(FormData editMainData);
    public Reponse updateBudget(FormData editMainData);
    public Reponse updateSeuil(FormData editMainData);
    public MainData returnMainData();
    
}

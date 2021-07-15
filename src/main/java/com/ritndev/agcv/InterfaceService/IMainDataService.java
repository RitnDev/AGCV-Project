package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.form.FormData;
import com.ritndev.agcv.model.MainData;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface IMainDataService {
    
    //Methode MainData
    public int newMainData();
    public List<MainData> listMainData();
    public MainData findByIdMainData(Long id);
    public int supprMainData(Long id);
    public int updateMainData(FormData editMainData);
    public int updateBudget(FormData editMainData);
    public int updateSeuil(FormData editMainData);
    public MainData returnMainData();
    
}

package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.form.FormRestock;
import com.ritndev.agcv.model.Restock;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface IRestockService {
    
    //Methode Restock
    public int saveRestock(FormRestock newRestock);
    public List<Restock> listRestock();
    public Restock findByIdRestock(Long id);
    public int supprRestock(Long id);
    public int updateRestock(FormRestock editRestock);
    
}

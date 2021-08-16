package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormRestock;
import com.ritndev.agcv.model.Restock;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface IRestockService {
    
    //Methode Restock
    public Reponse saveRestock(FormRestock newRestock);
    public List<Restock> listRestock();
    public Restock findByIdRestock(Long id);
    public Reponse supprRestock(Long id);
    public Reponse updateRestock(FormRestock editRestock);
    
}

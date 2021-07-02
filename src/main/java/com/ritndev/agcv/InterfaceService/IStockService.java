package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.form.FormStock;
import com.ritndev.agcv.model.StockCompetition;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface IStockService {
    
    //Methode StockCompetition
    public int newStock();
    public List<StockCompetition> listStock();
    public StockCompetition findByIdStock(Long id);
    public int supprStock(Long id);
    public int updateStock(FormStock editStock);
    
}

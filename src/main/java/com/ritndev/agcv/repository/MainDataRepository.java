package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.MainData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;        
        
/**
 *
 * @author Ritn
 */
public interface MainDataRepository extends JpaRepository<MainData, Long> {
    
    public List<MainData> findByActifTrue();
    
    public List<MainData> findByIdStockCompet(long idStockCompet);
    
}

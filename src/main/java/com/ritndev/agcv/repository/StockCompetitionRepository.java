package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.StockCompetition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ritn
 */
@Repository
public interface StockCompetitionRepository extends JpaRepository<StockCompetition, Long> {
    
}

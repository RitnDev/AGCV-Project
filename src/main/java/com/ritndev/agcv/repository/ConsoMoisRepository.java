package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.ConsoMois;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ritn
 */
@Repository
public interface ConsoMoisRepository extends JpaRepository<ConsoMois, Long> {
    
}

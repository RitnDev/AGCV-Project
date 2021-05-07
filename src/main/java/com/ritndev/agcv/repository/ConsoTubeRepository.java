package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.ConsoTube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ritn
 */
@Repository
public interface ConsoTubeRepository extends JpaRepository<ConsoTube, Long> {
    
}

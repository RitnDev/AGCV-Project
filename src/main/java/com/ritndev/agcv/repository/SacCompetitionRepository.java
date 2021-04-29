package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.SacCompetition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ritn
 */
@Repository
public interface SacCompetitionRepository extends JpaRepository<SacCompetition, Long> {
    
}

package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ritn
 */
@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    
}

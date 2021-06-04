package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.Competition;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ritn
 */
@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    
    public List<Competition> findByIdSaison(Long idSaison);
    
}

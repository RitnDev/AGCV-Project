package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ritn
 */
@Repository
public interface MembreRepository extends JpaRepository<Membre, Long> {
    
}

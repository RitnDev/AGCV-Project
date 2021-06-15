package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.TypeVolant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ritn
 */
@Repository
public interface TypeVolantRepository extends JpaRepository<TypeVolant, Long> {
    
}

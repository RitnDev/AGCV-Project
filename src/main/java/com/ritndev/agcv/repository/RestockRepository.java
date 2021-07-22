package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.Restock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ritn
 */
@Repository
public interface RestockRepository extends JpaRepository<Restock, Long> {
    
}

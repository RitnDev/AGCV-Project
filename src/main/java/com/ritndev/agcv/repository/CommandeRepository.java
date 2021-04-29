package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ritn
 */
@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    
}

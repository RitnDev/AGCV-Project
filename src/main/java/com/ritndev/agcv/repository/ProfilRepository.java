package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ritn
 */
@Repository
public interface ProfilRepository extends JpaRepository<Profil, Long> {
    
}

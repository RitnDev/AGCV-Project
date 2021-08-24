package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.PersistentLogins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ritn
 */
@Repository
public interface PersistentLoginsRepository extends JpaRepository<PersistentLogins, String> {
    
}

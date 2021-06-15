package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.PrixTube;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Ritn
 */
public interface PrixTubeRepository extends JpaRepository<PrixTube, Long> {
    
    //@Query("SELECT pt FROM prix_tube pt WHERE pt.idTypeTube = ?1")
    public List<PrixTube> findByIdTypeTube(long idTypeTube);
    
}

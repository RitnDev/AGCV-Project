package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.PrixTube;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ritn
 */
public interface PrixTubeRepository extends JpaRepository<PrixTube, Long> {
    
    public List<PrixTube> findByIdTypeTube(long idTypeTube);
    public List<PrixTube> findByActifTrue();
    public List<PrixTube> findByDefautTrue();
    public List<PrixTube> findByMarque(String marque);
    
}

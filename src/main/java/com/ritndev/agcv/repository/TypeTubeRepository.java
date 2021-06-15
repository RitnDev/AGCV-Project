package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.TypeTube;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Ritn
 */
public interface TypeTubeRepository extends JpaRepository<TypeTube, Long> {
    
    @Query("select tt from type_tube tt where tt.nom = ?1")
    public List<TypeTube> findByNom(String nom);
    
}

package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.TypeTube;
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Ritn
 */
public interface TypeTubeRepository extends JpaRepository<TypeTube, Long> {
    
    /*
    @Query("SELECT tt FROM type_tube tt WHERE tt.nom = ?1 LIMIT 1")
    public TypeTube findByNom(String nom);
    */
}

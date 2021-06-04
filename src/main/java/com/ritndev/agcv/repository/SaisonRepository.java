package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.Saison;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ritn
 */
@Repository
public interface SaisonRepository extends JpaRepository<Saison, Long> {

    @Query(value = "SELECT COALESCE(max(saisonId),0) FROM saison", nativeQuery = true)
    public Long findLastId();
    public boolean existsByAnneeDebut(int anneeDebut);

}
